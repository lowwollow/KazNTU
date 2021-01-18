package kz.almaty.satbayevuniversity.ui.notification.portalNotification;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.IOException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import kz.almaty.satbayevuniversity.R;
import kz.almaty.satbayevuniversity.data.AccountDao;
import kz.almaty.satbayevuniversity.data.App;
import kz.almaty.satbayevuniversity.data.AppDatabase;
import kz.almaty.satbayevuniversity.data.entity.notification.Notification;
import kz.almaty.satbayevuniversity.data.network.KaznituRetrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PortalNotificationViewModel extends ViewModel {
    SharedPreferences sharedPreferences = App.getContext().getSharedPreferences("shared_preferences",Context.MODE_PRIVATE);
    public ObservableBoolean isEmpty = new ObservableBoolean();

    private MutableLiveData<List<Notification>> notificationMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> handleTimeout = new MutableLiveData<>();

    private List<Notification> listOfNewsFromServer = new ArrayList<>();
    private List<Notification> listOfNewsFromDB = new ArrayList<>();

    private AppDatabase db = App.getInstance().getDatabase();
    private AccountDao accountDao = db.accountDao();

    public ObservableBoolean loadRv = new ObservableBoolean();

    private BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(3);
    private ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 1,
            TimeUnit.SECONDS, queue);
    private ConnectivityManager connManager = (ConnectivityManager)App.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
    private NetworkInfo activeNetwork = connManager.getActiveNetworkInfo();

    void getNotification(){
        loadRv.set(true);
        boolean onlyServer = sharedPreferences.getBoolean(App.getContext().getString(R.string.only_server),false);
        if(onlyServer){
            if(connManager.getActiveNetworkInfo() != null && connManager.getActiveNetworkInfo().isAvailable() && activeNetwork.isConnected()) {
                getNotificationListFromServer();
            }
        }else{
                MyTask task = new MyTask();
                task.execute();
            }
        }

    private void getNotificationListFromServer(){
            KaznituRetrofit.getApi().updateNotification().enqueue(new Callback<List<Notification>>() {
                @Override
                public void onResponse(Call<List<Notification>> call, Response<List<Notification>> response) {
                    if (response.isSuccessful()) {
                        loadRv.set(false);
                        listOfNewsFromServer = response.body();
                        isEmpty.set(listOfNewsFromServer.isEmpty());
                        if (!listOfNewsFromServer.equals(listOfNewsFromDB)) {
                            isEmpty.set(listOfNewsFromServer.isEmpty());
                            new Thread(() -> {
                                update(listOfNewsFromServer);
                            }).start();
                            notificationMutableLiveData.setValue(listOfNewsFromServer);
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<Notification>> call, Throwable t) {
                    if (t instanceof SocketTimeoutException) {
                        exception();
                    } else if (t instanceof IOException) {
                        exception();
                    } else if (t instanceof SocketException) {
                        exception();
                    }
                }
            });
    }

    MutableLiveData<List<Notification>> getNotificationMutableLiveData() {
        if(notificationMutableLiveData == null){
            notificationMutableLiveData = new MutableLiveData<>();
        }
        return notificationMutableLiveData;
    }

    MutableLiveData<Boolean> getHandleTimeout(){
        if (handleTimeout == null){
            handleTimeout = new MutableLiveData<>();
        }
        return handleTimeout;
    }

    private void exception(){
        loadRv.set(false);
        handleTimeout.setValue(true);
    }

    private void update(List<Notification> news) {
        executor.execute(() ->{
            accountDao.deleteNotification();
            accountDao.insertNews(news);
            System.out.println("####### update News");
        });
    }

    private class MyTask extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            try{
                TimeUnit.MILLISECONDS.sleep(150);
                if(!accountDao.getNews().isEmpty()){
                    loadRv.set(false);
                    listOfNewsFromDB = accountDao.getNews();
                    notificationMutableLiveData.postValue(listOfNewsFromDB);
                    if(connManager.getActiveNetworkInfo() != null && connManager.getActiveNetworkInfo().isAvailable() && activeNetwork.isConnected()) {
                        getNotificationListFromServer();
                    }
                }else{
                    if(connManager.getActiveNetworkInfo() != null && connManager.getActiveNetworkInfo().isAvailable() && activeNetwork.isConnected()) {
                        getNotificationListFromServer();
                    }else{
                        loadRv.set(false);
                        isEmpty.set(true);
                    }
                }

            }catch (InterruptedException e){
                e.printStackTrace();
            }
            return null;
        }
    }
}
