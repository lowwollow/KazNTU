package kz.almaty.satbayevuniversity.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.textfield.TextInputEditText;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import br.com.simplepass.loadingbutton.customViews.CircularProgressButton;
import io.sentry.core.Sentry;
import kz.almaty.satbayevuniversity.AuthViewModel;
import kz.almaty.satbayevuniversity.R;
import kz.almaty.satbayevuniversity.data.AccountDao;
import kz.almaty.satbayevuniversity.data.App;
import kz.almaty.satbayevuniversity.data.AppDatabase;
import kz.almaty.satbayevuniversity.data.entity.AccountEntity;
import kz.almaty.satbayevuniversity.databinding.ActivityLoginBinding;
import kz.almaty.satbayevuniversity.utils.Storage;


public class LoginActivity extends AppCompatActivity {
    public AuthViewModel authViewModel;
    private CircularProgressButton loginBtn;
    private boolean showPsw = false;
    private AppDatabase db = App.getInstance().getDatabase();
    private AccountDao accountDao = db.accountDao();
    ConnectivityManager connManager ;
    private TextInputEditText psw;
    private ImageView img;
    private BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(3);
    private ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 1,
            TimeUnit.SECONDS, queue);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityLoginBinding activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        authViewModel = ViewModelProviders.of(this).get(AuthViewModel.class);
        activityLoginBinding.setLifecycleOwner(this);
        activityLoginBinding.setViewModel(authViewModel);
        psw = activityLoginBinding.etPassword;
        img = activityLoginBinding.showPassword;

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPsw = !showPsw;
                showPassword(showPsw);
            }
        });
        showPassword(showPsw);


//        try {
//            throw new Exception("This is a test.");
//        } catch (Exception e) {
//            Sentry.captureException(e);
//        }

        loginBtn = activityLoginBinding.loginBtn;

        if (savedInstanceState == null) {
            authViewModel.initAuth();
        }

        authViewModel.getUserMutableLiveData().observe(this, accountEntity -> doIntent(accountEntity));

        authViewModel.toastGetMessage().observe(this, aBoolean -> {
            if (aBoolean) {
                Toast.makeText(this, "Заполните пустые поля", Toast.LENGTH_SHORT).show();
                revertBtn();
            }
        });

        authViewModel.getHandleError().observe(this, integer -> {
            switch (integer) {
                case 1:
                    Toast.makeText(this, "Ошибка соединения. Пожалуйста, попробуйте еще раз", Toast.LENGTH_SHORT).show();
                    revertBtn();
                    break;
                case 2:
                    Toast.makeText(this, "Ошибка", Toast.LENGTH_SHORT).show();
                    revertBtn();
                    break;
                case 3:
                    Toast.makeText(this, "Неизвестная ошибка", Toast.LENGTH_SHORT).show();
                    revertBtn();
                    break;
            }
        });

        authViewModel.getHandleError().observe(this, integer -> {
            switch (integer) {
                case 200:
                    loginBtn.doneLoadingAnimation(Color.parseColor("#6D67AE"),
                            BitmapFactory.decodeResource(getResources(), R.drawable.ic_done_white_48dp));
                    break;
                case 404:
                    Toast.makeText(this, "Not Found 404 Error", Toast.LENGTH_SHORT).show();
                    revertBtn();
                    break;
                case 400:
                    Toast.makeText(this, "Неверный логин или пароль", Toast.LENGTH_SHORT).show();
                    revertBtn();
                    break;
                case 500:
                    Toast.makeText(this, "Внутренняя ошибка сервера", Toast.LENGTH_SHORT).show();
                    revertBtn();
                    break;
            }
        });
        login();

    }

    private void login() {
        connManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        executor.execute(() -> {
            AccountEntity accountEntity = accountDao.getAccountEntity();
            if (accountEntity != null) {
                doIntent(accountEntity);
            } else {
                loginBtn.setOnClickListener(v -> {
                    if (connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                            connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                        loginBtn.startAnimation();
                        authViewModel.getInformation();
                    } else
                        Toast.makeText(this, "Отсутствует подключение к интернету", Toast.LENGTH_SHORT).show();
                    hideSoftKeyboard();
                });
            }
        });
    }
    @Override
    protected void onPostResume() {
        super.onPostResume();
        revertBtn();
    }

    private void showPassword(boolean isSet){
        if (isSet){
            psw.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }else{
            psw.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
        psw.setSelection(psw.getText().toString().length());
    }


    void doIntent(AccountEntity accountEntity){
        Storage.getInstance().setToken(accountEntity.getAccess_token());
        Storage.getInstance().setAccountFullName(accountEntity.getFullName());
        Storage.getInstance().setUsername(accountEntity.getUsername());
        Intent intent_name = new Intent(getApplicationContext(), HomeActivity.class);
        intent_name.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent_name);
        finish();
    }

    private void revertBtn(){
        loginBtn.revertAnimation();
    }


    protected void hideSoftKeyboard() {
        View view = this.getCurrentFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (view !=null){
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
    public void startAdmissionLoginActivity(View view){
        Intent intent = new Intent(this, kz.almaty.satbayevuniversity.ui.admission.login.LoginActivity.class);
        startActivity(intent);
    }
}