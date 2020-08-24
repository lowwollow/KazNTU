package kz.almaty.satbayevuniversity.ui.admission.registration;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import kz.almaty.satbayevuniversity.data.App;
import kz.almaty.satbayevuniversity.data.entity.admission.email_verification.Data;
import kz.almaty.satbayevuniversity.data.entity.admission.email_verification.VerifyEmail;
import kz.almaty.satbayevuniversity.data.entity.admission.registration.Account;
import kz.almaty.satbayevuniversity.data.entity.admission.registration.Registration;
import kz.almaty.satbayevuniversity.data.network.AdmissionRetrofit;
import kz.almaty.satbayevuniversity.utils.Storage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationViewModel extends ViewModel {
    MutableLiveData<Boolean> showVerificationCodeInputDialog;
    MutableLiveData<Account> accountMutableLiveData;
    MutableLiveData<List<String>> errorsMutableLiveData;
    MutableLiveData<String> errorMutableLive;
    public void verifyEmail(Data data,int citizenCategoryId, int citizenshipId){
        SharedPreferences sharedPreferences = App.getContext().getSharedPreferences("registration",Context.MODE_PRIVATE);
        int levelId = sharedPreferences.getInt("levelId",1);
        Call verify= AdmissionRetrofit.getApi(2).verifyEmailBachelor(data);
        Registration registration = new Registration();
        registration.setLogin(data.getIin());
        registration.setEmail(data.getEmail());
        registration.setCitizenCategory(citizenCategoryId);
        registration.setCitizenship(citizenshipId);
        Call register = AdmissionRetrofit.getApi(2).registerBachelor(registration);
        switch (levelId){
            case 1:
                verify = AdmissionRetrofit.getApi(2).verifyEmailBachelor(data);
                register = AdmissionRetrofit.getApi(2).registerBachelor(registration);
                break;
            case 2:
                verify = AdmissionRetrofit.getApi(2).verifyEmailMaster(data);
                register = AdmissionRetrofit.getApi(2).registerMaster(registration);
                break;
            case 3:
                verify = AdmissionRetrofit.getApi(2).verifyEmailDoctor(data);
                register = AdmissionRetrofit.getApi(2).registerDoctor(registration);
                break;
        }
        Call finalRegister = register;
        verify.enqueue(new Callback<VerifyEmail>() {
            @Override
            public void onResponse(Call<VerifyEmail> call, Response<VerifyEmail> response) {
                if(response.code() == 500){
                    try {
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        errorMutableLive.setValue(jsonObject.getString("message"));
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }else if(response.isSuccessful() && response.body() !=null){
                    if(response.body().getErrors() != null){
                        if(!response.body().getErrors().isEmpty()){
                            errorsMutableLiveData.setValue(response.body().getErrors());
                        }
                    }
                    if(response.body().getData().getIsVerified()){
                        register(finalRegister);
                        showVerificationCodeInputDialog.setValue(false);
                    }else{
                        showVerificationCodeInputDialog.setValue(true);
                    }
                }
            }
            @Override
            public void onFailure(Call<VerifyEmail> call, Throwable t) {

            }
        });
    }
    public void register(Call call){
        call.enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                if(response.isSuccessful() && response.body() != null){
                    List<String> listOfCookies = response.headers().values("Set-Cookie");
                    if(!listOfCookies.isEmpty()) {
                        Storage.getInstance().setCookies(listOfCookies.get(2));
                    }
                    accountMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {

            }
        });
    }

    public MutableLiveData<Boolean> getShowVerificationCodeInputDialog() {
        if(showVerificationCodeInputDialog == null){
            showVerificationCodeInputDialog = new MutableLiveData<>();
        }
        return showVerificationCodeInputDialog;
    }

    public MutableLiveData<Account> getAccoutMutableLiveData() {
        if(accountMutableLiveData == null){
            accountMutableLiveData = new MutableLiveData<>();
        }
        return accountMutableLiveData;
    }

    public MutableLiveData<List<String>> getErrorsMutableLiveData() {
        if(errorsMutableLiveData == null){
            errorsMutableLiveData = new MutableLiveData<>();
        }
        return errorsMutableLiveData;
    }

    public MutableLiveData<String> getErrorMutableLive() {
        if(errorMutableLive == null){
            errorMutableLive = new MutableLiveData<>();
        }
        return errorMutableLive;
    }
}
