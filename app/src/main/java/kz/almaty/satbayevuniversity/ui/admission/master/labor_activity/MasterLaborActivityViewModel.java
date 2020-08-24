package kz.almaty.satbayevuniversity.ui.admission.master.labor_activity;

import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import kz.almaty.satbayevuniversity.data.App;
import kz.almaty.satbayevuniversity.data.entity.admission.labor_activity_master.LaborActivity;
import kz.almaty.satbayevuniversity.data.network.AdmissionRetrofit;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MasterLaborActivityViewModel extends ViewModel {
    MutableLiveData<LaborActivity> laborActivityMutableLiveData;
    MutableLiveData<String> responseMutableLiveData;
    public void getLaborActivity(){
        AdmissionRetrofit.getApi(2).getLaborActivity().enqueue(new Callback<LaborActivity>() {
            @Override
            public void onResponse(Call<LaborActivity> call, Response<LaborActivity> response) {
                if(response.isSuccessful() && response.body() !=null){
                    laborActivityMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<LaborActivity> call, Throwable t) {
            }
        });
    }
    public void saveLaborActivity(LaborActivity laborActivity){
        AdmissionRetrofit.getApi(2).saveLaborActivity(laborActivity).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.code() == 400){
                    try {
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        responseMutableLiveData.setValue(jsonObject.getString("message"));
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    responseMutableLiveData.setValue("Данные успешно сохранены !");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
    public MutableLiveData<LaborActivity> getLaborActivityMutableLiveData() {
        if(laborActivityMutableLiveData == null){
            laborActivityMutableLiveData = new MutableLiveData<>();
        }
        return laborActivityMutableLiveData;
    }

    public MutableLiveData<String> getResponseMutableLiveData() {
        if(responseMutableLiveData == null){
            responseMutableLiveData = new MutableLiveData<>();
        }
        return responseMutableLiveData;
    }
}
