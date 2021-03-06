package kz.almaty.satbayevuniversity.ui.individualPlan.deferedDisciplnes;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

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
import kz.almaty.satbayevuniversity.data.entity.individualPlan.deferedDiscipline.DeferredDiscipline1;
import kz.almaty.satbayevuniversity.data.entity.individualPlan.deferedDiscipline.DeferredDisciplineGroup1;
import kz.almaty.satbayevuniversity.data.network.KaznituRetrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeferredDisciplineViewModel extends ViewModel {
    SharedPreferences sharedPreferences = App.getContext().getSharedPreferences("shared_preferences", Context.MODE_PRIVATE);
    private MutableLiveData<List<DeferredDiscipline1>> deferredDisciplineLiveData = new MutableLiveData<>();
    private List<DeferredDiscipline1> deferredDisciplineList = new ArrayList<>();
    private List<DeferredDiscipline1> deferredDisciplineListDB = new ArrayList<>();
    private AppDatabase db = App.getInstance().getDatabase();
    private AccountDao accountDao = db.accountDao();

    public ObservableBoolean getEmptyBoolean = new ObservableBoolean();
    public ObservableBoolean loadRv = new ObservableBoolean();

    private MutableLiveData<Boolean> handleTimeout = new MutableLiveData<>();

    private BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(3);
    private ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 1,
            TimeUnit.SECONDS, queue);
    private ConnectivityManager connManager = (ConnectivityManager) App.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
    private NetworkInfo activeNetwork = connManager.getActiveNetworkInfo();



    void getDeferredDiscipline(String lang) {
        loadRv.set(true);
        boolean onlyServer = sharedPreferences.getBoolean(App.getContext().getString(R.string.only_server), false);
            if (onlyServer) {
                if (connManager.getActiveNetworkInfo() != null && connManager.getActiveNetworkInfo().isAvailable() && activeNetwork.isConnected()) {
                    getDeferredDisciplineFromServer(lang);
                }
            } else {
                MyTask task = new MyTask(lang);
                task.execute();
            }
    }

    private class MyTask extends AsyncTask<Void, Void, Void> {

        String lang = new String();
        public MyTask(String lang){
            this.lang = lang;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            if (!accountDao.getDeferredDiscipline1().isEmpty()) {
                loadRv.set(false);
                deferredDisciplineListDB = accountDao.getDeferredDiscipline1();
                deferredDisciplineLiveData.postValue(deferredDisciplineListDB);
                if (connManager.getActiveNetworkInfo() != null && connManager.getActiveNetworkInfo().isAvailable() && activeNetwork.isConnected()) {
                    getDeferredDisciplineFromServer(lang);
                }
            } else {
                if (connManager.getActiveNetworkInfo() != null && connManager.getActiveNetworkInfo().isAvailable() && activeNetwork.isConnected()) {
                    getDeferredDisciplineFromServer(lang);
                } else {
                    loadRv.set(false);
                    getEmptyBoolean.set(true);
                }
            }
            return null;
        }
    }

    private void getDeferredDisciplineFromServer(String lang) {
        KaznituRetrofit.getApi().updateDeferedDiscipline(lang).enqueue(new Callback<DeferredDisciplineGroup1>() {
            @Override
            public void onResponse(Call<DeferredDisciplineGroup1> call, Response<DeferredDisciplineGroup1> response) {
                if (response.isSuccessful()) {
                    loadRv.set(false);
                    deferredDisciplineList = response.body().getDeferredDiscipline1List();

                    if (!deferredDisciplineList.equals(deferredDisciplineListDB)) {
                        new Thread(() -> {
                            update(deferredDisciplineList);
                        }).start();
                        deferredDisciplineLiveData.setValue(deferredDisciplineList);
                    }

                    if (deferredDisciplineList.isEmpty()) {
                        getEmptyBoolean.set(true);
                    }

                }
            }

            @Override
            public void onFailure(Call<DeferredDisciplineGroup1> call, Throwable t) {
                Log.d("TESTING", "onResponse: TTEESSTT2 FAIL" + t.getMessage());
            }
        });
    }


    private void update(List<DeferredDiscipline1> gr){
        executor.execute(()->{
            accountDao.deleteDeferredDiscipline1();
            accountDao.insertDeferredDiscipline1(gr);
        });
    }

    MutableLiveData<List<DeferredDiscipline1>> getDeferredDisciplineLiveData(){
        if (deferredDisciplineLiveData == null)
            deferredDisciplineLiveData = new MutableLiveData<>();
        return deferredDisciplineLiveData;
    }
}


