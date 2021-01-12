package kz.almaty.satbayevuniversity.ui.individualPlan.chosenDisciplines;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import kz.almaty.satbayevuniversity.data.AccountDao;
import kz.almaty.satbayevuniversity.data.App;
import kz.almaty.satbayevuniversity.data.AppDatabase;
import kz.almaty.satbayevuniversity.data.entity.individualPlan.choosenDiscipline.ChosenDiscipline1;
import kz.almaty.satbayevuniversity.data.entity.individualPlan.choosenDiscipline.ChosenDisciplineGroup1;
import kz.almaty.satbayevuniversity.data.entity.schedule.Schedule;
import kz.almaty.satbayevuniversity.data.network.KaznituRetrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChosenDisciplineViewModel extends ViewModel {
    private static final String TAG = "ChosenDisciplineViewModel";
    SharedPreferences sharedPreferences = App.getContext().getSharedPreferences("shared_preferences", Context.MODE_PRIVATE);
    private List<ChosenDisciplineGroup1> chosenDisciplines = new ArrayList<>();
    private List<ChosenDisciplineGroup1> chosenDisciplinesFromDb = new ArrayList<>();
    private MutableLiveData<List<ChosenDisciplineGroup1>> chosenDisciplinesLiveData = new MutableLiveData<>();

    public ObservableBoolean loadRv = new ObservableBoolean();

    private MutableLiveData<Integer> handleTimeout = new MutableLiveData<>();

    private MutableLiveData<Integer> handleError = new MutableLiveData<>();

    private AppDatabase db = App.getInstance().getDatabase();
    private AccountDao accountDao = db.accountDao();

    private BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(3);
    private ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 1,
        TimeUnit.SECONDS, queue);
    private ConnectivityManager connManager = (ConnectivityManager)App.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
    private NetworkInfo activeNetwork = connManager.getActiveNetworkInfo();


    private void getScheduleListFromServer(){

        KaznituRetrofit.getApi().updateChosenDiscipline().enqueue(new Callback<List<ChosenDisciplineGroup1>>() {

            @Override
            public void onResponse(Call<List<ChosenDisciplineGroup1>> call, Response<List<ChosenDisciplineGroup1>> response) {
                switch (response.code()) {
                    case 200:
                        loadRv.set(false);
                        chosenDisciplines = response.body();
                        //if(!chosenDisciplines.equals(chosenDisciplinesFromDb)){
                            new Thread(() -> {
                                update(chosenDisciplines);
                            }).start();
                            chosenDisciplinesLiveData.setValue(chosenDisciplines);
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
            public void onFailure(Call<List<ChosenDisciplineGroup1>> call, Throwable t) {

            }
        });
    }

    private void update(List<ChosenDisciplineGroup1> chosenDisciplines) {
        executor.execute(() -> {
            accountDao.deleteChosenDiscipline1();
            accountDao.insertChosenDiscipline1(chosenDisciplines);
        });
    }

}
