package kz.almaty.satbayevuniversity.ui.admission.master.additional_info;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import kz.almaty.satbayevuniversity.data.entity.admission.additional_info_master.AdditionalInfo;
import kz.almaty.satbayevuniversity.data.network.AdmissionRetrofit;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdditionalInfoViewModel extends ViewModel {
    MutableLiveData<AdditionalInfo> additionalInfoMutableLiveData;
    MutableLiveData<Bitmap> bitmapMutableLiveData;
    MutableLiveData<String> responseMutableLiveData;

    public void getAdditionalInfo(){
        AdmissionRetrofit.getApi(2).getAdditionalInfo().enqueue(new Callback<AdditionalInfo>() {
            @Override
            public void onResponse(Call<AdditionalInfo> call, Response<AdditionalInfo> response) {
                if(response.isSuccessful() && response.body() != null){
                    additionalInfoMutableLiveData.setValue(response.body());
                }
            }
            @Override
            public void onFailure(Call<AdditionalInfo> call, Throwable t) {
            }
        });
    }

    public void saveAdditionalInfo(AdditionalInfo additionalInfo){
        AdmissionRetrofit.getApi(2).saveAdditionalInfo(additionalInfo).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if(response.code() == 400){
                        try {
                            JSONObject jsonObject = new JSONObject(response.errorBody().string());
                            responseMutableLiveData.setValue(jsonObject.getString("message"));
                        } catch (JSONException | IOException e) {
                            e.printStackTrace();
                        }
                    }else if(response.isSuccessful()){
                        responseMutableLiveData.setValue("Данные успешно сохранено");
                    }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
    public MutableLiveData<AdditionalInfo> getAdditionalInfoMutableLiveData() {
        if(additionalInfoMutableLiveData == null){
            additionalInfoMutableLiveData = new MutableLiveData<>();
        }
        return additionalInfoMutableLiveData;
    }

    public  void getDocumentImage(String documentId,MutableLiveData<Bitmap> bitmapMutableLiveData){
        AdmissionRetrofit.getApi(2).getDocumentImage(documentId).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful() && response.body() != null){
                    Bitmap bitmap = BitmapFactory.decodeStream(response.body().byteStream());
                    bitmapMutableLiveData.setValue(bitmap);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public MutableLiveData<String> getResponseMutableLiveData() {
        if(responseMutableLiveData == null){
            responseMutableLiveData = new MutableLiveData<>();
        }
        return responseMutableLiveData;
    }
}
