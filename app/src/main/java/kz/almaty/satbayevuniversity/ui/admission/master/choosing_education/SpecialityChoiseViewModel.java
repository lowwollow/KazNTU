package kz.almaty.satbayevuniversity.ui.admission.master.choosing_education;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import kz.almaty.satbayevuniversity.data.entity.admission.choosing_education_master.SpecialityChoise;
import kz.almaty.satbayevuniversity.data.network.AdmissionRetrofit;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SpecialityChoiseViewModel extends ViewModel {
    MutableLiveData<SpecialityChoise> specialityChoiseMutableLiveData;
    MutableLiveData<Bitmap> bitmapMutableLiveData;

    public void getSpecialityChoises(){
        AdmissionRetrofit.getApi(2).getSpecialityChoise().enqueue(new Callback<SpecialityChoise>() {
            @Override
            public void onResponse(Call<SpecialityChoise> call, Response<SpecialityChoise> response) {
                if(response.isSuccessful() && response.body() !=null){
                    specialityChoiseMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<SpecialityChoise> call, Throwable t) {

            }
        });
    }

    public MutableLiveData<SpecialityChoise> getSpecialityChoiseMutableLiveData() {
        if(specialityChoiseMutableLiveData == null){
            specialityChoiseMutableLiveData = new MutableLiveData<>();
        }
        return specialityChoiseMutableLiveData;
    }

    public void getDocumentImage(String documentId){
        AdmissionRetrofit.getApi(2).getDocumentImage(documentId).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful() && response.body() !=null){
                    Bitmap bitmap = BitmapFactory.decodeStream(response.body().byteStream());
                    bitmapMutableLiveData.setValue(bitmap);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });
    }

    public MutableLiveData<Bitmap> getBitmapMutableLiveData() {
        if(bitmapMutableLiveData == null){
            bitmapMutableLiveData = new MutableLiveData<>();
        }
        return bitmapMutableLiveData;
    }
}
