package kz.almaty.satbayevuniversity.ui.admission.bachelor.residence_info;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import kz.almaty.satbayevuniversity.data.entity.admission.residence_info.ResidenceInfo;
import kz.almaty.satbayevuniversity.data.network.AdmissionRetrofit;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResidenceInfoViewModel extends ViewModel {
    MutableLiveData<ResidenceInfo> residenceInfoMutableLiveData;
    MutableLiveData<String> responseMutableLiveData;
    public void getResidenceInfo(){
        AdmissionRetrofit.getApi(2).getResidenceInfo().enqueue(new Callback<ResidenceInfo>() {
            @Override
            public void onResponse(Call<ResidenceInfo> call, Response<ResidenceInfo> response) {
                if(response.isSuccessful()){
                    residenceInfoMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ResidenceInfo> call, Throwable t) {
            }
        });
    }

    public void saveResidenceInfo(ResidenceInfo residenceInfo){
        AdmissionRetrofit.getApi(2).saveResidenceInfo(residenceInfo).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    responseMutableLiveData.setValue("Данные успешно сохранены");
                }else if(response.code() == 400){
                    try {
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        responseMutableLiveData.setValue(jsonObject.getString("message"));
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public MutableLiveData<ResidenceInfo> getResidenceInfoMutableLiveData() {
        if(residenceInfoMutableLiveData == null)
            residenceInfoMutableLiveData = new MutableLiveData<>();
        return residenceInfoMutableLiveData;
    }

    public MutableLiveData<String> getResponseMutableLiveData() {
        if(responseMutableLiveData == null){
            responseMutableLiveData = new MutableLiveData<>();
        }
        return responseMutableLiveData;
    }
}
