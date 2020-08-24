package kz.almaty.satbayevuniversity.ui.admission.bachelor.status_info;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import kz.almaty.satbayevuniversity.R;
import kz.almaty.satbayevuniversity.data.entity.admission.status_info.EntrantStatusInfo;
import kz.almaty.satbayevuniversity.databinding.FragmentBachelorStatusBinding;

public class BachelorStatusFragment extends Fragment {
    FragmentBachelorStatusBinding fragmentBachelorStatusBinding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentBachelorStatusBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_bachelor_status,container,false);

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getString(R.string.enrollee_status));

        BachelorStatusViewModel bachelorStatusViewModel = ViewModelProviders.of(this).get(BachelorStatusViewModel.class);
        bachelorStatusViewModel.getStatusInfo();
        bachelorStatusViewModel.getStatusInfoMutableLiveData().observe(this, entrantStatusInfo -> {
            fragmentBachelorStatusBinding.layoutBachelorStatus.status.setText(entrantStatusInfo.getEntrantStatusTitle());
            fragmentBachelorStatusBinding.layoutBachelorStatus.isCheckedByAdmission.setChecked(entrantStatusInfo.getByAdmission().getIsChecked());
            fragmentBachelorStatusBinding.layoutBachelorStatus.isCheckedByDoctor.setChecked(entrantStatusInfo.getByDoctor().getIsChecked());
        });
        return fragmentBachelorStatusBinding.getRoot();
    }
    public static BachelorStatusFragment newInstance(){
        return new BachelorStatusFragment();
    }


}
