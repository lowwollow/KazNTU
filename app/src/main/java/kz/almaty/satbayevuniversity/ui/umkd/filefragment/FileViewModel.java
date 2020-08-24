package kz.almaty.satbayevuniversity.ui.umkd.filefragment;

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
import kz.almaty.satbayevuniversity.data.entity.umkd.File;
import kz.almaty.satbayevuniversity.data.network.KaznituRetrofit;
import kz.almaty.satbayevuniversity.utils.Storage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FileViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<List<File>> fileMutableLiveData = new MutableLiveData<>();
    public ObservableBoolean getEmptyBoolean = new ObservableBoolean();
    private List<File> listOfFilesFromServer = new ArrayList<>();
    private List<File> listOfFilesFromDB = new ArrayList<>();

    public ObservableBoolean loadRv = new ObservableBoolean();
    private AppDatabase db = App.getInstance().getDatabase();
    private AccountDao accountDao = db.accountDao();

    private BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(3);
    private ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 1,
            TimeUnit.SECONDS, queue);
    private ConnectivityManager connManager = (ConnectivityManager)App.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
    private NetworkInfo activeNetwork = connManager.getActiveNetworkInfo();

    public  void getFiles(){
        loadRv.set(true);
        if(connManager.getActiveNetworkInfo() != null && connManager.getActiveNetworkInfo().isAvailable() && activeNetwork.isConnected()) {
            getFilesFromServer();
        }else{
            getEmptyBoolean.set(true);
            loadRv.set(false);
        }
    }

    void getFilesFromServer(){
        loadRv.set(true);
        KaznituRetrofit.getApi().updateFileCourse(
                Storage.getInstance().getCourseCode(),
                Storage.getInstance().getInstructorID())
                .enqueue(new Callback<List<File>>() {
                    @Override
                    public void onResponse(Call<List<File>> call, Response<List<File>> response) {
                        loadRv.set(false);
                        listOfFilesFromServer = response.body();
                        getEmptyBoolean.set(listOfFilesFromServer.isEmpty());
                        fileMutableLiveData.setValue(listOfFilesFromServer);
                    }
                    @Override
                    public void onFailure(Call<List<File>> call, Throwable t) {

                    }
                });
    }

    public MutableLiveData<List<File>> getFileMutableLiveData(){
        if(fileMutableLiveData == null){
            fileMutableLiveData = new MutableLiveData<>();
        }
        return fileMutableLiveData;
    }


}
