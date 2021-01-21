package kz.almaty.satbayevuniversity.ui.grade.attestation;

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
import kz.almaty.satbayevuniversity.data.entity.grade.attestation.Attestation;
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


public class GradeViewModel extends ViewModel {
    SharedPreferences sharedPreferences = App.getContext().getSharedPreferences("shared_preferences",Context.MODE_PRIVATE);
    private MutableLiveData<List<Attestation>> attestationLiveData = new MutableLiveData<>();
    private List<Attestation> attestationList = new ArrayList<>();
    private List<Attestation> attestationListDB = new ArrayList<>();

    private AppDatabase db = App.getInstance().getDatabase();
    private AccountDao accountDao = db.accountDao();

    public ObservableBoolean getEmptyBoolean = new ObservableBoolean();
    public ObservableBoolean loadRv = new ObservableBoolean();

    private MutableLiveData<Boolean> handleTimeout = new MutableLiveData<>();

    //
    private BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(3);
    private ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 1,
            TimeUnit.SECONDS, queue);
    private ConnectivityManager connManager = (ConnectivityManager)App.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
    private NetworkInfo activeNetwork = connManager.getActiveNetworkInfo();

    void getAttestation(String lang) {
        loadRv.set(true);
        boolean onlyServer = sharedPreferences.getBoolean(App.getContext().getString(R.string.only_server),false);
        if(onlyServer){
            if (connManager.getActiveNetworkInfo() != null && connManager.getActiveNetworkInfo().isAvailable() && activeNetwork.isConnected()) {
                getGradeListFromServer(lang);
            }
        }else{
            MyTask task = new MyTask(lang);
            task.execute();
        }


    }

    private void getGradeListFromServer(String lang) {
            KaznituRetrofit.getApi().updateAttestation(lang).enqueue(new Callback<List<Attestation>>() {
                @Override
                public void onResponse(Call<List<Attestation>> call, Response<List<Attestation>> response) {
                        if (response.isSuccessful()) {
                        loadRv.set(false);
                        attestationList = response.body();
                        getEmptyBoolean.set(attestationList.isEmpty());
                        if(!attestationList.equals(attestationListDB)){
                            new Thread(() -> {
                                update(attestationList);
                            }).start();
                            attestationLiveData.postValue(attestationList);
                            // post value sets value from the background thread
                        }
                    }
                }
                @Override
                public void onFailure(Call<List<Attestation>> call, Throwable t) {
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

        MutableLiveData<List<Attestation>> getAttestationLiveDate () {
            if (attestationLiveData == null) {
                attestationLiveData = new MutableLiveData<>();
            }
            return attestationLiveData;
        }

        MutableLiveData<Boolean> getHandleTimeout () {
            if (handleTimeout == null) {
                handleTimeout = new MutableLiveData<>();
            }
            return handleTimeout;
        }


    private void update(List<Attestation> attestationList) {
        executor.execute(() -> { // lambda
            accountDao.deleteAttestation();
            accountDao.insertAttestation(attestationList);
        });
    }

        private void exception(){
            loadRv.set(false);
            handleTimeout.setValue(true);
        }

        private class MyTask extends AsyncTask<Void, Void, Void>{

        String lang = new String();
        public MyTask(String lang){
            this.lang = lang;
        }

        @Override
            protected Void doInBackground(Void... voids) {
                try{
                    TimeUnit.MILLISECONDS.sleep(150);
                    if(!accountDao.getAttestation().isEmpty()){
                        loadRv.set(false);
                        attestationListDB = accountDao.getAttestation();
                        attestationLiveData.postValue(attestationListDB);
                        if (connManager.getActiveNetworkInfo() != null && connManager.getActiveNetworkInfo().isAvailable() && activeNetwork.isConnected()) {
                            getGradeListFromServer(lang);
                        }
                    }else{
                        if (connManager.getActiveNetworkInfo() != null && connManager.getActiveNetworkInfo().isAvailable() && activeNetwork.isConnected()) {
                            getGradeListFromServer(lang);
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
