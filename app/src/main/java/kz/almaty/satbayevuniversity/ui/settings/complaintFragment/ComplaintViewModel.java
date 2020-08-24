package kz.almaty.satbayevuniversity.ui.settings.complaintFragment;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import kz.almaty.satbayevuniversity.utils.Storage;
import kz.almaty.satbayevuniversity.data.network.KaznituRetrofit;

import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ComplaintViewModel extends ViewModel {
    public ObservableField<String> complaintString = new ObservableField<>();
    private MutableLiveData<Integer> complaintMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> messageLD = new MutableLiveData<>();

//    public ObservableBoolean complaintPG = new ObservableBoolean();
//    public ObservableBoolean complaintReady = new ObservableBoolean();

    public void sendComplaint(){
        String complaint = complaintString.get();
        if ((complaint == null || complaint.isEmpty())) {
            messageLD.setValue(true);
        }  else{
            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("iin", Storage.getInstance().getUsername());
            requestBody.put("section", "");
            requestBody.put("text", complaintString.get());
            KaznituRetrofit.getApi().sendComplaint(requestBody).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()){
                        switch (response.code()){
                            case 200:
                                complaintMutableLiveData.setValue(200);
                                break;
                            case 404:
                                complaintMutableLiveData.setValue(404);
                                break;
                            case 400:
                                complaintMutableLiveData.setValue(400);
                                break;
                            case 500:
                                complaintMutableLiveData.setValue(500);
                                break;
                        }
                    }
                }
                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                }
            });
        }
    }

    MutableLiveData<Integer> getComplaintMutableLiveData() {
        if(complaintMutableLiveData == null){
            complaintMutableLiveData = new MutableLiveData<>();
        }
        return complaintMutableLiveData;
    }

    MutableLiveData<Boolean> getMessageLD(){
        return messageLD;
    }
}
