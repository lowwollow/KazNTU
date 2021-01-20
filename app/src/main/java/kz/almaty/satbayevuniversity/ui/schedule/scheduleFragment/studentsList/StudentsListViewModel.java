package kz.almaty.satbayevuniversity.ui.schedule.scheduleFragment.studentsList;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import kz.almaty.satbayevuniversity.R;
import kz.almaty.satbayevuniversity.data.App;
import kz.almaty.satbayevuniversity.data.entity.schedule.Student;
import kz.almaty.satbayevuniversity.data.network.KaznituRetrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentsListViewModel extends ViewModel {
    private ConnectivityManager connManager = (ConnectivityManager) App.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
    private NetworkInfo activeNetwork = connManager.getActiveNetworkInfo();
    public ObservableBoolean loadRv = new ObservableBoolean();
    MutableLiveData<List<Student>> liveData = new MutableLiveData<>();

    public void getStudentList(int classid, String language){
        if(connManager.getActiveNetworkInfo() != null && connManager.getActiveNetworkInfo().isAvailable() && activeNetwork.isConnected()) {
            getStudentListFromServer(classid,language);
        }else{
            Toast.makeText(App.getContext(), R.string.internetConnection, Toast.LENGTH_SHORT).show();
        }
    }
    private void getStudentListFromServer(int classid, String language){
        loadRv.set(true);
        KaznituRetrofit.getApi().getStudentList(classid,language).enqueue(new Callback<List<Student>>() {
            @Override
            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                Log.d("TESTING", "onResponse: " + response.body());
                if(response.isSuccessful()){
                    loadRv.set(false);
                    liveData.setValue(response.body());
                }
            }
            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {
                loadRv.set(false);
            }
        });
    }

    public MutableLiveData<List<Student>> getLiveData() {
        return liveData;
    }

}
