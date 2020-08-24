package kz.almaty.satbayevuniversity.ui.admission.bachelor.residence_info;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
import kz.almaty.satbayevuniversity.data.entity.admission.residence_info.ResidenceInfo;
import kz.almaty.satbayevuniversity.data.network.AdmissionRetrofit;
import kz.almaty.satbayevuniversity.databinding.FragmentResidenceInfoBinding;
import kz.almaty.satbayevuniversity.ui.admission.MainViewModel;

import static kz.almaty.satbayevuniversity.utils.Utils.getIdAndTitleFromListById;

public class ResidenceInfoFragment extends Fragment {
    FragmentResidenceInfoBinding fragmentResidenceInfoBinding;
    MainViewModel mainViewModel;
    ResidenceInfoViewModel residenceInfoViewModel;
    MutableLiveData<List<IdAndTitle>> cityMutableLiveData = new MutableLiveData<>();
    MutableLiveData<List<IdAndTitle>> areaMutableLiveData = new MutableLiveData<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentResidenceInfoBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_residence_info,container,false);
        fragmentResidenceInfoBinding.setHandler(this);

        residenceInfoViewModel = ViewModelProviders.of(this).get(ResidenceInfoViewModel.class);
        residenceInfoViewModel.getResidenceInfo();
        residenceInfoViewModel.getResponseMutableLiveData().observe(this, s -> {
            Toast.makeText(getContext(),s,Toast.LENGTH_SHORT).show();
        });
        residenceInfoViewModel.getResidenceInfoMutableLiveData().observe(this, residenceInfo -> {
            fragmentResidenceInfoBinding.setResidenceInfo(residenceInfo);
            fragmentResidenceInfoBinding.progressBar.setVisibility(View.GONE);
            setDormitory();
            setCountries(residenceInfo.getCountryId());
            if(residenceInfo.getRegion() != null){
                setRegion(residenceInfo.getRegion().getId());
            }else{
                setRegion(0);
            }
            if(residenceInfo.getCityOrVillagePermanent() != null){
                setCity(residenceInfo.getCityOrVillagePermanent().getId());
            }else{
                setCity(0);
            }
            if(residenceInfo.getArea() != null){
                setArea(residenceInfo.getArea().getId());
            }else{
                setArea(0);
            }
            if(residenceInfo.getNeedInAccomodation())
                setHostelPrivelege(residenceInfo.getHosterPrivelegeID());
            else
                setHostelPrivelege(0);
            if(residenceInfo.getDistrictInAlmatyId()==null)
                setAlmatyDistrict(0);
            else
               setAlmatyDistrict(residenceInfo.getDistrictInAlmatyId());
        });

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getString(R.string.residence_info));
        return fragmentResidenceInfoBinding.getRoot();
    }

    public static ResidenceInfoFragment newInstance(){
        return new ResidenceInfoFragment();
    }

    private void setDormitory(){
        fragmentResidenceInfoBinding.dormitoryCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            fragmentResidenceInfoBinding.dormitoryPrivelegesLayout.setVisibility(isChecked ? View.VISIBLE : View.GONE);
        });
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
    }
    private void setCountries(int selectedId){
        MutableLiveData<List<IdAndTitle>> countryMutableLiveData = new MutableLiveData<>();
        mainViewModel.makeRetrofitRequest(AdmissionRetrofit.getApi(1).getCountries(),countryMutableLiveData);
        countryMutableLiveData.observe(this, strings -> {
            ArrayAdapter<IdAndTitle> arrayAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_dropdown_item,strings);
            fragmentResidenceInfoBinding.countriesSpinner.setAdapter(arrayAdapter);
            int position = arrayAdapter.getPosition(getIdAndTitleFromListById(strings,selectedId));
            fragmentResidenceInfoBinding.countriesSpinner.setSelection(position);
        });
        fragmentResidenceInfoBinding.countriesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                fragmentResidenceInfoBinding.kazakhstanLayout.setVisibility(((IdAndTitle)fragmentResidenceInfoBinding.countriesSpinner.getSelectedItem()).getId() == 1 ? View.VISIBLE : View.GONE);
                fragmentResidenceInfoBinding.adressPermanentTextView.setText(((IdAndTitle)fragmentResidenceInfoBinding.countriesSpinner.getSelectedItem()).getId() == 1 ? getResources().getString(R.string.address_in_almaty) : getResources().getString(R.string.residence_info));
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    private void setHostelPrivelege(int selectedId){
        MutableLiveData<List<IdAndTitle>> hostelPrivelegeMutableLiveData = new MutableLiveData<>();
        mainViewModel.makeRetrofitRequest(AdmissionRetrofit.getApi(1).getHostelPriveleges(),hostelPrivelegeMutableLiveData);
        hostelPrivelegeMutableLiveData.observe(this, strings -> {
            ArrayAdapter<IdAndTitle> arrayAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_dropdown_item,strings);
            fragmentResidenceInfoBinding.hostelPrivelegesSpinner.setAdapter(arrayAdapter);
            int position = arrayAdapter.getPosition(getIdAndTitleFromListById(strings,selectedId));
            fragmentResidenceInfoBinding.hostelPrivelegesSpinner.setSelection(position);
        });
    }
    private void setAlmatyDistrict(int selectedId){
        MutableLiveData<List<IdAndTitle>> almatyDistrictMutableLiveData = new MutableLiveData<>();
        mainViewModel.makeRetrofitRequest(AdmissionRetrofit.getApi(1).getAlmatyDistricts(),almatyDistrictMutableLiveData);
        almatyDistrictMutableLiveData.observe(this, strings -> {
            ArrayAdapter<IdAndTitle> arrayAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_dropdown_item,strings);
            fragmentResidenceInfoBinding.almatyDistrictsSpinner.setAdapter(arrayAdapter);
            int position = arrayAdapter.getPosition(getIdAndTitleFromListById(strings,selectedId));
            fragmentResidenceInfoBinding.almatyDistrictsSpinner.setSelection(position);
        });

    }
    private void setRegion(int selectedId){
        MutableLiveData<List<IdAndTitle>> regionMutableLiveData = new MutableLiveData<>();
        mainViewModel.makeRetrofitRequest(AdmissionRetrofit.getApi(2).getRegions(),regionMutableLiveData);
        regionMutableLiveData.observe(this, strings -> {
            ArrayAdapter<IdAndTitle> arrayAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_dropdown_item,strings);
            fragmentResidenceInfoBinding.regionSpinner.setAdapter(arrayAdapter);
            int position = arrayAdapter.getPosition(getIdAndTitleFromListById(strings,selectedId));
            fragmentResidenceInfoBinding.regionSpinner.setSelection(position);
        });

        fragmentResidenceInfoBinding.regionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int parentId = ((IdAndTitle)fragmentResidenceInfoBinding.regionSpinner.getSelectedItem()).getId();
                mainViewModel.makeRetrofitRequest(AdmissionRetrofit.getApi(2).getPlaceByParentId(String.valueOf(parentId)),cityMutableLiveData);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    private void setCity(int selectedId){
        cityMutableLiveData.observe(this, strings -> {
            ArrayAdapter<IdAndTitle> arrayAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_dropdown_item,strings);
            fragmentResidenceInfoBinding.cityOrVillagePermanentSpinner.setAdapter(arrayAdapter);
            int position = arrayAdapter.getPosition(getIdAndTitleFromListById(strings,selectedId));
            fragmentResidenceInfoBinding.cityOrVillagePermanentSpinner.setSelection(position);
        });

        fragmentResidenceInfoBinding.cityOrVillagePermanentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int parentId = ((IdAndTitle)fragmentResidenceInfoBinding.cityOrVillagePermanentSpinner.getSelectedItem()).getId();
                mainViewModel.makeRetrofitRequest(AdmissionRetrofit.getApi(2).getPlaceByParentId(String.valueOf(parentId)),areaMutableLiveData);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void setArea(int selectedId){
        areaMutableLiveData.observe(this, strings -> {
            ArrayAdapter<IdAndTitle> arrayAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_dropdown_item,strings);
            fragmentResidenceInfoBinding.areaSpinner.setAdapter(arrayAdapter);
            int position = arrayAdapter.getPosition(getIdAndTitleFromListById(strings,selectedId));
            fragmentResidenceInfoBinding.areaSpinner.setSelection(position);
        });
    }

    public void saveResidenceInfo(ResidenceInfo residenceInfo){
        residenceInfo.setDistrictInAlmatyId(((IdAndTitle)fragmentResidenceInfoBinding.almatyDistrictsSpinner.getSelectedItem()).getId());
        residenceInfo.setCountryId(((IdAndTitle)fragmentResidenceInfoBinding.countriesSpinner.getSelectedItem()).getId());
        residenceInfo.setNeedInAccomodation(fragmentResidenceInfoBinding.dormitoryCheckBox.isChecked());
        residenceInfo.setHosterPrivelegeID(fragmentResidenceInfoBinding.dormitoryCheckBox.isChecked() ? ((IdAndTitle)fragmentResidenceInfoBinding.hostelPrivelegesSpinner.getSelectedItem()).getId() : null);
        residenceInfo.setRegion((IdAndTitle)fragmentResidenceInfoBinding.regionSpinner.getSelectedItem());
        residenceInfo.setCityOrVillagePermanent((IdAndTitle)fragmentResidenceInfoBinding.cityOrVillagePermanentSpinner.getSelectedItem());
        residenceInfo.setArea((IdAndTitle)fragmentResidenceInfoBinding.areaSpinner.getSelectedItem());

        residenceInfoViewModel.saveResidenceInfo(residenceInfo);
    }
}
