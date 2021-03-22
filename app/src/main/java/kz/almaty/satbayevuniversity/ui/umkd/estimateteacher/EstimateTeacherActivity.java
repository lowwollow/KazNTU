package kz.almaty.satbayevuniversity.ui.umkd.estimateteacher;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;

import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import kz.almaty.satbayevuniversity.R;
import kz.almaty.satbayevuniversity.data.SharedPrefCache;
import kz.almaty.satbayevuniversity.data.User;
import kz.almaty.satbayevuniversity.data.network.KaznituRetrofit;
import kz.almaty.satbayevuniversity.ui.umkd.UmkdAdapter;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EstimateTeacherActivity extends AppCompatActivity {
    private Context context;
    private Toolbar toolbar;
    public EstimateTeacherActivity getInstance() {return new EstimateTeacherActivity();}
    private Button btn;
    private SharedPreferences sPref;
    MutableLiveData<List<InstructorBody>> temp = new MutableLiveData<>();
    public static String description = "";
    public static double rating = 0.0;
    public EstimateTeacherActivity(){}

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this, R.layout.estimate_teacher);
        toolbar = findViewById(R.id.toolbar);
        setToolbar();
        setTitleName();
        btn = findViewById(R.id.button_ok);
        getTextFrom();
        toolbar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });

    }


    @Override
    protected void onStart() {
        super.onStart();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData();
            }
        });
    }

    private void setTitleName(){
        UmkdAdapter adapter = new UmkdAdapter();
        String teacher_name = adapter.getUmkd().getInstructorName();
        TextView name = findViewById(R.id.name);
        name.setText(teacher_name);
    }

    private void setToolbar(){
        this.setSupportActionBar(toolbar);
        this.getSupportActionBar().setHomeAsUpIndicator(R.drawable.close_file_icon);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setTitle(R.string.rate_teacher);
    }

    private String getTextFrom(){
        EditText editText = findViewById(R.id.editText);
        return editText.getText().toString();
    }

    private double getScore(){
        RatingBar ratingBar = findViewById(R.id.ratingBar);
        return ratingBar.getRating();
    }

    private boolean isAnonym(){
        SwitchMaterial switchMaterial = findViewById(R.id.switchMaterial);
        return switchMaterial.isChecked();
    }

    private void sendData(){
        UmkdAdapter adapter = new UmkdAdapter();
        int instructorId = adapter.getUmkd().getInstructorId();
        String instructorName = adapter.getUmkd().getInstructorName();
        postRating(instructorId,instructorName);
        //getRating(instructorId);
        endActivity();
    }

    private void endActivity(){
        finish();
    }


    private void postRating(int instructorId, String instructorName){
        String comment = getTextFrom();
        double rating = getScore();
        boolean isAnonym = isAnonym();
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("instructorId", instructorId);
        requestBody.put("instructorName", instructorName);
        requestBody.put("rating", rating);
        requestBody.put("description", comment);
        requestBody.put("isAnonimous", isAnonym);
//        Log.d("id", "postRating: " + instructorId);
        KaznituRetrofit.getApi().sendRating(requestBody).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Спасибо за отзыв!",Toast.LENGTH_LONG ).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("fail", "onFailure: " + call);
            }
        });
    }

    public void getRating(int instructorId){
        Call<List<InstructorBody>> callAsync = KaznituRetrofit.getApi().getRating(instructorId);
        callAsync.enqueue(new Callback<List<InstructorBody>>() {
            @Override
            public void onResponse(Call<List<InstructorBody>> call, Response<List<InstructorBody>> response) {
                temp.postValue(response.body());
            }
            @Override
            public void onFailure(Call<List<InstructorBody>> call, Throwable t) {

            }
        });
    }


    public MutableLiveData<List<InstructorBody>> getMutableData(){
        if (temp == null) {
            temp = new MutableLiveData<List<InstructorBody>>();
        }
        return temp;
    }
    public boolean ok(int id){
        return false;
    }
}
