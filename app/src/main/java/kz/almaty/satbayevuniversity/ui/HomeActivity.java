package kz.almaty.satbayevuniversity.ui;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.Locale;

import kz.almaty.satbayevuniversity.AuthViewModel;
import kz.almaty.satbayevuniversity.R;
import kz.almaty.satbayevuniversity.data.SharedPrefCache;
import kz.almaty.satbayevuniversity.data.entity.Language;
import kz.almaty.satbayevuniversity.databinding.ActivityHomeBinding;
import kz.almaty.satbayevuniversity.databinding.NavHeaderBinding;
import kz.almaty.satbayevuniversity.ui.academicProgress.AcademicFragment;
import kz.almaty.satbayevuniversity.ui.academicProgress.MainAcademicFragment;
import kz.almaty.satbayevuniversity.ui.individualPlan.chosenDisciplines.ChosenDiscipline;
import kz.almaty.satbayevuniversity.ui.individualPlan.chosenDisciplines.ChosenDisciplineFragment;
import kz.almaty.satbayevuniversity.ui.schedule.scheduleFragment.ScheduleFragment;
import kz.almaty.satbayevuniversity.ui.settings.SettingsFragment;
import kz.almaty.satbayevuniversity.ui.umkd.UmkdFragment;
import kz.almaty.satbayevuniversity.utils.Storage;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static final String FRAGMENT_FIRST = "FRAGMENT_FIRST";
    public static final String FRAGMENT_SECOND = "FRAGMENT_SECOND";
    public static final String FRAGMENT_THIRD = "FRAGMENT_THIRD";
    private float scaleFactor = 5f;
    public NavigationView navigationView;
    public DrawerLayout drawer;
    public AuthViewModel authViewModel;
    public ActivityHomeBinding activityHomeBinding;
    private NavHeaderBinding navHeaderBinding;
    public SharedPreferences sPref;

    public HomeActivity() {
    }

    public void setLocale(String lang) {
        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupBindings(savedInstanceState);
        SharedPrefCache sharedPrefCache = new SharedPrefCache();
        String sharedString = sharedPrefCache.getStr("language",this);
        Gson gson = new Gson();
        try{
            Language language1 = gson.fromJson(sharedString, Language.class);
            setLocale(language1.getLanguageCode());
            getMenuText();
        } catch (IllegalStateException | JsonSyntaxException ignored){}

        showWeSettedPushNoficationDialog();
    }

    public void getMenuText(){
        // боковое меню
        Menu menu = navigationView.getMenu();
        MenuItem academicProgress = menu.findItem(R.id.academicProgress);
        academicProgress.setTitle(R.string.academicProgress);

        MenuItem umkd = menu.findItem(R.id.umkd);
        umkd.setTitle(R.string.umkd);

        MenuItem settings = menu.findItem(R.id.settings);
        settings.setTitle(R.string.settings);

        MenuItem logout = menu.findItem(R.id.logout);
        logout.setTitle(getString(R.string.logout));
    }

    private void setupBindings(Bundle savedInstanceState) {
        authViewModel = ViewModelProviders.of(this).get(AuthViewModel.class);
        activityHomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);

        navHeaderBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.nav_header,
                activityHomeBinding.navigationView, false);

        activityHomeBinding.navigationView.addHeaderView(navHeaderBinding.getRoot());
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) navHeaderBinding.headerLayout.getLayoutParams();

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int height = size.y;
        params.topMargin = height / 6;

        navHeaderBinding.headerLayout.setLayoutParams(params);

        navHeaderBinding.setAccountEntity(Storage.getInstance());
        authViewModel.getImageUrl();

        drawer = findViewById(R.id.drawer);
        drawer.setScrimColor(Color.TRANSPARENT);

        navigationView = findViewById(R.id.navigationView);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);
        onNavigationItemSelected(navigationView.getMenu().getItem(0));
        navigationView.getMenu().getItem(0).setChecked(true);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, R.string.open, R.string.close){
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                float slideX = drawerView.getWidth() * slideOffset;
                activityHomeBinding.fragmentContainer.setTranslationX(slideX);
                activityHomeBinding.fragmentContainer.setScaleX(1 - (slideOffset / scaleFactor));
                activityHomeBinding.fragmentContainer.setScaleY(1 - (slideOffset / scaleFactor));
            }
        };
        drawer.setDrawerElevation(0f);
        drawer.addDrawerListener(actionBarDrawerToggle);

        if (savedInstanceState == null){
            authViewModel.initAuth();
        }
        setupUpdate();
    }

    private void setupUpdate() {
        authViewModel.getDrawable().observe(this, bitmap -> {
            navHeaderBinding.setImageUrl(bitmap);
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.academicProgress:
                replaceFragment(MainAcademicFragment.newInstance(),R.id.fragment_container);
                break;
            case R.id.umkd:
                replaceFragment(UmkdFragment.newInstance(),R.id.fragment_container);
                break;
            case R.id.settings:
                replaceFragment(SettingsFragment.newInstance(),R.id.fragment_container);
                break;
            case R.id.logout:
                exit();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void exit(){
        clearSharedPreferences();
        authViewModel.clearDB();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void clearSharedPreferences() {
        sPref = getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sPref.edit();
        editor.putString("MyToken", "");
        editor.putString("Username", "");
        editor.putString("FullName", "");
        editor.apply();
    }
    private void replaceFragment(Fragment newFragment,  int container) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(container, newFragment).commit();
    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, getResources().getString(R.string.click_back_again), Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(() -> doubleBackToExitPressedOnce = false, 2000);
        }
    }

    public void OpenToggleNavMenu() {
        drawer.openDrawer(GravityCompat.START);
    }

    private void showWeSettedPushNoficationDialog(){
        SharedPreferences sharedPreferences = getSharedPreferences("FIRST_RUN",MODE_PRIVATE);
        boolean first_run = sharedPreferences.getBoolean("FIRST_RUN",false);
        if(!first_run){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("FIRST_RUN",true);
            editor.commit();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(false);
            AlertDialog alertDialog = builder.create();
            View view = getLayoutInflater().inflate(R.layout.dialog_push_noficiation,null);
            Button ok_button = view.findViewById(R.id.dialog_push_notification_button);
            ok_button.setOnClickListener(v -> {
                alertDialog.dismiss();
            });
            alertDialog.setView(view);
            alertDialog.show();
        }
    }
}

