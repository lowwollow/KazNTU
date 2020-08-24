package kz.almaty.satbayevuniversity.ui.umkd;

import android.content.Context;
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
import kz.almaty.satbayevuniversity.data.entity.umkd.Umkd;
import kz.almaty.satbayevuniversity.data.network.KaznituRetrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UmkdViewModel extends ViewModel {

    public ObservableBoolean loadRv = new ObservableBoolean();

    private List<Umkd> umkdList = new ArrayList<>();
    private List<Umkd> umkdListDB = new ArrayList<>();

    public ObservableBoolean getEmptyBoolean = new ObservableBoolean();

    private MutableLiveData<List<Umkd>> umkdMutableLiveData = new MutableLiveData<>();

    private AppDatabase db = App.getInstance().getDatabase();
    private AccountDao accountDao = db.accountDao();

    private BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(3);
    private ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 1,
            TimeUnit.SECONDS, queue);

    private ConnectivityManager connManager = (ConnectivityManager)App.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
    private NetworkInfo activeNetwork = connManager.getActiveNetworkInfo();

    void getUmkd(){
        loadRv.set(true);
        executor.execute(() -> {
            if (!accountDao.getUmkd().isEmpty()) {
                umkdListDB = accountDao.getUmkd();
                umkdMutableLiveData.postValue(umkdListDB);
                loadRv.set(false);
                if (connManager.getActiveNetworkInfo() != null && connManager.getActiveNetworkInfo().isAvailable() && activeNetwork.isConnected()) {
                    getUmkdListFromServer();
                }
            }
            else {
                if (connManager.getActiveNetworkInfo() != null && connManager.getActiveNetworkInfo().isAvailable() && activeNetwork.isConnected()) {
                    getUmkdListFromServer();
                }else{
                    getEmptyBoolean.set(umkdList.isEmpty());
                    loadRv.set(false);
                }
             }
        });
    }
    public void getUmkdListFromServer(){
        KaznituRetrofit.getApi().updateInstructor().enqueue(new Callback<List<Umkd>>() {
            @Override
            public void onResponse(Call<List<Umkd>> call, Response<List<Umkd>> response) {
                if (response.isSuccessful()) {
                    umkdList = response.body();
                    getEmptyBoolean.set(umkdList.isEmpty());
                    loadRv.set(false);
                    if (!umkdList.equals(umkdListDB)) {
                        new Thread(() -> {
                            update(umkdList);
                        }).start();
                        umkdMutableLiveData.postValue(umkdList);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Umkd>> call, Throwable t) {
            }
        });
    }
    MutableLiveData<List<Umkd>> getUmkdMutableLiveData() {
        if(umkdMutableLiveData == null){
            umkdMutableLiveData = new MutableLiveData<>();
        }
        return umkdMutableLiveData;
    }

    private void update(List<Umkd> umkdList) {
        executor.execute(() -> {
            accountDao.deleteUmkd();
            accountDao.insertUmkd(umkdList);
        });
    }
}
