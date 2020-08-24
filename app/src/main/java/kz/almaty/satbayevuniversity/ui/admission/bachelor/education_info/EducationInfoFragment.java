package kz.almaty.satbayevuniversity.ui.admission.bachelor.education_info;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.List;

import kz.almaty.satbayevuniversity.R;
import kz.almaty.satbayevuniversity.data.entity.admission.IdAndTitle;
import kz.almaty.satbayevuniversity.data.entity.admission.education_info.EducationInfo;
import kz.almaty.satbayevuniversity.data.entity.admission.education_info.IeltsCert;
import kz.almaty.satbayevuniversity.data.entity.admission.education_info.ToeflCert;
import kz.almaty.satbayevuniversity.data.network.AdmissionRetrofit;
import kz.almaty.satbayevuniversity.databinding.FragmentEducationInfoBinding;
import kz.almaty.satbayevuniversity.ui.admission.MainViewModel;
import kz.almaty.satbayevuniversity.utils.Utils;

public class EducationInfoFragment extends Fragment {
    FragmentEducationInfoBinding fragmentEducationInfoBinding;
    MainViewModel mainViewModel;
    String cityId = "";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentEducationInfoBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_education_info,container,false);

        EducationInfoViewModel educationInfoViewModel = ViewModelProviders.of(this).get(EducationInfoViewModel.class);
        educationInfoViewModel.getEducationInfo();
        educationInfoViewModel.getEducationInfoMutableLiveData().observe(this, educationInfo -> {
            fragmentEducationInfoBinding.setEducationInfo(educationInfo);

            fragmentEducationInfoBinding.progressBar.setVisibility(View.GONE);
            setEducationLanguages(educationInfo.getStudyLang());
            setEducationDocumentType(educationInfo.getTypeOfCertificateOrDiploma());
            setLocalities();
            setToefl();
            setIelts();
        });
        fragmentEducationInfoBinding.schoolTypeRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            setSchoolNames(cityId,getSchoolType(),getCitySettlementType());
        });

        fragmentEducationInfoBinding.citySettlementTypeRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            setSchoolNames(cityId,getSchoolType(),getCitySettlementType());
        });
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getString(R.string.info_about_education));
        return fragmentEducationInfoBinding.getRoot();
    }

    public static EducationInfoFragment newInstance(){
        return new EducationInfoFragment();
    }

    private void setEducationLanguages(int selectedId){
        MutableLiveData<List<IdAndTitle>> eduLanguagesMutableLiveData = new MutableLiveData<>();
        mainViewModel.makeRetrofitRequest(AdmissionRetrofit.getApi(1).getEduLanguages(""),eduLanguagesMutableLiveData);
        eduLanguagesMutableLiveData.observe(this, strings -> {
            ArrayAdapter<IdAndTitle> arrayAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_dropdown_item,strings);
            fragmentEducationInfoBinding.eduLanguagesSpinner.setAdapter(arrayAdapter);
            int position = arrayAdapter.getPosition(Utils.getIdAndTitleFromListById(strings,selectedId));
            fragmentEducationInfoBinding.eduLanguagesSpinner.setSelection(position);
        });
    }
    private void setEducationDocumentType(int selectedId){
        MutableLiveData<List<IdAndTitle>> eduDocumentTypeMutableLiveData = new MutableLiveData<>();
        mainViewModel.makeRetrofitRequest(AdmissionRetrofit.getApi(1).getEduDocumentTypes(),eduDocumentTypeMutableLiveData);
        eduDocumentTypeMutableLiveData.observe(this, strings -> {
            ArrayAdapter<IdAndTitle> arrayAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_dropdown_item,strings);
            fragmentEducationInfoBinding.eduDocumentTypesSpinner.setAdapter(arrayAdapter);
            int position = arrayAdapter.getPosition(Utils.getIdAndTitleFromListById(strings,selectedId));
            fragmentEducationInfoBinding.eduDocumentTypesSpinner.setSelection(position);
        });
    }
    private void setLocalities(){
        MutableLiveData<List<IdAndTitle>> localitiesMutableLiveData = new MutableLiveData<>();
        mainViewModel.makeRetrofitRequest(AdmissionRetrofit.getApi(1).getLocalities(),localitiesMutableLiveData);
        localitiesMutableLiveData.observe(this, strings -> {
            ArrayAdapter<IdAndTitle> arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, strings);
            fragmentEducationInfoBinding.cityNameAutoCompleteTextView.setAdapter(arrayAdapter);

            fragmentEducationInfoBinding.cityNameAutoCompleteTextView.setOnItemClickListener((parent, view, position, id) -> {
                cityId = String.valueOf(arrayAdapter.getItem(position).getId());
                setSchoolNames(cityId,getSchoolType(),getCitySettlementType());
            });

        });


    }
    private void setSchoolNames(String localityId, String schoolTypeId,boolean isCitySettlementType){
        MutableLiveData<List<IdAndTitle>> schoolsMutableLiveData = new MutableLiveData<>();
        mainViewModel.makeRetrofitRequest(AdmissionRetrofit.getApi(1).getSchools(localityId,schoolTypeId,isCitySettlementType),schoolsMutableLiveData);
        schoolsMutableLiveData.observe(this, idAndTitles -> {
            ArrayAdapter<IdAndTitle> arrayAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_dropdown_item_1line,idAndTitles);
            fragmentEducationInfoBinding.nameOfSchoolACTV.setAdapter(arrayAdapter);
        });
    }
    private String getSchoolType(){
        String schoolType="";
        int radioButtonId = fragmentEducationInfoBinding.schoolTypeRadioGroup.getCheckedRadioButtonId();
        if(radioButtonId == R.id.schoolRadioButton){
            schoolType = "1";
        }else if(radioButtonId == R.id.collegeRadioButton){
            schoolType = "3";
        }
        return schoolType;
    }
    private  boolean getCitySettlementType(){
        boolean isCitySettlementType = false;
        int  radioButtonId = fragmentEducationInfoBinding.citySettlementTypeRadioGroup.getCheckedRadioButtonId();
        if(radioButtonId == R.id.cityRadioButton){
            isCitySettlementType = true;
        }else if(radioButtonId == R.id.villageRadioButton){
            isCitySettlementType = false;
        }
        return  isCitySettlementType;
    }
    private void setToefl(){
        fragmentEducationInfoBinding.toeflCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
           fragmentEducationInfoBinding.toeflLinearLayout.getRoot().setVisibility(isChecked ? View.VISIBLE : View.GONE);
        });
    }
    private void setIelts(){
        fragmentEducationInfoBinding.ieltsCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            fragmentEducationInfoBinding.ieltsLinearLayout.getRoot().setVisibility(isChecked ? View.VISIBLE : View.GONE);
        });
    }
}
