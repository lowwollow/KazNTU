package kz.almaty.satbayevuniversity.ui.admission.bachelor.education_info;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import kz.almaty.satbayevuniversity.data.entity.admission.education_info.EducationInfo;
import kz.almaty.satbayevuniversity.data.network.AdmissionRetrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EducationInfoViewModel extends ViewModel {
    MutableLiveData<EducationInfo> educationInfoMutableLiveData;

    public void getEducationInfo(){
        AdmissionRetrofit.getApi(2).getEducationInfo().enqueue(new Callback<EducationInfo>() {
            @Override
            public void onResponse(Call<EducationInfo> call, Response<EducationInfo> response) {
                if(response.isSuccessful()){
                    educationInfoMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<EducationInfo> call, Throwable t) {
                
            }
        });
    }

    public MutableLiveData<EducationInfo> getEducationInfoMutableLiveData() {
        if(educationInfoMutableLiveData == null)
            educationInfoMutableLiveData = new MutableLiveData<>();
        return educationInfoMutableLiveData;
    }
}
