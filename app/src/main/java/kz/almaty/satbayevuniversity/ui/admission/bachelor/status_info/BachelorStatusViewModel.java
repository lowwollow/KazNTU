package kz.almaty.satbayevuniversity.ui.admission.bachelor.status_info;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import kz.almaty.satbayevuniversity.data.entity.admission.status_info.EntrantStatusInfo;
import kz.almaty.satbayevuniversity.data.network.AdmissionRetrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BachelorStatusViewModel extends ViewModel {
    MutableLiveData<EntrantStatusInfo> statusInfoMutableLiveData;

    public void getStatusInfo(){
        AdmissionRetrofit.getApi(2).getBachelorStatusInfo().enqueue(new Callback<EntrantStatusInfo>() {
            @Override
            public void onResponse(Call<EntrantStatusInfo> call, Response<EntrantStatusInfo> response) {
                if(response.isSuccessful() && response.body() != null){
                    statusInfoMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<EntrantStatusInfo> call, Throwable t) {
            }
        });
    }

    public MutableLiveData<EntrantStatusInfo> getStatusInfoMutableLiveData() {
        if(statusInfoMutableLiveData == null)
            statusInfoMutableLiveData = new MutableLiveData<>();
        return statusInfoMutableLiveData;
    }
}
