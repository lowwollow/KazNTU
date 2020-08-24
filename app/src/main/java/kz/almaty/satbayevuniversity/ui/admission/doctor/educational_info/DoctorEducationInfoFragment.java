package kz.almaty.satbayevuniversity.ui.admission.doctor.educational_info;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProviders;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import kz.almaty.satbayevuniversity.R;
import kz.almaty.satbayevuniversity.data.entity.admission.IdAndTitle;
import kz.almaty.satbayevuniversity.data.entity.admission.Speciality;
import kz.almaty.satbayevuniversity.data.entity.admission.University;
import kz.almaty.satbayevuniversity.data.network.AdmissionRetrofit;
import kz.almaty.satbayevuniversity.databinding.FragmentDoctorEducationInfoBinding;
import kz.almaty.satbayevuniversity.databinding.LayoutUniversityBinding;
import kz.almaty.satbayevuniversity.ui.admission.MainViewModel;


public class DoctorEducationInfoFragment extends Fragment {
    FragmentDoctorEducationInfoBinding fragmentDoctorEducationInfoBinding;
    MainViewModel mainViewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getString(R.string.info_about_education));
        fragmentDoctorEducationInfoBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_doctor_education_info,container,false);
        fragmentDoctorEducationInfoBinding.setHandler(this);

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        addUniversity(true,1);
        addUniversity(true,2);
        return fragmentDoctorEducationInfoBinding.getRoot();
    }

    public void addUniversity(boolean firstTime, int levelID){
        LayoutUniversityBinding layoutUniversityBinding = DataBindingUtil.inflate(getLayoutInflater(),R.layout.layout_university,null,false);
        if(levelID == 1){
            layoutUniversityBinding.setTitle("Бакалавр");
        }else if(levelID == 2){
            layoutUniversityBinding.setTitle("Магистратура");
        }
        layoutUniversityBinding.removeUniversityButton.setVisibility(firstTime ? View.GONE : View.VISIBLE);

        setUniversities(layoutUniversityBinding);
        setSpecialities(layoutUniversityBinding);
        setEduLanguages(layoutUniversityBinding);
        setYearOfGraduation(layoutUniversityBinding);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0,20,0,0);
        layoutUniversityBinding.getRoot().setLayoutParams(layoutParams);
        layoutUniversityBinding.removeUniversityButton.setOnClickListener(v -> fragmentDoctorEducationInfoBinding.mainLayout.removeView(layoutUniversityBinding.getRoot()));
        fragmentDoctorEducationInfoBinding.mainLayout.addView(layoutUniversityBinding.getRoot());
    }



    public static DoctorEducationInfoFragment newInstance(){
        return new DoctorEducationInfoFragment();
    }
    private void setUniversities(LayoutUniversityBinding layoutUniversityBinding){
        mainViewModel.getUnivercities();
        mainViewModel.getUnivercitiesListMutableLiveData().observe(this, univercities -> {
            ArrayAdapter<University> arrayAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_dropdown_item,univercities);
            layoutUniversityBinding.universitySpinner.setAdapter(arrayAdapter);
        });

        layoutUniversityBinding.universitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                University university = (University) parent.getSelectedItem();
                mainViewModel.getSpecialitiesBySchoolId("1",String.valueOf(university.getId()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setSpecialities(LayoutUniversityBinding layoutUniversityBinding){
        layoutUniversityBinding.specialitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Speciality speciality = (Speciality) parent.getSelectedItem();
                layoutUniversityBinding.anotherSpecialityEditText.setVisibility(speciality.getTitle().equals("Другие") ? View.VISIBLE : View.GONE);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        mainViewModel.getSpecialityBySchoolIdMutableLiveData().observe(this, specialities -> {
            List<Speciality> specialityList = specialities;
            Speciality speciality = new Speciality();
            speciality.setTitle("Другие");
            specialityList.add(speciality);
            ArrayAdapter<Speciality> arrayAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_dropdown_item,specialities);
            layoutUniversityBinding.specialitySpinner.setAdapter(arrayAdapter);
        });
    }
    private void setEduLanguages(LayoutUniversityBinding layoutUniversityBinding){
        MutableLiveData<List<IdAndTitle>> eduLanguagesMutableLiveData = new MutableLiveData<>();
        mainViewModel.makeRetrofitRequest(AdmissionRetrofit.getApi(1).getEduLanguages(""),eduLanguagesMutableLiveData);
        eduLanguagesMutableLiveData.observe(this, strings -> {
            ArrayAdapter<IdAndTitle> arrayAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_dropdown_item,strings);
            layoutUniversityBinding.eduLanguagesSpinner.setAdapter(arrayAdapter);
        });

    }
    private void setYearOfGraduation(LayoutUniversityBinding layoutUniversityBinding){
        ArrayList<String> yearsList = new ArrayList<>();
        int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        for(int i=1921;i<=thisYear;i++){
            yearsList.add(String.valueOf(i));
        }
        layoutUniversityBinding.yearOfGraduationSpinner.setAdapter(new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_item,yearsList));

    }
}
