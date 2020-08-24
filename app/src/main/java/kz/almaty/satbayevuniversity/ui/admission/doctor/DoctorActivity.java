package kz.almaty.satbayevuniversity.ui.admission.doctor;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.navigation.NavigationView;

import kz.almaty.satbayevuniversity.R;
import kz.almaty.satbayevuniversity.databinding.NavigationViewHeaderBinding;
import kz.almaty.satbayevuniversity.ui.admission.ContinueRegistrationDialogFragment;
import kz.almaty.satbayevuniversity.ui.admission.login.LoginViewModel;
import kz.almaty.satbayevuniversity.ui.admission.doctor.choosing_education.DoctorChoosingEducationProgrammFragment;
import kz.almaty.satbayevuniversity.ui.admission.doctor.educational_info.DoctorEducationInfoFragment;
import kz.almaty.satbayevuniversity.ui.admission.master.achievement_info.MasterAchievementFragment;
import kz.almaty.satbayevuniversity.ui.admission.master.additional_info.MasterAdditionalInfoFragment;
import kz.almaty.satbayevuniversity.ui.admission.master.labor_activity.MasterLaborActivityFragment;
import kz.almaty.satbayevuniversity.ui.admission.bachelor.main_info.MainInfoFragment;
import kz.almaty.satbayevuniversity.ui.admission.bachelor.medical_info.MedicalInfoFragment;
import kz.almaty.satbayevuniversity.ui.admission.bachelor.residence_info.ResidenceInfoFragment;
import kz.almaty.satbayevuniversity.ui.admission.master.status_info.MasterStatusFragment;

public class DoctorActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationViewHeaderBinding navigationViewHeaderBinding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_doctor_master);

        navigationViewHeaderBinding = DataBindingUtil.inflate(getLayoutInflater(),R.layout.navigation_view_header,navigationView,false);
        setupNavigation();


        LoginViewModel admissionAuthViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        admissionAuthViewModel.getBitmap().observe(this, new Observer<Bitmap>() {
            @Override
            public void onChanged(Bitmap bitmap) {
                navigationViewHeaderBinding.setImageUrl(bitmap);
            }
        });
        admissionAuthViewModel.getImageUrl();

        admissionAuthViewModel.getUserInfo();
        admissionAuthViewModel.getUserInfoMutableLiveData().observe(this, userInfo -> {
            navigationViewHeaderBinding.setUserInfo(userInfo);
        });
    }

    private void setupNavigation(){
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        toolbar = findViewById(R.id.toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);

        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> {
            drawerLayout.openDrawer(GravityCompat.START);
        });

        actionBarDrawerToggle.syncState();
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.colorWhite));
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.addHeaderView(navigationViewHeaderBinding.getRoot());
        onNavigationItemSelected(navigationView.getMenu().findItem(R.id.main_data));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        switch (item.getItemId()){
            case R.id.main_data:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, MainInfoFragment.newInstance()).commit();
                break;
            case R.id.choosing_education_programm:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, DoctorChoosingEducationProgrammFragment.newInstance()).commit();
                break;
            case R.id.residence:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, ResidenceInfoFragment.newInstance()).commit();
                break;
            case R.id.info_about_education:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, DoctorEducationInfoFragment.newInstance()).commit();
                break;
            case R.id.achievement:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, MasterAchievementFragment.newInstance()).commit();
                break;
            case R.id.labor_activity:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, MasterLaborActivityFragment.newInstance()).commit();
                break;
            case R.id.additional:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, MasterAdditionalInfoFragment.newInstance()).commit();
                break;
            case R.id.medical_data:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, MedicalInfoFragment.newInstance()).commit();
                break;
            case R.id.check_status:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, MasterStatusFragment.newInstance()).commit();
                break;
            case R.id.exit:
                ContinueRegistrationDialogFragment continueRegistrationDialogFragment = new ContinueRegistrationDialogFragment();
                continueRegistrationDialogFragment.show(getSupportFragmentManager(),"continueRegistrationDialogFragment");
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed(){
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }
}
