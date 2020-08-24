package kz.almaty.satbayevuniversity.ui.admission.bachelor.medical_info;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import kz.almaty.satbayevuniversity.R;
import kz.almaty.satbayevuniversity.databinding.FragmentMedicalInfoBinding;

public class MedicalInfoFragment extends Fragment {
    FragmentMedicalInfoBinding fragmentMedicalInfoBinding;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       fragmentMedicalInfoBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_medical_info,container,false);

       fragmentMedicalInfoBinding.dateOfFluorographyEditText.setOnClickListener(v -> openDatePickerDialog());

        MedicalInfoViewModel medicalInfoViewModel = ViewModelProviders.of(this).get(MedicalInfoViewModel.class);
        fragmentMedicalInfoBinding.setViewModel(medicalInfoViewModel);
        medicalInfoViewModel.getMedicalInfo();
        medicalInfoViewModel.getResponseMutableLiveData().observe(this, s -> {
            Toast.makeText(getContext(),s,Toast.LENGTH_SHORT).show();
        });

        medicalInfoViewModel.getMedicalInfoMutableLiveData().observe(this, medicalInfo -> {
            fragmentMedicalInfoBinding.setMedicalInfo(medicalInfo);
            fragmentMedicalInfoBinding.progressBar.setVisibility(View.GONE);
        });

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getString(R.string.medical_info));
        return fragmentMedicalInfoBinding.getRoot();
    }

    public static MedicalInfoFragment newInstance(){
        return new MedicalInfoFragment();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void openDatePickerDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext());
        DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                fragmentMedicalInfoBinding.dateOfFluorographyEditText.setText(dayOfMonth+"."+(month+1)+"."+year);
            }
        };
        datePickerDialog.setOnDateSetListener(onDateSetListener);
        datePickerDialog.show();
    }

}
