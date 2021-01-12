package kz.almaty.satbayevuniversity.ui.individualPlan.chosenDisciplines;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.onesignal.OneSignal;

import java.io.IOException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import kz.almaty.satbayevuniversity.R;
import kz.almaty.satbayevuniversity.data.AccountDao;
import kz.almaty.satbayevuniversity.data.App;
import kz.almaty.satbayevuniversity.data.AppDatabase;
import kz.almaty.satbayevuniversity.data.entity.individualPlan.choosenDiscipline.ChosenDiscipline1;
import kz.almaty.satbayevuniversity.data.entity.individualPlan.choosenDiscipline.ChosenDisciplineGroup1;
import kz.almaty.satbayevuniversity.data.entity.schedule.Schedule;
import kz.almaty.satbayevuniversity.data.network.KaznituRetrofit;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChosenDisciplineViewModel extends ViewModel {
    private static final String TAG = "ChosenDisciplineViewModel";
    SharedPreferences sharedPreferences = App.getContext().getSharedPreferences("shared_preferences", Context.MODE_PRIVATE);
    private List<ChosenDiscipline1> chosenDisciplines = new ArrayList<>();
    private List<ChosenDiscipline1> chosenDisciplinesFromDb = new ArrayList<>();
    private MutableLiveData<List<ChosenDiscipline1>> chosenDisciplinesLiveData = new MutableLiveData<>();

    public ObservableBoolean loadRv = new ObservableBoolean();
    public ObservableBoolean getEmptyBoolean = new ObservableBoolean();

    private MutableLiveData<Integer> handleTimeout = new MutableLiveData<>();

    private MutableLiveData<Integer> handleError = new MutableLiveData<>();

    private AppDatabase db = App.getInstance().getDatabase();
    private AccountDao accountDao = db.accountDao();

    private BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(3);
    private ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 1,
        TimeUnit.SECONDS, queue);
    private ConnectivityManager connManager = (ConnectivityManager)App.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
    private NetworkInfo activeNetwork = connManager.getActiveNetworkInfo();


    public void getChosenDiscipline(){
        loadRv.set(true);
        boolean onlyServer = sharedPreferences.getBoolean(App.getContext().getString(R.string.only_server),false);
        if(onlyServer){
            if (connManager.getActiveNetworkInfo() != null && connManager.getActiveNetworkInfo().isAvailable() && activeNetwork.isConnected()) {
                getChosenDisciplineListFromServer();
            }
        }else{
            MyTask task = new MyTask();
            task.execute();
        }
    }

    public void getChosenDisciplineListFromServer() {
        KaznituRetrofit.getApi().updateChosenDiscipline1().enqueue(new Callback<List<ChosenDiscipline1>>(){
            @Override
            public void onResponse(Call<List<ChosenDiscipline1>> call, Response<List<ChosenDiscipline1>> response) {
                if (response.code() == 200) {
                    loadRv.set(false);
                    chosenDisciplines = response.body();
                    Log.d("TEST", "onResponse: " + chosenDisciplines.toString());
                    if (!chosenDisciplines.equals(chosenDisciplinesFromDb)) {
                        new Thread(() -> {
                            update(chosenDisciplines);
                        }).start();
                        chosenDisciplinesLiveData.setValue(chosenDisciplines);
                    }
                }
                else if (response.code() == 404)
                    handleError.setValue(404);
                else if (response.code() == 400)
                    handleError.setValue(400);
                else if (response.code() == 401)
                    handleError.setValue(401);
                else if (response.code() == 500)
                    handleError.setValue(500);
            }
            @Override
            public void onFailure(Call<List<ChosenDiscipline1>> call, Throwable t) {
                exception();
            }
        });
    }


    private class MyTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            try{
                TimeUnit.SECONDS.sleep(1);
                if(!accountDao.getChosenDiscipline1().isEmpty()){
                    loadRv.set(false);
                    chosenDisciplinesFromDb = accountDao.getChosenDiscipline1();
                    chosenDisciplinesLiveData.postValue(chosenDisciplinesFromDb);
                    if (connManager.getActiveNetworkInfo() != null && connManager.getActiveNetworkInfo().isAvailable() && Objects.requireNonNull(activeNetwork).isConnected()) {
                        getChosenDisciplineListFromServer();
                    }
                }else {
                    // при первом вхождении
                    if (connManager.getActiveNetworkInfo() != null && connManager.getActiveNetworkInfo().isAvailable() && Objects.requireNonNull(activeNetwork).isConnected()) {
                        getChosenDisciplineListFromServer();
                    } else {
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

    private void update(List<ChosenDiscipline1> chosenDisciplines) {
        executor.execute(() -> {
            accountDao.deleteChosenDiscipline1();
            accountDao.insertChosenDiscipline1(chosenDisciplines);
        });
    }

    private void exception(){
        loadRv.set(false);
        handleTimeout.setValue(1);
    }

    public void registerPlayerId(){
        String playerId = OneSignal.getPermissionSubscriptionState().getSubscriptionStatus().getUserId();
        KaznituRetrofit.getApi().registerPlayerId(playerId,"android","4.0").enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    MutableLiveData<Integer> getHandleTimeout(){
        if (handleTimeout == null){
            handleTimeout = new MutableLiveData<>();
        }
        return handleTimeout;
    }

    MutableLiveData<List<ChosenDiscipline1>> getChosenDisciplinesData(){
        if(chosenDisciplines == null){
            chosenDisciplinesLiveData = new MutableLiveData<>();
        }
        return chosenDisciplinesLiveData;
    }

    MutableLiveData<Integer> getHandleError(){
        if(handleError == null){
            handleError = new MutableLiveData<>();
        }
        return handleError;
    }

}
