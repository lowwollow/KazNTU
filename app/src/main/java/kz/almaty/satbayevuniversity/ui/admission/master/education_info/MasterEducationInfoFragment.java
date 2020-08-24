package kz.almaty.satbayevuniversity.ui.admission.master.education_info;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavArgs;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import kz.almaty.satbayevuniversity.R;
import kz.almaty.satbayevuniversity.data.entity.admission.IdAndTitle;
import kz.almaty.satbayevuniversity.data.entity.admission.Speciality;
import kz.almaty.satbayevuniversity.data.entity.admission.University;
import kz.almaty.satbayevuniversity.data.entity.admission.education_info_master.Data;
import kz.almaty.satbayevuniversity.data.entity.admission.education_info_master.EducationInfoMaster;
import kz.almaty.satbayevuniversity.data.entity.admission.labor_activity_master.DocumentScan;
import kz.almaty.satbayevuniversity.data.network.AdmissionRetrofit;
import kz.almaty.satbayevuniversity.databinding.FragmentMasterEducatinInfoBinding;
import kz.almaty.satbayevuniversity.databinding.LayoutImageViewBinding;
import kz.almaty.satbayevuniversity.databinding.LayoutUniversityBinding;
import kz.almaty.satbayevuniversity.ui.admission.MainViewModel;
import kz.almaty.satbayevuniversity.ui.admission.master.additional_info.AdditionalInfoViewModel;
import kz.almaty.satbayevuniversity.utils.Utils;

public class MasterEducationInfoFragment extends Fragment {
    FragmentMasterEducatinInfoBinding fragmentMasterEducatinInfoBinding;
    MainViewModel mainViewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getString(R.string.info_about_education));
        fragmentMasterEducatinInfoBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_master_educatin_info,container,false);
        fragmentMasterEducatinInfoBinding.setHandler(this);

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        MasterEducationInfoViewModel masterEducationInfoViewModel = ViewModelProviders.of(this).get(MasterEducationInfoViewModel.class);
        masterEducationInfoViewModel.getEducationInfoMaster();
        masterEducationInfoViewModel.getEducationInfoMasterMutableLiveData().observe(this, educationInfoMaster -> {
            fragmentMasterEducatinInfoBinding.progressBar.setVisibility(View.GONE);
            for(Data data : educationInfoMaster.getDatas()){
                addUniversity(true,data);
            }
        });
//        addUniversity(true, );
        return fragmentMasterEducatinInfoBinding.getRoot();
    }

    public void addUniversity(boolean firstTime,Data data){
        LayoutUniversityBinding layoutUniversityBinding = DataBindingUtil.inflate(getLayoutInflater(),R.layout.layout_university,null,false);
        layoutUniversityBinding.setData(data);
        layoutUniversityBinding.setTitle(getResources().getString(R.string.university));
        layoutUniversityBinding.removeUniversityButton.setVisibility(firstTime ? View.GONE : View.VISIBLE);

        for(DocumentScan documentScan : data.getDiplomaScan()){
            layoutUniversityBinding.diplomaDocumentsLayout.addView(addImageDocuments(documentScan.getDocumentID()));
        }

        setUniversities(layoutUniversityBinding,data.getUniversity().getId());
        setSpecialities(layoutUniversityBinding,data.getSpeciality().getId());
        setEduLanguages(layoutUniversityBinding,data.getStudyLanguageId());
        setYearOfGraduation(layoutUniversityBinding,data.getFinishedYear());

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0,20,0,0);
        layoutUniversityBinding.getRoot().setLayoutParams(layoutParams);

        layoutUniversityBinding.removeUniversityButton.setOnClickListener(v -> fragmentMasterEducatinInfoBinding.mainLayout.removeView(layoutUniversityBinding.getRoot()));
        fragmentMasterEducatinInfoBinding.mainLayout.addView(layoutUniversityBinding.getRoot());
    }

    public static MasterEducationInfoFragment newInstance(){
        return new MasterEducationInfoFragment();
    }

    private void setUniversities(LayoutUniversityBinding layoutUniversityBinding,int selectedId){
        mainViewModel.getUnivercities();
        mainViewModel.getUnivercitiesListMutableLiveData().observe(this, univercities -> {
            ArrayAdapter<University> arrayAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_dropdown_item,univercities);
            layoutUniversityBinding.universitySpinner.setAdapter(arrayAdapter);
            int position = arrayAdapter.getPosition(getUniversityById(selectedId,univercities));
            layoutUniversityBinding.universitySpinner.setSelection(position);
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

    private void setSpecialities(LayoutUniversityBinding layoutUniversityBinding,int selectedId){
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
            ArrayAdapter<Speciality> arrayAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_dropdown_item,specialities);
            layoutUniversityBinding.specialitySpinner.setAdapter(arrayAdapter);
            int position = arrayAdapter.getPosition(getSpecialityById(selectedId, specialities));
            layoutUniversityBinding.specialitySpinner.setSelection(position);
            Speciality speciality = new Speciality();
            speciality.setTitle("Другие");
            speciality.setId(1000);
            specialityList.add(speciality);
        });
    }
    private void setEduLanguages(LayoutUniversityBinding layoutUniversityBinding,int selectedId){
        MutableLiveData<List<IdAndTitle>> eduLanguagesMutableLiveData = new MutableLiveData<>();
        mainViewModel.makeRetrofitRequest(AdmissionRetrofit.getApi(1).getEduLanguages(""),eduLanguagesMutableLiveData);
        eduLanguagesMutableLiveData.observe(this, strings -> {
            ArrayAdapter<IdAndTitle> arrayAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_dropdown_item,strings);
            layoutUniversityBinding.eduLanguagesSpinner.setAdapter(arrayAdapter);
            int position = arrayAdapter.getPosition(Utils.getIdAndTitleFromListById(strings,selectedId));
            layoutUniversityBinding.eduLanguagesSpinner.setSelection(position);
        });

    }
    private void setYearOfGraduation(LayoutUniversityBinding layoutUniversityBinding,int finishedYear){
        ArrayList<String> yearsList = new ArrayList<>();
        int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        for(int i=1921;i<=thisYear;i++){
            yearsList.add(String.valueOf(i));
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_item,yearsList);
        layoutUniversityBinding.yearOfGraduationSpinner.setAdapter(arrayAdapter);
        int position = arrayAdapter.getPosition(String.valueOf(finishedYear));
        layoutUniversityBinding.yearOfGraduationSpinner.setSelection(position);
    }

    private University getUniversityById(int selectedId,List<University> universities){
        for(University university : universities){
            if(university.getId() == selectedId){
                return university;
            }
        }
        return null;
    }
    private Speciality getSpecialityById(int selectedId,List<Speciality> specialities){
        for(Speciality speciality : specialities){
            if(speciality.getId() == selectedId){
                return  speciality;
            }
        }
        return null;
    }
    private View addImageDocuments(String documentId ){
        AdditionalInfoViewModel additionalInfoViewModel = ViewModelProviders.of(this).get(AdditionalInfoViewModel.class);
        MutableLiveData<Bitmap> bitmapMutableLiveData = new MutableLiveData<>();
        additionalInfoViewModel.getDocumentImage(documentId,bitmapMutableLiveData);

        LayoutImageViewBinding layoutImageViewBinding = DataBindingUtil.inflate(getLayoutInflater(),R.layout.layout_image_view,null,false);
        bitmapMutableLiveData.observe(this, bitmap -> {
            layoutImageViewBinding.setBitmap(bitmap);
            layoutImageViewBinding.imageView.setOnClickListener(v -> {
                Utils.openFullImage(bitmap,getContext());
            });
        });
        return layoutImageViewBinding.getRoot();
    }
}
