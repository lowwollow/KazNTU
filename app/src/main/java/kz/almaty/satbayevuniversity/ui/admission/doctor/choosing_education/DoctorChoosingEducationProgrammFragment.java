package kz.almaty.satbayevuniversity.ui.admission.doctor.choosing_education;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProviders;

import java.util.List;

import kz.almaty.satbayevuniversity.R;
import kz.almaty.satbayevuniversity.data.entity.admission.IdAndTitle;
import kz.almaty.satbayevuniversity.data.network.AdmissionRetrofit;
import kz.almaty.satbayevuniversity.databinding.FragmentChoosingEducationProgrammDoctorBinding;
import kz.almaty.satbayevuniversity.ui.admission.MainViewModel;

public class DoctorChoosingEducationProgrammFragment extends Fragment {
    FragmentChoosingEducationProgrammDoctorBinding fragmentChoosingEducationProgrammDoctorBinding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentChoosingEducationProgrammDoctorBinding = DataBindingUtil.inflate(getLayoutInflater(),R.layout.fragment_choosing_education_programm_doctor,container,false);

        MainViewModel mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);



        fragmentChoosingEducationProgrammDoctorBinding.groupEducationalProgramSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                IdAndTitle idAndTitle = (IdAndTitle) parent.getSelectedItem();
                mainViewModel.getSpecialitiesByGroupOfEducationalProgram("3",String.valueOf(idAndTitle.getId()));
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        mainViewModel.getSpecialityByEducationalProgramMutableLiveData().observe(getViewLifecycleOwner(), idAndTitle -> {
            fragmentChoosingEducationProgrammDoctorBinding.setSpeciality(idAndTitle);
        });



        MutableLiveData<List<IdAndTitle>> paymentTypeMutableLiveData = new MutableLiveData<>();
        mainViewModel.makeRetrofitRequest(AdmissionRetrofit.getApi(1).getEduPaymentTypes(""),paymentTypeMutableLiveData);
        paymentTypeMutableLiveData.observe(this, idAndTitles -> {
            fragmentChoosingEducationProgrammDoctorBinding.setPaymentType(idAndTitles);
        });

        fragmentChoosingEducationProgrammDoctorBinding.paymentTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                IdAndTitle idAndTitle = (IdAndTitle) parent.getSelectedItem();
                if(idAndTitle.getId() == 1){
                    fragmentChoosingEducationProgrammDoctorBinding.grantTypesLinearLayout.setVisibility(View.VISIBLE);
                }else{
                    fragmentChoosingEducationProgrammDoctorBinding.grantTypesLinearLayout.setVisibility(View.GONE);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        MutableLiveData<List<IdAndTitle>> researchDirectionsMutableLiveData = new MutableLiveData<>();
        mainViewModel.makeRetrofitRequest(AdmissionRetrofit.getApi(1).getResearchDirections(),researchDirectionsMutableLiveData);
        researchDirectionsMutableLiveData.observe(this, idAndTitles -> {
            fragmentChoosingEducationProgrammDoctorBinding.setResearchDirections(idAndTitles);
        });

        MutableLiveData<List<IdAndTitle>> grantTypesMutableLiveData = new MutableLiveData<>();
        mainViewModel.makeRetrofitRequest(AdmissionRetrofit.getApi(1).getGrantTypes("1|2|3"),grantTypesMutableLiveData);
        grantTypesMutableLiveData.observe(this, idAndTitles -> {
            fragmentChoosingEducationProgrammDoctorBinding.setGrantTypes(idAndTitles);
        });

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getString(R.string.choosing_education_programm));
        return fragmentChoosingEducationProgrammDoctorBinding.getRoot();
    }

    public static DoctorChoosingEducationProgrammFragment newInstance(){
        return new DoctorChoosingEducationProgrammFragment();
    }
}
