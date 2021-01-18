package kz.almaty.satbayevuniversity.ui.grade.transcriptFragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import kz.almaty.satbayevuniversity.R;
import kz.almaty.satbayevuniversity.data.AccountDao;
import kz.almaty.satbayevuniversity.data.App;
import kz.almaty.satbayevuniversity.data.AppDatabase;
import kz.almaty.satbayevuniversity.data.entity.grade.transcript.ResponseTranscript;
import kz.almaty.satbayevuniversity.data.entity.grade.transcript.SemestersItem;
import kz.almaty.satbayevuniversity.data.network.KaznituRetrofit;

import java.io.IOException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TranscriptViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    SharedPreferences sharedPreferences = App.getContext().getSharedPreferences("shared_preferences",Context.MODE_PRIVATE);
    private List<SemestersItem> semestersItems = new ArrayList<>();
    private List<SemestersItem> semestersItemsDB = new ArrayList<>();

    private MutableLiveData<List<SemestersItem>> transcriptLiveData = new MutableLiveData<>();

    private AppDatabase db = App.getInstance().getDatabase();
    private AccountDao accountDao = db.accountDao();
    public ObservableBoolean loadRv = new ObservableBoolean();
    public ObservableBoolean getEmptyBoolean = new ObservableBoolean();

    private MutableLiveData<Boolean> handleTimeout = new MutableLiveData<>();
    //
    private BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(3);
    private ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 1,
            TimeUnit.SECONDS, queue);

    private ConnectivityManager connManager = (ConnectivityManager)App.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
    private NetworkInfo activeNetwork = connManager.getActiveNetworkInfo();

    public void getTranscript(){
        boolean onlyServer = sharedPreferences.getBoolean(App.getContext().getString(R.string.only_server),false);
        if(onlyServer){
            if (connManager.getActiveNetworkInfo() != null && connManager.getActiveNetworkInfo().isAvailable() && activeNetwork.isConnected()) {
                getSemesterItemListFromServer();
            }
        }else{
            MyTask task = new MyTask();
            task.execute();
        }
    }

    private void getSemesterItemListFromServer() {
            KaznituRetrofit.getApi().updateTranscript().enqueue(new Callback<ResponseTranscript>() {
                @Override
                public void onResponse(Call<ResponseTranscript> call, Response<ResponseTranscript> response) {
                    if (response.isSuccessful()) {
                        loadRv.set(false);
                        semestersItems = response.body().getSemesters();
                        getEmptyBoolean.set(semestersItems.isEmpty());
                        if(!semestersItems.equals(semestersItemsDB)){
                            new Thread(() -> {
                                update(semestersItems);
                            }).start();
                            transcriptLiveData.setValue(semestersItems);
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseTranscript> call, Throwable t) {
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

    MutableLiveData<List<SemestersItem>> getTranscriptLiveData(){
        if(transcriptLiveData == null){
            transcriptLiveData = new MutableLiveData<>();
        }
        return transcriptLiveData;
    }

    MutableLiveData<Boolean> getHandleTimeout(){
        if (handleTimeout == null){
            handleTimeout = new MutableLiveData<>();
        }
        return handleTimeout;
    }



    private void update(List<SemestersItem> semestersItemList) {
        executor.execute(() -> {
            accountDao.deleteSemestersItem();
            accountDao.insertSemestersItem(semestersItemList);
        });
    }

    private void exception(){
        loadRv.set(false);
        handleTimeout.setValue(true);
    }

    private class MyTask extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            try{
                TimeUnit.MILLISECONDS.sleep(150);
                if(!accountDao.getSemestersItem().isEmpty()){
                    loadRv.set(false);
                    semestersItemsDB = accountDao.getSemestersItem();
                    transcriptLiveData.postValue(semestersItemsDB);
                    if (connManager.getActiveNetworkInfo() != null && connManager.getActiveNetworkInfo().isAvailable() && activeNetwork.isConnected()) {
                        getSemesterItemListFromServer();
                    }
                }else{
                    if (connManager.getActiveNetworkInfo() != null && connManager.getActiveNetworkInfo().isAvailable() && activeNetwork.isConnected()) {
                        getSemesterItemListFromServer();
                    }else{
                        loadRv.set(false);
                        getEmptyBoolean.set(true);
                    }
                }

            }catch (InterruptedException e){
                e.printStackTrace();
            }
            return null;
        }
    }
}
