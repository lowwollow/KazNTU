package kz.almaty.satbayevuniversity.ui.admission.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.snackbar.Snackbar;

import java.util.Locale;

import kz.almaty.satbayevuniversity.R;
import kz.almaty.satbayevuniversity.data.App;
import kz.almaty.satbayevuniversity.databinding.ActivityAdmissionLoginBinding;
import kz.almaty.satbayevuniversity.ui.admission.bachelor.BachelorActivity;
import kz.almaty.satbayevuniversity.ui.admission.registration.LevelDialog;
import kz.almaty.satbayevuniversity.ui.admission.doctor.DoctorActivity;
import kz.almaty.satbayevuniversity.ui.admission.master.MasterActivity;
import kz.almaty.satbayevuniversity.ui.settings.languageFragment.LanguageFragment;
import kz.almaty.satbayevuniversity.utils.LocaleHelper;

public class LoginActivity extends AppCompatActivity {
    LevelDialog levelDialogFragment;
    LoginViewModel loginViewModel;
    ActivityAdmissionLoginBinding activityAdmissionLoginBinding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAdmissionLoginBinding = DataBindingUtil.setContentView(this,R.layout.activity_admission_login);
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        activityAdmissionLoginBinding.setViewModel(loginViewModel);
        activityAdmissionLoginBinding.loginBtn.setOnClickListener(v -> {
            loginViewModel.login();
        });
        startActivityAfterSuccsessfullyLogin();
        languageChanged();

        SharedPreferences sharedPreferences = App.getContext().getSharedPreferences("language", Context.MODE_PRIVATE);
        String language = sharedPreferences.getString("language","ru");
        setLocale(language);
    }
    public void startChoosingLevel(View view){
        levelDialogFragment = new LevelDialog();
        levelDialogFragment.show(getSupportFragmentManager(),"degreeDialogFragment");
    }
    private void startActivityAfterSuccsessfullyLogin(){
        loginViewModel.getIsAuthenticated().observe(this, aBoolean -> {
            if(aBoolean){
                Intent i;
                if(loginViewModel.username.get().charAt(loginViewModel.username.get().length()-1) == 'D'){
                    i = new Intent(this, DoctorActivity.class);
                }else if(loginViewModel.username.get().charAt(loginViewModel.username.get().length()-1) == 'M'){
                    i = new Intent(this, MasterActivity.class);
                }else{
                    i = new Intent(this, BachelorActivity.class);
                }
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });
    }
    private void languageChanged(){
        loginViewModel.getLanguageChanged().observe(this, languageCode -> {
            setLocale(languageCode);
            restartActivity();
        });
    }
    public void setLocale(String lang) {
        lang = (lang.equals("kz") ? "kk" : lang);
        Locale myLocale = new Locale(lang);
        getResources().getConfiguration().setLocale(myLocale);
        getResources().updateConfiguration(getResources().getConfiguration(),getResources().getDisplayMetrics());
    }
    public void restartActivity(){
        startActivity(getIntent());
        finish();
        overridePendingTransition(0, 0);
    }
}
