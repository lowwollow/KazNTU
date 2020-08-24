package kz.almaty.satbayevuniversity.ui.admission.bachelor.choosing_education;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import kz.almaty.satbayevuniversity.data.entity.admission.main_info.MainInfo;
import kz.almaty.satbayevuniversity.data.network.AdmissionRetrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChoosingEducationViewModel extends ViewModel {
    MutableLiveData<MainInfo> mainInfoMutableLiveData;

    public void getMainInfo(){
//        AdmissionRetrofit.getApi(2).getMainInfo().enqueue(new Callback<MainInfo>() {
//            @Override
//            public void onResponse(Call<MainInfo> call, Response<MainInfo> response) {
//                if(response.isSuccessful()){
//                    mainInfoMutableLiveData.setValue(response.body());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<MainInfo> call, Throwable t) {
//            }
//        });
    }

    public MutableLiveData<MainInfo> getMainInfoMutableLiveData() {
        if(mainInfoMutableLiveData == null)
            mainInfoMutableLiveData = new MutableLiveData<>();
        return mainInfoMutableLiveData;
    }
}
