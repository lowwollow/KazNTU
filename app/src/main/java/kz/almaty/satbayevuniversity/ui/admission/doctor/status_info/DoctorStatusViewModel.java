package kz.almaty.satbayevuniversity.ui.admission.doctor.status_info;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import kz.almaty.satbayevuniversity.data.entity.admission.status_info_master.MasterStatusInfo;
import kz.almaty.satbayevuniversity.data.network.AdmissionRetrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoctorStatusViewModel extends ViewModel {
    private MutableLiveData<MasterStatusInfo> masterStatusInfoMutableLiveData;
    public void getMasterStatusInfo(){
        AdmissionRetrofit.getApi(2).getMasterStatusInfo().enqueue(new Callback<MasterStatusInfo>() {
            @Override
            public void onResponse(Call<MasterStatusInfo> call, Response<MasterStatusInfo> response) {
                if(response.body() != null && response.isSuccessful()){
                    masterStatusInfoMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<MasterStatusInfo> call, Throwable t) {
            }
        });
    }

    public MutableLiveData<MasterStatusInfo> getMasterStatusInfoMutableLiveData() {
        if(masterStatusInfoMutableLiveData == null){
            masterStatusInfoMutableLiveData = new MutableLiveData<>();
        }
        return masterStatusInfoMutableLiveData;
    }
}
