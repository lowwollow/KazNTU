package kz.almaty.satbayevuniversity.ui.admission.registration;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableBoolean;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProviders;

import java.util.List;

import kz.almaty.satbayevuniversity.R;
import kz.almaty.satbayevuniversity.data.entity.admission.IdAndTitle;
import kz.almaty.satbayevuniversity.data.entity.admission.email_verification.Data;
import kz.almaty.satbayevuniversity.data.network.AdmissionRetrofit;
import kz.almaty.satbayevuniversity.databinding.ActivityRegistrationBinding;
import kz.almaty.satbayevuniversity.ui.admission.MainViewModel;

public class RegistrationActivity extends AppCompatActivity {
    public ObservableBoolean verificationLayout = new ObservableBoolean(false);
    ActivityRegistrationBinding activityRegistrationBinding;
    RegistrationViewModel emailVerificationViewModel;
    MainViewModel mainViewModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityRegistrationBinding = DataBindingUtil.setContentView(this,R.layout.activity_registration);
        emailVerificationViewModel = ViewModelProviders.of(this).get(RegistrationViewModel.class);
        Data data = new Data();
        activityRegistrationBinding.setData(data);
        activityRegistrationBinding.setHandler(this);
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        setCitizenCategory();
        setCountries();
        setMutableLiveDatas(data);

    }

    private void setCitizenCategory(){
        MutableLiveData<List<IdAndTitle>> citizenCategoryMutableLiveData = new MutableLiveData<>();
        mainViewModel.makeRetrofitRequest(AdmissionRetrofit.getApi(1).getCitizenCategory(),citizenCategoryMutableLiveData);
        citizenCategoryMutableLiveData.observe(this, strings -> {
            ArrayAdapter<IdAndTitle> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, strings);
            activityRegistrationBinding.citizenCategorySpinner.setAdapter(arrayAdapter);

        });
        activityRegistrationBinding.citizenCategorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                IdAndTitle idAndTitle = (IdAndTitle) activityRegistrationBinding.citizenCategorySpinner.getSelectedItem();
                switch (idAndTitle.getId()){
                    case 1:
                    case 3:
                        activityRegistrationBinding.citizenshipLayout.setVisibility(View.VISIBLE);
                        activityRegistrationBinding.setText(getResources().getString(R.string.iin));
                        break;
                    case 2:
                        activityRegistrationBinding.citizenshipLayout.setVisibility(View.GONE);
                        activityRegistrationBinding.setText(getResources().getString(R.string.iin));
                        break;
                    case 4:
                    case 5:
                        activityRegistrationBinding.citizenshipLayout.setVisibility(View.VISIBLE);
                        activityRegistrationBinding.setText(getResources().getString(R.string.document_number));
                        break;

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    private void setCountries(){
        MutableLiveData<List<IdAndTitle>> countryMutableLiveData = new MutableLiveData<>();
        mainViewModel.makeRetrofitRequest(AdmissionRetrofit.getApi(1).getCountries(),countryMutableLiveData);
        countryMutableLiveData.observe(this, strings -> {
            ArrayAdapter<IdAndTitle> arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,strings);
            activityRegistrationBinding.citizenCountriesSpinner.setAdapter(arrayAdapter);
        });
    }
    public void verifyEmail(Data data){
        int citizenCategoryId = ((IdAndTitle)activityRegistrationBinding.citizenCategorySpinner.getSelectedItem()).getId();
        int citizenshipId = ((IdAndTitle)activityRegistrationBinding.citizenCountriesSpinner.getSelectedItem()).getId();
        emailVerificationViewModel.verifyEmail(data,citizenCategoryId,citizenshipId);
    }
    private void setMutableLiveDatas(Data data){
        emailVerificationViewModel.getShowVerificationCodeInputDialog().observe(this, show -> {
            VerificationCodeDialog verificationCodeDialog = new VerificationCodeDialog(this,data);
            Fragment prev = getSupportFragmentManager().findFragmentByTag("verification_code_dialog");
            if(show){
                if(prev == null){
                    verificationCodeDialog.show(getSupportFragmentManager(),"verification_code_dialog");
                }
            }else{
                if(prev != null){
                    ((DialogFragment)prev).dismiss();
                }
            }
        });
        emailVerificationViewModel.getAccoutMutableLiveData().observe(this, account -> {
            YourLoginAndPasswordDialog yourLoginAndPasswordDialog = new YourLoginAndPasswordDialog(account);
            yourLoginAndPasswordDialog.show(getSupportFragmentManager(),"loginAndPasswordDialog");
        });
        emailVerificationViewModel.getErrorsMutableLiveData().observe(this, errors -> {
            for(String error : errors){
                Toast.makeText(this, error, Toast.LENGTH_LONG).show();
            }
        });
        emailVerificationViewModel.getErrorMutableLive().observe(this, error -> {
            Toast.makeText(this,error,Toast.LENGTH_LONG).show();
        });
    }
}
