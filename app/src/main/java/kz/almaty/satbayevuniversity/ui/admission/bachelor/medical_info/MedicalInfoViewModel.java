package kz.almaty.satbayevuniversity.ui.admission.bachelor.medical_info;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import kz.almaty.satbayevuniversity.R;
import kz.almaty.satbayevuniversity.data.App;
import kz.almaty.satbayevuniversity.data.entity.admission.medical_info.MedicalInfo;
import kz.almaty.satbayevuniversity.data.network.AdmissionRetrofit;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MedicalInfoViewModel extends ViewModel {
    MutableLiveData<MedicalInfo> medicalInfoMutableLiveData;
    MutableLiveData<String> responseMutableLiveData;
    public void getMedicalInfo(){
        AdmissionRetrofit.getApi(2).getMedicalInfo().enqueue(new Callback<MedicalInfo>() {
            @Override
            public void onResponse(Call<MedicalInfo> call, Response<MedicalInfo> response) {
                if(response.isSuccessful()){
                    medicalInfoMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<MedicalInfo> call, Throwable t) {
            }
        });
    }

    public void saveMedicalInfo(MedicalInfo medicalInfo){
        AdmissionRetrofit.getApi(2).saveMedicalInfo(medicalInfo).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    responseMutableLiveData.setValue("Данные успешно сохранены");
                }else if(response.code() == 200){
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
    public MutableLiveData<MedicalInfo> getMedicalInfoMutableLiveData() {
        if(medicalInfoMutableLiveData == null)
            medicalInfoMutableLiveData = new MutableLiveData<>();
        return medicalInfoMutableLiveData;
    }

    public MutableLiveData<String> getResponseMutableLiveData() {
        if(responseMutableLiveData == null){
            responseMutableLiveData = new MutableLiveData<>();
        }
        return responseMutableLiveData;
    }
}
