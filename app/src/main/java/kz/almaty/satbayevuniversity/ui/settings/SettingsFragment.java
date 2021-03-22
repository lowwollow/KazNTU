package kz.almaty.satbayevuniversity.ui.settings;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import kz.almaty.satbayevuniversity.AuthViewModel;
import kz.almaty.satbayevuniversity.R;
import kz.almaty.satbayevuniversity.databinding.FragmentSettingsBinding;
import kz.almaty.satbayevuniversity.ui.HomeActivity;
import kz.almaty.satbayevuniversity.ui.LoginActivity;
import kz.almaty.satbayevuniversity.ui.settings.complaintFragment.ComplaintFragment;
import kz.almaty.satbayevuniversity.ui.settings.languageFragment.LanguageFragment;
import kz.almaty.satbayevuniversity.utils.Storage;

public class SettingsFragment extends Fragment {
    private FragmentSettingsBinding settingsFragmentBinding;
    private ConstraintLayout constraintLayout, settingsLanguage;
    private Button settingsLoginBtn;
    public Toolbar toolbar;
    private Context context;

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    public SettingsFragment(Context context) {
        this.context = context;
    }

    public SettingsFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        settingsFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false);
        View view = settingsFragmentBinding.getRoot();
        constraintLayout = view.findViewById(R.id.settingsComplaint);
        settingsLoginBtn = view.findViewById(R.id.settingsLoginBtn);
        settingsLanguage = view.findViewById(R.id.settingsLanguage);
        toolbar = view.findViewById(R.id.settingsToolbar);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener((view, i, keyEvent) -> {
            if (i == KeyEvent.KEYCODE_BACK && keyEvent.getAction() == KeyEvent.ACTION_UP) {
                ((HomeActivity) getActivity()).onNavigationItemSelected(((HomeActivity) getActivity()).navigationView.getMenu().getItem(0));
                ((HomeActivity) getActivity()).navigationView.getMenu().getItem(0).setChecked(true);
                return true;
            }
            return false;
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        AuthViewModel authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);
        settingsFragmentBinding.setAccountEntity(Storage.getInstance());
        settingsFragmentBinding.setSettings(new SettingsFragment(context));
        authViewModel.getImageUrl();
        authViewModel.getDrawable().observe(getViewLifecycleOwner(), bitmap -> {
            settingsFragmentBinding.setImageUrl(bitmap);
        });
        constraintLayout.setOnClickListener(v -> {
            ComplaintFragment complaintFragment = new ComplaintFragment();
            if (getFragmentManager() != null) {
                complaintFragment.show(getFragmentManager(), "Dialog");
            }
        });
        settingsLanguage.setOnClickListener(v -> {
            LanguageFragment languageFragment = new LanguageFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, languageFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        });
        settingsLoginBtn.setOnClickListener(v -> {
            authViewModel.clearDB();
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        });

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.settings);

        toolbar.setNavigationOnClickListener(v -> {
            ((HomeActivity) getActivity()).OpenToggleNavMenu();
        });
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}



