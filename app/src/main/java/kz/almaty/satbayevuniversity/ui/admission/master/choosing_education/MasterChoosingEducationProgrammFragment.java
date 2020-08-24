package kz.almaty.satbayevuniversity.ui.admission.master.choosing_education;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.Toast;

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
import kz.almaty.satbayevuniversity.data.entity.admission.Speciality;
import kz.almaty.satbayevuniversity.data.entity.admission.choosing_education_master.SpecialityChoise;
import kz.almaty.satbayevuniversity.data.network.AdmissionRetrofit;
import kz.almaty.satbayevuniversity.databinding.FragmentChoosingEducationProgrammMasterBinding;
import kz.almaty.satbayevuniversity.ui.admission.MainViewModel;
import kz.almaty.satbayevuniversity.ui.admission.bachelor.ImageDialog;
import kz.almaty.satbayevuniversity.utils.Utils;

public class MasterChoosingEducationProgrammFragment extends Fragment {
    FragmentChoosingEducationProgrammMasterBinding fragmentChoosingEducationProgrammMasterBinding;
    MainViewModel mainViewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentChoosingEducationProgrammMasterBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_choosing_education_programm_master,container,false);

        fragmentChoosingEducationProgrammMasterBinding.setMasterChoosingEducationProgrammFragment(this);
        SpecialityChoiseViewModel specialityChoiseViewModel = ViewModelProviders.of(this).get(SpecialityChoiseViewModel.class);
        specialityChoiseViewModel.getSpecialityChoises();
        specialityChoiseViewModel.getSpecialityChoiseMutableLiveData().observe(this, specialityChoise -> {
            fragmentChoosingEducationProgrammMasterBinding.setSpecialityChoise(specialityChoise);
            fragmentChoosingEducationProgrammMasterBinding.progressBar.setVisibility(View.GONE);

            specialityChoiseViewModel.getDocumentImage(String.valueOf(specialityChoise.getGarantMessageId()));
            specialityChoiseViewModel.getBitmapMutableLiveData().observe(this, bitmap -> {
                fragmentChoosingEducationProgrammMasterBinding.setBitmap(bitmap);
            });
            setGroupOfEducationalProgram(specialityChoise.getGroupEducationalPrograms());
            setSpecialities(specialityChoise.getSpecialityId());
            setMasterProgramType(specialityChoise.getMasterProgramTypeId());
            setPaymentType(specialityChoise.getIsCorporateStudent(),specialityChoise.getPaymentTypeId());
            setGrantTypes(specialityChoise.getGrantTypeId());
            setEduLanguages(specialityChoise.getStudyLanguageId());

        });

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getString(R.string.choosing_education_programm));
        return fragmentChoosingEducationProgrammMasterBinding.getRoot();
    }

    public static MasterChoosingEducationProgrammFragment newInstance(){
        return new MasterChoosingEducationProgrammFragment();
    }
    private void setGroupOfEducationalProgram(int selectedId){
        MutableLiveData<List<IdAndTitle>> groupOfEducationalProgramsMutableLiveData = new MutableLiveData<>();
        mainViewModel.makeRetrofitRequest(AdmissionRetrofit.getApi(1).getGroupEducationalPrograms("2"),groupOfEducationalProgramsMutableLiveData);
        groupOfEducationalProgramsMutableLiveData.observe(this, idAndTitles -> {
            ArrayAdapter<IdAndTitle> arrayAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_dropdown_item,idAndTitles);
            fragmentChoosingEducationProgrammMasterBinding.groupEducationalProgramSpinner.setAdapter(arrayAdapter);
            int position = arrayAdapter.getPosition(Utils.getIdAndTitleFromListById(idAndTitles,selectedId));
            fragmentChoosingEducationProgrammMasterBinding.groupEducationalProgramSpinner.setSelection(position);
        });

        fragmentChoosingEducationProgrammMasterBinding.groupEducationalProgramSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                IdAndTitle idAndTitle = (IdAndTitle) parent.getSelectedItem();
                mainViewModel.getSpecialitiesByGroupOfEducationalProgram("2",String.valueOf(idAndTitle.getId()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    private void setSpecialities(int selectedId){
        mainViewModel.getSpecialityByEducationalProgramMutableLiveData().observe(this, specialityList -> {
            ArrayAdapter<Speciality> arrayAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_dropdown_item,specialityList);
            fragmentChoosingEducationProgrammMasterBinding.specialitiesSpinner.setAdapter(arrayAdapter);
            int position = arrayAdapter.getPosition(getSpecialityFromListById(specialityList,selectedId));
            fragmentChoosingEducationProgrammMasterBinding.specialitiesSpinner.setSelection(position);
        });
    }
    private Speciality getSpecialityFromListById(List<Speciality> specialities,int selectedId){
        for(Speciality speciality : specialities){
            if(speciality.getId() == selectedId){
                return speciality;
            }
        }
        return null;
    }
    private void setEduLanguages(int selectedId){
        MutableLiveData<List<IdAndTitle>> eduLanguagesMutableLiveData = new MutableLiveData<>();
        mainViewModel.makeRetrofitRequest(AdmissionRetrofit.getApi(1).getEduLanguages("1|2|3"),eduLanguagesMutableLiveData);
        eduLanguagesMutableLiveData.observe(this, strings -> {
            ArrayAdapter<IdAndTitle> arrayAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_dropdown_item,strings);
            fragmentChoosingEducationProgrammMasterBinding.eduLanguagesSpinner.setAdapter(arrayAdapter);
            int position = arrayAdapter.getPosition(Utils.getIdAndTitleFromListById(strings,selectedId));
            fragmentChoosingEducationProgrammMasterBinding.eduLanguagesSpinner.setSelection(position);
        });
    }

    private void setMasterProgramType(int selectedId){
        MutableLiveData<List<IdAndTitle>> masterProgramTypeMutableLiveData = new MutableLiveData<>();
        mainViewModel.makeRetrofitRequest(AdmissionRetrofit.getApi(1).getMasterProgramType(),masterProgramTypeMutableLiveData);
        masterProgramTypeMutableLiveData.observe(this, strings -> {
            ArrayAdapter<IdAndTitle> arrayAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_dropdown_item,strings);
            fragmentChoosingEducationProgrammMasterBinding.masterProgramTypeSpinner.setAdapter(arrayAdapter);
            int position = arrayAdapter.getPosition(Utils.getIdAndTitleFromListById(strings,selectedId));
            fragmentChoosingEducationProgrammMasterBinding.masterProgramTypeSpinner.setSelection(position);
        });
    }

    private void setPaymentType(boolean isCorporateStudent, int selectedId){
        MutableLiveData<List<IdAndTitle>> mutableLiveData = new MutableLiveData<>();
        fragmentChoosingEducationProgrammMasterBinding.individualRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Radio button is checked",Toast.LENGTH_LONG).show();
            }
        });
        fragmentChoosingEducationProgrammMasterBinding.masterEnrolleeTypeRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if(R.id.corporateRadioButton == checkedId){
                mainViewModel.makeRetrofitRequest(AdmissionRetrofit.getApi(1).getEduPaymentTypes("3"),mutableLiveData);
                mutableLiveData.observe(getViewLifecycleOwner(), strings -> {
                    ArrayAdapter<IdAndTitle> arrayAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_dropdown_item,strings);
                    fragmentChoosingEducationProgrammMasterBinding.paymentTypeSpinner.setAdapter(arrayAdapter);
                    int position = arrayAdapter.getPosition(Utils.getIdAndTitleFromListById(strings,selectedId));
                    fragmentChoosingEducationProgrammMasterBinding.paymentTypeSpinner.setSelection(position);
                });
            }else if(R.id.individualRadioButton == checkedId){
                mainViewModel.makeRetrofitRequest(AdmissionRetrofit.getApi(1).getEduPaymentTypes("1|2"),mutableLiveData);
                mutableLiveData.observe(getViewLifecycleOwner(), strings -> {
                    ArrayAdapter<IdAndTitle> arrayAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_dropdown_item,strings);
                    fragmentChoosingEducationProgrammMasterBinding.paymentTypeSpinner.setAdapter(arrayAdapter);
                    int position = arrayAdapter.getPosition(Utils.getIdAndTitleFromListById(strings,selectedId));
                    fragmentChoosingEducationProgrammMasterBinding.paymentTypeSpinner.setSelection(position);
                });
            }
        });
    }
    private void setGrantTypes(int selectedId){
        fragmentChoosingEducationProgrammMasterBinding.paymentTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                IdAndTitle idAndTitle = (IdAndTitle) parent.getSelectedItem();
                fragmentChoosingEducationProgrammMasterBinding.grantTypesLinearLayout.setVisibility(idAndTitle.getId() == 1 ? View.VISIBLE : View.GONE);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        MutableLiveData<List<IdAndTitle>> grantTypesMutableLiveData = new MutableLiveData<>();
        mainViewModel.makeRetrofitRequest(AdmissionRetrofit.getApi(1).getGrantTypes("1|2|3"),grantTypesMutableLiveData);
        grantTypesMutableLiveData.observe(this, idAndTitles -> {
            ArrayAdapter<IdAndTitle> arrayAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_dropdown_item,idAndTitles);
            fragmentChoosingEducationProgrammMasterBinding.grantTypesSpinner.setAdapter(arrayAdapter);
            int position = arrayAdapter.getPosition(Utils.getIdAndTitleFromListById(idAndTitles,selectedId));
            fragmentChoosingEducationProgrammMasterBinding.grantTypesSpinner.setSelection(position);
        });
    }
    public void openFullImage(Bitmap bitmap){
        ImageDialog imageDialog = new ImageDialog(bitmap);
        imageDialog.show(getFragmentManager(),"imageDialog");
    }
}
