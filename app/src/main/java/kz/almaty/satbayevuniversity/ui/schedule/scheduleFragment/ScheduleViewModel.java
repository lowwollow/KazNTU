package kz.almaty.satbayevuniversity.ui.schedule.scheduleFragment;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.databinding.Observable;
import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import kz.almaty.satbayevuniversity.R;
import kz.almaty.satbayevuniversity.data.AccountDao;
import kz.almaty.satbayevuniversity.data.App;
import kz.almaty.satbayevuniversity.data.AppDatabase;
import kz.almaty.satbayevuniversity.data.entity.schedule.Schedule;
import kz.almaty.satbayevuniversity.data.network.KaznituRetrofit;

import java.util.ArrayList;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import kz.almaty.satbayevuniversity.utils.LocaleHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScheduleViewModel extends ViewModel {
    private static final String TAG = "ScheduleViewModel";
    SharedPreferences sharedPreferences = App.getContext().getSharedPreferences("shared_preferences",Context.MODE_PRIVATE);
    private List<Schedule> scheduleList = new ArrayList<>();
    private List<Schedule> scheduleListFromDb = new ArrayList<>();
    private MutableLiveData<List<Schedule>> scheduleLiveData;

    public ObservableBoolean loadRv = new ObservableBoolean();
    public MutableLiveData<Boolean> emptyImage = new MutableLiveData<>();;
    public MutableLiveData<Boolean> loadRv1 = new MutableLiveData<>();
    private MutableLiveData<Integer> handleError;
    private MutableLiveData<Integer> handleTimeout;

    private final AppDatabase db = App.getInstance().getDatabase();
    private final AccountDao accountDao = db.accountDao();

    private BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(3);
    private ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 1,
            TimeUnit.SECONDS, queue);
    private ConnectivityManager connManager = (ConnectivityManager)App.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
    private NetworkInfo activeNetwork = connManager.getActiveNetworkInfo();

    public void getSchedule(String lang) {
        loadRv.set(true);
        loadRv1.setValue(true);
        boolean onlyServer = sharedPreferences.getBoolean(App.getContext().getString(R.string.only_server),false);
        if(onlyServer){
            if(connManager.getActiveNetworkInfo() != null && connManager.getActiveNetworkInfo().isAvailable() && activeNetwork.isConnected() ){
                getScheduleListFromServer(lang);
            }
        }else{
            MyTask task = new MyTask(lang);
            task.execute();
        }
    }


    private void getScheduleListFromServer(String lang){
        emptyImage.postValue(false);
            KaznituRetrofit.getApi().updateSchedule(lang).enqueue(new Callback<List<Schedule>>() {
                @Override
                public void onResponse(Call<List<Schedule>> call, Response<List<Schedule>> response) {
                    switch (response.code()) {
                        case 200:
                            loadRv.set(false);
                            loadRv1.setValue(false);
                            scheduleList = response.body();
                            if (scheduleList.size() == 0) emptyImage.postValue(true);
                            if(!scheduleList.equals(scheduleListFromDb)){
                                new Thread(() -> {
                                    update(scheduleList);
                                }).start();
                                scheduleLiveData.setValue(scheduleList);
                            }
                            break;
                        case 404:
                            handleError.setValue(404);
                            break;
                        case 400:
                            handleError.setValue(400);
                            break;
                        case 401:
                            handleError.setValue(401);
                            break;
                        case 500:
                            handleError.setValue(500);
                            break;
                    }
                }
                @Override
                public void onFailure(Call<List<Schedule>> call, Throwable t) {
                }
            });

    }

    public MutableLiveData<Boolean> getEmptyImage(){
        return emptyImage;
    }


    public MutableLiveData<List<Schedule>> getScheduleLiveData(){
        if(scheduleLiveData==null){
            scheduleLiveData = new MutableLiveData<>();
        }
        return scheduleLiveData;
    }

    MutableLiveData<Integer> getHandleError(){
        if(handleError == null){
            handleError = new MutableLiveData<>();
        }
        return handleError;
    }

    MutableLiveData<Integer> getHandleTimeout(){
        if (handleTimeout == null){
            handleTimeout = new MutableLiveData<>();
        }
        return handleTimeout;
    }

    private void update(List<Schedule> scheduleList) {
        executor.execute(() -> {
            accountDao.deleteSchedule();
            accountDao.insertSchedule(scheduleList);
        });
    }


    private class MyTask extends AsyncTask<Void, Void, Void>{
        String lang;

        public MyTask(String lang){
            this.lang = lang;
        }

       // @SuppressLint("WrongThread")
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                TimeUnit.MILLISECONDS.sleep(30);
                if (!accountDao.getSchedule().isEmpty()) {
                    loadRv.set(false);
                    scheduleListFromDb = accountDao.getSchedule();
                    scheduleLiveData.postValue(scheduleListFromDb);
                    if (connManager.getActiveNetworkInfo() != null && connManager.getActiveNetworkInfo().isAvailable() && activeNetwork.isConnected()) {
                        getScheduleListFromServer(lang);
                    }
                } else {
                    if (connManager.getActiveNetworkInfo() != null && connManager.getActiveNetworkInfo().isAvailable() && activeNetwork.isConnected()) {
                        getScheduleListFromServer(lang);
                    } else {
                        loadRv.set(false);
                        //emptyImage.set(true);
                    }
                }
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            return null;
        }
    }

}
