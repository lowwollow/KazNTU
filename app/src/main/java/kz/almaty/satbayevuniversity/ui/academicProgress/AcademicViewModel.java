package kz.almaty.satbayevuniversity.ui.academicProgress;


import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.onesignal.OneSignal;

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
import kz.almaty.satbayevuniversity.data.entity.academic.ResponseJournal;
import kz.almaty.satbayevuniversity.data.network.KaznituRetrofit;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AcademicViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    SharedPreferences sharedPreferences = App.getContext().getSharedPreferences("shared_preferences",Context.MODE_PRIVATE);

    private MutableLiveData<List<ResponseJournal>> academicData = new MutableLiveData<>();
    private List<ResponseJournal> responseJournalList = new ArrayList<>();
    private List<ResponseJournal> responseJournalListForDB = new ArrayList<>();

    public ObservableBoolean getEmptyBoolean = new ObservableBoolean();
    public ObservableBoolean loadRv = new ObservableBoolean();

    private MutableLiveData<Integer> handleError = new MutableLiveData<>();
    private MutableLiveData<Integer> handleTimeout = new MutableLiveData<>();

    private AppDatabase db = App.getInstance().getDatabase();
    private AccountDao accountDao = db.accountDao();

    private BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(3);
    private ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 1,
            TimeUnit.SECONDS, queue);
    private ConnectivityManager connManager = (ConnectivityManager)App.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
    private NetworkInfo activeNetwork = connManager.getActiveNetworkInfo();

    void getJournal() {
        loadRv.set(true);
        boolean onlyServer = sharedPreferences.getBoolean(App.getContext().getString(R.string.only_server),false);
        if(onlyServer){
            if (connManager.getActiveNetworkInfo() != null && connManager.getActiveNetworkInfo().isAvailable() && activeNetwork.isConnected()) {
                getJournalListFromServer();
            }
        }else{
            executor.execute(() ->{
                if(!accountDao.getResponseJournal().isEmpty()){
                    loadRv.set(false);
                    responseJournalListForDB = accountDao.getResponseJournal();
                    academicData.postValue(responseJournalListForDB);
                    if (connManager.getActiveNetworkInfo() != null && connManager.getActiveNetworkInfo().isAvailable() && activeNetwork.isConnected()) {
                        getJournalListFromServer();
                    }
                }else {
                    if (connManager.getActiveNetworkInfo() != null && connManager.getActiveNetworkInfo().isAvailable() && activeNetwork.isConnected()) {
                        getJournalListFromServer();
                    } else {
                        loadRv.set(false);
                        getEmptyBoolean.set(true);
                    }
                }
            });
        }
    }

     private void getJournalListFromServer() {
             KaznituRetrofit.getApi().updateJournal().enqueue(new Callback<List<ResponseJournal>>() {
                 @Override
                 public void onResponse(Call<List<ResponseJournal>> call, Response<List<ResponseJournal>> response) {
                     switch (response.code()) {
                         case 200:
                             loadRv.set(false);
                             responseJournalList = response.body();
                             getEmptyBoolean.set(responseJournalList.isEmpty());
                             if(!responseJournalList.equals(responseJournalListForDB)){
                                 new Thread(() -> {
                                     update(responseJournalList);
                                     System.out.println("#######update");
                                 }).start();
                                 academicData.setValue(responseJournalList);
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
                 public void onFailure(Call<List<ResponseJournal>> call, Throwable t) {
                     if (t instanceof SocketTimeoutException) {
                         exception();
                     } else if (t instanceof IOException) {
                         exception();
                     }
                 }
             });
     }


    MutableLiveData<Integer> getHandleTimeout(){
        if (handleTimeout == null){
            handleTimeout = new MutableLiveData<>();
        }
        return handleTimeout;
    }

    MutableLiveData<List<ResponseJournal>> getAcademicData(){
        if(academicData == null){
            academicData = new MutableLiveData<>();
        }
        return academicData;
    }

    MutableLiveData<Integer> getHandleError(){
        if(handleError == null){
            handleError = new MutableLiveData<>();
        }
        return handleError;
    }



    private void update(List<ResponseJournal> responseJournals) {
        executor.execute(() -> {
                accountDao.deleteResponseJournal();
                accountDao.insertResponseJournal(responseJournals);
        });
    }

    private void exception(){
        loadRv.set(false);
        handleTimeout.setValue(1);
    }
    public void registerPlayerId(){
        String playerId = OneSignal.getPermissionSubscriptionState().getSubscriptionStatus().getUserId();
        KaznituRetrofit.getApi().registerPlayerId(playerId,"android","3.0").enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });
    }
}
