package kz.almaty.satbayevuniversity.ui.notification.pushNotification;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import kz.almaty.satbayevuniversity.data.App;
import kz.almaty.satbayevuniversity.data.entity.notification.PushNotification;
import kz.almaty.satbayevuniversity.data.network.KaznituRetrofit;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PushNotificationViewModel extends ViewModel {

    public ObservableBoolean loadRv = new ObservableBoolean();
    public ObservableBoolean isEmpty = new ObservableBoolean();
    MutableLiveData<List<PushNotification>> pushNotificationList = new MutableLiveData<>();
    List<PushNotification> listOfPushNotification = new ArrayList<>();

    private ConnectivityManager connManager = (ConnectivityManager) App.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
    private NetworkInfo activeNetwork = connManager.getActiveNetworkInfo();

    public void getPushNotification(){
        loadRv.set(true);
        isEmpty.set(false);
        if (connManager.getActiveNetworkInfo() != null && connManager.getActiveNetworkInfo().isAvailable() && activeNetwork.isConnected()) {
            getPushNotificationFromServer();
        }else{
            loadRv.set(false);
            isEmpty.set(true);
        }
    }
    private void getPushNotificationFromServer(){
        KaznituRetrofit.getApi().getPushNotificationList().enqueue(new Callback<List<PushNotification>>() {
            @Override
            public void onResponse(Call<List<PushNotification>> call, Response<List<PushNotification>> response) {
                listOfPushNotification = response.body();
                //Changed
                if (listOfPushNotification != null) {
                        isEmpty.set(listOfPushNotification.isEmpty());
                        pushNotificationList.setValue(listOfPushNotification);
                        loadRv.set(false);
                }

            }
            @Override
            public void onFailure(Call<List<PushNotification>> call, Throwable t) {
                loadRv.set(false);
            }
        });
    }
    public void removeItem(int position) {
        loadRv.set(true);
        KaznituRetrofit.getApi().removePushNotification(position).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                loadRv.set(false);
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                loadRv.set(false);
            }
        });
    }

    public LiveData<List<PushNotification>> getNotificationLiveData(){
        if(pushNotificationList == null){
            pushNotificationList = new MutableLiveData<>();
        }
        return pushNotificationList;
    }
}
