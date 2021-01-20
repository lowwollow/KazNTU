package kz.almaty.satbayevuniversity.ui.schedule.exams;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import kz.almaty.satbayevuniversity.R;
import kz.almaty.satbayevuniversity.data.AccountDao;
import kz.almaty.satbayevuniversity.data.App;
import kz.almaty.satbayevuniversity.data.AppDatabase;
import kz.almaty.satbayevuniversity.data.entity.schedule.Exam;
import kz.almaty.satbayevuniversity.data.network.KaznituRetrofit;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExamsViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    SharedPreferences sharedPreferences = App.getContext().getSharedPreferences("shared_preferences",Context.MODE_PRIVATE);
    private MutableLiveData<List<Exam>> examLiveData = new MutableLiveData<>();
    private List<Exam> examList = new ArrayList<>();
    private List<Exam> examListDB = new ArrayList<>();
    public ObservableBoolean getEmptyBoolean = new ObservableBoolean();



    private AppDatabase db = App.getInstance().getDatabase();
    private AccountDao accountDao = db.accountDao();
    //
    private BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(3);
    private ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 1,
            TimeUnit.SECONDS, queue);

    private ConnectivityManager connManager = (ConnectivityManager)App.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
    private NetworkInfo activeNetwork = connManager.getActiveNetworkInfo();
    public void getExam(String lang){

        boolean onlyServer = sharedPreferences.getBoolean(App.getContext().getString(R.string.only_server),false);
        if(onlyServer){
            if (connManager.getActiveNetworkInfo() != null && connManager.getActiveNetworkInfo().isAvailable() && activeNetwork.isConnected()) {
                getExamListFromServer(lang);
            }
        }else{
            executor.execute(() ->{
                if(!accountDao.getExam().isEmpty()){
                    examListDB = accountDao.getExam();
                    examLiveData.postValue(examListDB);
                    getEmptyBoolean.set(examListDB.isEmpty());
                    if (connManager.getActiveNetworkInfo() != null && connManager.getActiveNetworkInfo().isAvailable() && activeNetwork.isConnected()) {
                        getExamListFromServer(lang);
                    }
                }else{
                    if (connManager.getActiveNetworkInfo() != null && connManager.getActiveNetworkInfo().isAvailable() && activeNetwork.isConnected()) {
                        getExamListFromServer(lang);
                    }else{
                        getEmptyBoolean.set(true);
                    }
                }
            });
        }
    }

    private void getExamListFromServer(String lang) {
            KaznituRetrofit.getApi().updateExam(lang).enqueue(new Callback<List<Exam>>() {
                @Override
                public void onResponse(Call<List<Exam>> call, Response<List<Exam>> response) {
                    if (response.body() != null) {
                        examList = response.body();
                        getEmptyBoolean.set(examList.isEmpty());
                        if(!examList.equals(examListDB)) {
                            new Thread(() -> {
                                update(examList);
                            }).start();
                            examLiveData.setValue(examList);
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<Exam>> call, Throwable t) {
                    System.out.println("####Exam failure" + t.getMessage() + "&&&" + t.getCause());

                }
            });
    }

    MutableLiveData<List<Exam>> getExamLiveData(){
        if(examLiveData == null){
            examLiveData = new MutableLiveData<>();
        }
        return examLiveData;
    }


         private void update(List<Exam> examList) {
        executor.execute(() -> {
            accountDao.deleteExam();
            accountDao.insertExam(examList);
        });
    }

}
