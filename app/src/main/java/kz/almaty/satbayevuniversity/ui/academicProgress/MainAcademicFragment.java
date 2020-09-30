package kz.almaty.satbayevuniversity.ui.academicProgress;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import kz.almaty.satbayevuniversity.R;
import kz.almaty.satbayevuniversity.data.App;
import kz.almaty.satbayevuniversity.ui.HomeActivity;
import kz.almaty.satbayevuniversity.ui.grade.ViewPagerFragment;
import kz.almaty.satbayevuniversity.ui.individualPlan.ViewPagerIndividualPlan;
import kz.almaty.satbayevuniversity.ui.notification.NotificationViewPagerFragment;
import kz.almaty.satbayevuniversity.ui.schedule.ViewPagerSchedule;

public class MainAcademicFragment extends Fragment implements BottomNavigationView.OnNavigationItemSelectedListener  {
    SharedPreferences.Editor editor = App.getContext().getSharedPreferences("shared_preferences",Context.MODE_PRIVATE).edit();
    SharedPreferences sharedPreferences = App.getContext().getSharedPreferences("one_signal_notification_handler",Context.MODE_PRIVATE);
    private BottomNavigationView navigation;
    private static final String TAG = "MainAcademicFragment";
    public Toolbar toolbar;
    public ImageView imageView;
    boolean firstTime = false;

    public static MainAcademicFragment newInstance() {
        return new MainAcademicFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        firstTime = true;
        View view = inflater.inflate(R.layout.fragment_main_academic, container, false);
        toolbar = view.findViewById(R.id.mainToolbar);
        navigation = view.findViewById(R.id.bottomNavigation);
        imageView = view.findViewById(R.id.updateData);
        navigation.setOnNavigationItemSelectedListener(this);

        int notification_type_id = sharedPreferences.getInt("typeId",1);

        if (notification_type_id == 1){
            navigation.setSelectedItemId(R.id.academicProgressFragment);
        }else if(notification_type_id == 3){
            navigation.setSelectedItemId(R.id.grade);
        }
        sharedPreferences.edit().putInt("typeId",1).apply();
        return view;
    }
    @Override
    public void onResume(){
        super.onResume();
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener((view, i, keyEvent) -> {
            if(i == KeyEvent.KEYCODE_BACK && keyEvent.getAction() == KeyEvent.ACTION_UP){
                    if(getFragmentManager().findFragmentById(R.id.main_academic_fragment_container) instanceof AcademicFragment){
                        return false;
                    }else{
                        onNavigationItemSelected(navigation.getMenu().getItem(0));
                        navigation.getMenu().getItem(0).setChecked(true);
                        return true;
                    }
            }
            return false;
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        getToolbar();
        // TODO: Use the ViewModel

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.journal);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(v -> {
            ((HomeActivity)getActivity()).OpenToggleNavMenu();
        });

        ConnectivityManager connManager =
                (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = connManager.getActiveNetworkInfo();

        imageView.setOnClickListener(v -> {
            if(connManager.getActiveNetworkInfo() != null && connManager.getActiveNetworkInfo().isAvailable() && activeNetwork.isConnected()) {
                editor.putBoolean(getString(R.string.only_server),true);
                editor.apply();
                if(toolbar.getTitle().toString().equalsIgnoreCase(getString(R.string.journal))) {
                    replaceFragment(AcademicFragment.newInstance());
                }else if(toolbar.getTitle().toString().equalsIgnoreCase(getString(R.string.schedule))){
                    replaceFragment(ViewPagerSchedule.newInstance());
                }else if(toolbar.getTitle().toString().equalsIgnoreCase(getString(R.string.grade))){
                    replaceFragment(ViewPagerFragment.newInstance());
                }else if(toolbar.getTitle().toString().equalsIgnoreCase(getString(R.string.notifications))){
                    replaceFragment(NotificationViewPagerFragment.newInstance());
                }else if(toolbar.getTitle().toString().equalsIgnoreCase(getString(R.string.individualPlan))){
                    replaceFragment(ViewPagerIndividualPlan.newInstance());
                }
            } else {
                Toast.makeText(getActivity(), R.string.internetConnection, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        editor.putBoolean(getString(R.string.only_server),false);
        editor.apply();
        switch (item.getItemId()) {
            case R.id.academicProgressFragment:
                replaceFragment(AcademicFragment.newInstance());
                return true;
            case R.id.schedule:
                firstTime = false;
                replaceFragment(ViewPagerSchedule.newInstance());
                return true;
            case R.id.grade:
                firstTime = false;
                replaceFragment(ViewPagerFragment.newInstance());
                return true;
            case R.id.notifications:
                firstTime = false;
                replaceFragment(NotificationViewPagerFragment.newInstance());
                return true;
            case R.id.individualPlanFragment:
                firstTime = false;
                replaceFragment(ViewPagerIndividualPlan.newInstance());
                return true;
        }
        return false;
    };

    private void replaceFragmentBackStack(Fragment newFragment, String tag, int container) {
        assert getFragmentManager() != null;
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(container, newFragment, tag)
                .addToBackStack(tag)
                .commit();
    }

    private void replaceFragment(Fragment newFragment) {
        assert getFragmentManager() != null;
        FragmentTransaction ft = getFragmentManager().beginTransaction().replace(R.id.main_academic_fragment_container, newFragment);
        ft.commit();
    }


}
