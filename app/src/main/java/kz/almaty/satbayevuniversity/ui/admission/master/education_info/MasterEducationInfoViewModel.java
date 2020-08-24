package kz.almaty.satbayevuniversity.ui.admission.master.education_info;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import kz.almaty.satbayevuniversity.data.entity.admission.education_info_master.EducationInfoMaster;
import kz.almaty.satbayevuniversity.data.network.AdmissionRetrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MasterEducationInfoViewModel extends ViewModel {
    MutableLiveData<EducationInfoMaster> educationInfoMasterMutableLiveData;

    public void getEducationInfoMaster(){
        AdmissionRetrofit.getApi(2).getEducationInfoMaster().enqueue(new Callback<EducationInfoMaster>() {
            @Override
            public void onResponse(Call<EducationInfoMaster> call, Response<EducationInfoMaster> response) {
                if(response.isSuccessful() && response.body() != null){
                    educationInfoMasterMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<EducationInfoMaster> call, Throwable t) {
            }
        });
    }

    public MutableLiveData<EducationInfoMaster> getEducationInfoMasterMutableLiveData() {
        if(educationInfoMasterMutableLiveData == null){
            educationInfoMasterMutableLiveData = new MutableLiveData<>();
        }
        return educationInfoMasterMutableLiveData;
    }
}
