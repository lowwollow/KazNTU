package kz.almaty.satbayevuniversity.ui.admission.master.achievement_info;

import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import kz.almaty.satbayevuniversity.data.entity.admission.achievement_info_master.AchievementInfo;
import kz.almaty.satbayevuniversity.data.network.AdmissionRetrofit;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MasterAchievementInfoViewModel extends ViewModel {
    MutableLiveData<AchievementInfo> achievementInfoMutableLiveData;
    MutableLiveData<String> responseMutableLiveData;

    public void getAchievementInfo(){
        AdmissionRetrofit.getApi(2).getAchievementInfo().enqueue(new Callback<AchievementInfo>() {
            @Override
            public void onResponse(Call<AchievementInfo> call, Response<AchievementInfo> response) {
                if(response.isSuccessful() && response.body() != null){
                    achievementInfoMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<AchievementInfo> call, Throwable t) {
            }
        });
    }

    public void saveAchievementInfo(AchievementInfo achievementInfo){
        AdmissionRetrofit.getApi(2).saveAchievementInfo(achievementInfo).enqueue(new Callback<ResponseBody>() {
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
                    responseMutableLiveData.setValue("Данные успешно сохранены");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
    public MutableLiveData<AchievementInfo> getAchievementInfoMutableLiveData() {
        if(achievementInfoMutableLiveData == null){
            achievementInfoMutableLiveData = new MutableLiveData<>();
        }
        return achievementInfoMutableLiveData;
    }

    public MutableLiveData<String> getResponseMutableLiveData() {
        if(responseMutableLiveData == null){
            responseMutableLiveData = new MutableLiveData<>();
        }
        return responseMutableLiveData;
    }

    @BindingAdapter("android:text")
    public static void setText(TextView view, Integer value) {
        if (value == null)
            return;
        view.setText(String.valueOf(value));
    }
    @InverseBindingAdapter(attribute = "android:text", event = "android:textAttrChanged")
    public static Integer getTextString(TextView view) {
        return Integer.valueOf(view.getText().toString());
    }
}
