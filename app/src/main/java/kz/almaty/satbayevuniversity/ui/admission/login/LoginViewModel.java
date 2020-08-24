package kz.almaty.satbayevuniversity.ui.admission.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ObservableField;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import kz.almaty.satbayevuniversity.data.App;
import kz.almaty.satbayevuniversity.data.entity.admission.LoginResponse;
import kz.almaty.satbayevuniversity.data.entity.admission.User;
import kz.almaty.satbayevuniversity.data.entity.admission.UserInfo;
import kz.almaty.satbayevuniversity.data.network.AdmissionRetrofit;
import kz.almaty.satbayevuniversity.utils.Storage;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {
    public ObservableField<String> password = new ObservableField<>();
    public ObservableField<String> username = new ObservableField<>();
    private MutableLiveData<Bitmap> drawableMutableLiveData;
    private MutableLiveData<UserInfo> userInfoMutableLiveData;
    private MutableLiveData<Boolean> isAuthenticated;
    private MutableLiveData<String> languageChanged;


    public void login() {
            User user = new User(username.get(), password.get());
            AdmissionRetrofit.getApi(2).login(user).enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, final Response<LoginResponse> response) {
                    if (response.isSuccessful()) {
                        if(response.body().getRedirect()){
                            List<String> listOfCookies = response.headers().values("Set-Cookie");
                            if(!listOfCookies.isEmpty()){
                                Storage.getInstance().setCookies(listOfCookies.get(2));
                                isAuthenticated.setValue(true);
                            }
                        }else {
                            isAuthenticated.setValue(false);
                            Toast.makeText(App.getContext(), response.body().getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {

                }
            });
    }
    public void getImageUrl(){
        AdmissionRetrofit.getApi(2).getPhoto().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.body() != null){
                    Bitmap bmp = BitmapFactory.decodeStream(response.body().byteStream());
                    drawableMutableLiveData.setValue(bmp);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });
    }

    public void getUserInfo(){
        AdmissionRetrofit.getApi(1).getUserInfo().enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                if(response.isSuccessful()){
                    userInfoMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {

            }
        });
    }


    public void changeLang(String language){
        SharedPreferences.Editor editor = App.getContext().getSharedPreferences("language", Context.MODE_PRIVATE).edit();
        editor.putString("language",language);
        editor.apply();

        languageChanged.postValue(language);
    }
    public MutableLiveData<Bitmap> getBitmap(){
        if (drawableMutableLiveData == null){
            drawableMutableLiveData = new MutableLiveData<>();
        }
        return drawableMutableLiveData;
    }

    public MutableLiveData<UserInfo> getUserInfoMutableLiveData() {
        if(userInfoMutableLiveData == null){
            userInfoMutableLiveData = new MutableLiveData<>();
        }
        return userInfoMutableLiveData;
    }

    public MutableLiveData<Boolean> getIsAuthenticated() {
        if(isAuthenticated==null){
            isAuthenticated = new MutableLiveData<>();
        }
        return isAuthenticated;
    }
    public MutableLiveData<String> getLanguageChanged(){
        if(languageChanged == null){
            languageChanged = new MutableLiveData<>();
        }
        return languageChanged;
    }

}
