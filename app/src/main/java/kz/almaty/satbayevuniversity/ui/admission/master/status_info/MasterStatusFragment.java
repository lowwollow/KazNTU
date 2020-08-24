package kz.almaty.satbayevuniversity.ui.admission.master.status_info;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import kz.almaty.satbayevuniversity.R;
import kz.almaty.satbayevuniversity.databinding.FragmentMasterDoctorStatusBinding;

public class MasterStatusFragment extends Fragment {
    FragmentMasterDoctorStatusBinding fragmentMasterDoctorStatusBinding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentMasterDoctorStatusBinding  = DataBindingUtil.inflate(getLayoutInflater(),R.layout.fragment_master_doctor_status,container,false);
        MasterStatusViewModel masterStatusViewModel = ViewModelProviders.of(this).get(MasterStatusViewModel.class);
        masterStatusViewModel.getMasterStatusInfo();
        masterStatusViewModel.getMasterStatusInfoMutableLiveData().observe(this, masterStatusInfo -> {
            fragmentMasterDoctorStatusBinding.setMasterStatusInfo(masterStatusInfo);
            fragmentMasterDoctorStatusBinding.layoutBachelorStatus.status.setText(masterStatusInfo.getEntrantStatusTitle());
            fragmentMasterDoctorStatusBinding.layoutBachelorStatus.isCheckedByAdmission.setChecked(masterStatusInfo.getByAdmission().getIsChecked());
            fragmentMasterDoctorStatusBinding.layoutBachelorStatus.isCheckedByDoctor.setChecked(masterStatusInfo.getByDoctor().getIsChecked());
        });

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getString(R.string.enrollee_status));
        return fragmentMasterDoctorStatusBinding.getRoot();
    }
    public static MasterStatusFragment newInstance(){
        return new MasterStatusFragment();
    }
}
