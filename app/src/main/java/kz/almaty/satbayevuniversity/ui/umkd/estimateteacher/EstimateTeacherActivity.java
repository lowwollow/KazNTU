package kz.almaty.satbayevuniversity.ui.umkd.estimateteacher;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Adapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import kz.almaty.satbayevuniversity.R;
import kz.almaty.satbayevuniversity.databinding.EstimateTeacherBinding;
import kz.almaty.satbayevuniversity.databinding.EstimateTeacherBindingImpl;
import kz.almaty.satbayevuniversity.ui.umkd.UmkdAdapter;

public class EstimateTeacherActivity extends AppCompatActivity {
    private Context context;

    public EstimateTeacherActivity(Context context) {
        this.context = context;
    }
    public EstimateTeacherActivity(){}
    public EstimateTeacherActivity getInstance() {return new EstimateTeacherActivity();}

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EstimateTeacherBinding estimate = DataBindingUtil.setContentView(this, R.layout.estimate_teacher);
        setTitleName();
        //TODO
        //getSupportActionBar().setTitle("Оценить преподавателя");
    }


    private void setTitleName(){
        UmkdAdapter adapter = new UmkdAdapter();
        String teacher_name = adapter.getUmkd().getInstructorName();
        TextView name = findViewById(R.id.name);
        name.setText(teacher_name);
    }

}
