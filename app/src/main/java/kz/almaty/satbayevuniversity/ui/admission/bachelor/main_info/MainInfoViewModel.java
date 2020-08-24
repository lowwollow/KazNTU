package kz.almaty.satbayevuniversity.ui.admission.bachelor.main_info;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import kz.almaty.satbayevuniversity.R;
import kz.almaty.satbayevuniversity.data.App;
import kz.almaty.satbayevuniversity.data.entity.admission.registration.Account;
import kz.almaty.satbayevuniversity.data.entity.admission.main_info.MainInfo;
import kz.almaty.satbayevuniversity.data.network.AdmissionRetrofit;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainInfoViewModel extends ViewModel {
    MutableLiveData<MainInfo> mainInfoMutableLiveData;
    MutableLiveData<Bitmap> firstBitmapMutableLiveData;
    MutableLiveData<Bitmap> secondBitmapMutableLiveData;
    MutableLiveData<Account> accountMutableLiveData;
//    MutableLiveData<Bitmap> photoMutableLiveData;

    public void saveMainInfo(MainInfo mainInfo){
        Toast.makeText(App.getContext(),mainInfo.getPhoto().toString(),Toast.LENGTH_LONG).show();
        AdmissionRetrofit.getApi(2).saveMasterMainInfo(mainInfo).enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                if(response.isSuccessful()){
                }
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {

            }
        });
    }
    public void getMainInfo(){
        AdmissionRetrofit.getApi(2).getMasterMainInfo().enqueue(new Callback<MainInfo>() {
            @Override
            public void onResponse(Call<MainInfo> call, Response<MainInfo> response) {
                if(response.isSuccessful()){
                    if (response.body() != null) {
                    mainInfoMutableLiveData.setValue(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<MainInfo> call, Throwable t) {
            }
        });
    }



    public void getDocumentImage(String documentID,int which){
        AdmissionRetrofit.getApi(2).getDocumentImage(documentID).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    Bitmap bitmap = BitmapFactory.decodeStream(response.body().byteStream());
                    if(which==1)
                        firstBitmapMutableLiveData.setValue(bitmap);
                    else if(which == 2)
                        secondBitmapMutableLiveData.setValue(bitmap);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });
    }

    public MutableLiveData<MainInfo> getMainInfoMutableLiveData() {
        if(mainInfoMutableLiveData == null)
            mainInfoMutableLiveData = new MutableLiveData<>();
        return mainInfoMutableLiveData;
    }

    public MutableLiveData<Bitmap> getFirstBitmapMutableLiveData() {
        if(firstBitmapMutableLiveData==null){
            firstBitmapMutableLiveData = new MutableLiveData<>();
        }
        return firstBitmapMutableLiveData;
    }
    public MutableLiveData<Bitmap> getSecondBitmapMutableLiveData() {
        if(secondBitmapMutableLiveData==null){
            secondBitmapMutableLiveData = new MutableLiveData<>();
        }
        return secondBitmapMutableLiveData;
    }

    @BindingAdapter({"bitmap"})
    public static void loadImage(ImageView imageView, Bitmap bitmap){
        Glide.with(imageView.getContext())
                .setDefaultRequestOptions(new RequestOptions().centerInside())
                .load(bitmap)
                .placeholder(R.drawable.user_icon)
                .into(imageView);
    }

    public MutableLiveData<Account> getAccountMutableLiveData() {
        if(accountMutableLiveData == null){
            accountMutableLiveData = new MutableLiveData<>();
        }
        return accountMutableLiveData;
    }
}
