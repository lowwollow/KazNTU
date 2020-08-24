package kz.almaty.satbayevuniversity.ui.admission.bachelor.main_info;

import android.app.DatePickerDialog;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProviders;

import java.io.File;
import java.util.List;

import kz.almaty.satbayevuniversity.R;
import kz.almaty.satbayevuniversity.data.entity.admission.IdAndTitle;
import kz.almaty.satbayevuniversity.data.entity.admission.main_info.MainInfo;
import kz.almaty.satbayevuniversity.data.network.AdmissionRetrofit;
import kz.almaty.satbayevuniversity.databinding.FragmentMainInfoBinding;
import kz.almaty.satbayevuniversity.ui.admission.MainViewModel;
import kz.almaty.satbayevuniversity.ui.admission.login.LoginViewModel;
import kz.almaty.satbayevuniversity.ui.admission.bachelor.ImageDialog;
import kz.almaty.satbayevuniversity.utils.Utils;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static kz.almaty.satbayevuniversity.utils.Utils.getIdAndTitleFromListById;

public class MainInfoFragment extends Fragment {
    FragmentMainInfoBinding fragmentMainInfoBinding;
    MainViewModel mainViewModel;
    ImageDialog imageDialog;
    MainInfoViewModel mainInfoViewModel;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentMainInfoBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_main_info,container,false);
        fragmentMainInfoBinding.setHandler(this);
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainInfoViewModel = ViewModelProviders.of(this).get(MainInfoViewModel.class);
        fragmentMainInfoBinding.setViewModel(mainInfoViewModel);

        mainInfoViewModel.getMainInfo();
        mainInfoViewModel.getMainInfoMutableLiveData().observe(this, mainInfo -> {
            fragmentMainInfoBinding.setMainInfo(mainInfo);
            fragmentMainInfoBinding.progressBar.setVisibility(View.GONE);

            if(mainInfo.getIdCardPhotos()!=null){
                if(mainInfo.getIdCardPhotos().size()==1){
                    mainInfoViewModel.getDocumentImage(mainInfo.getIdCardPhotos().get(0).getDocumentID(),1);
                }else if(mainInfo.getIdCardPhotos().size()==2){
                    mainInfoViewModel.getDocumentImage(mainInfo.getIdCardPhotos().get(0).getDocumentID(),1);
                    mainInfoViewModel.getDocumentImage(mainInfo.getIdCardPhotos().get(1).getDocumentID(),2);
                }
            }
            setCitizenCategory(mainInfo.getCitizenCategory());
            setCountries(mainInfo.getCitizenship());
            setNationalities(mainInfo.getNationality());
            setMaritalStatuses(mainInfo.getMaritalStatus());
            setDocumentTypes(mainInfo.getDocumentType());
            if(mainInfo.getDocumentIssueOrganization() != null){
                setDocumentIssueOrganizations(mainInfo.getDocumentIssueOrganization());
            }else{
                setDocumentIssueOrganizations(0);
            }
            setHearAboutTypes(mainInfo.getHearAboutId());
        });
        setPhotos();

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getString(R.string.main_info));
        return fragmentMainInfoBinding.getRoot();

    }

    public void openFullImage(Bitmap bitmap){
        Utils.openFullImage(bitmap,getContext());
    }

    public static MainInfoFragment newInstance(){
       return new MainInfoFragment();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void openDatePickerDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext());
        DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                fragmentMainInfoBinding.dateOfBirthEditText.setText(dayOfMonth+"."+month+"."+year);
            }
        };
        datePickerDialog.setOnDateSetListener(onDateSetListener);
        datePickerDialog.show();
    }

    private void setCitizenCategory(int selectedId){
        MutableLiveData<List<IdAndTitle>> citizenCategoryMutableLiveData = new MutableLiveData<>();
        mainViewModel.makeRetrofitRequest(AdmissionRetrofit.getApi(1).getCitizenCategory(),citizenCategoryMutableLiveData);
        citizenCategoryMutableLiveData.observe(this, strings -> {
            ArrayAdapter<IdAndTitle> arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, strings);
            fragmentMainInfoBinding.citizenCategorySpinner.setAdapter(arrayAdapter);
            int position = arrayAdapter.getPosition(getIdAndTitleFromListById(strings,selectedId));
            fragmentMainInfoBinding.citizenCategorySpinner.setSelection(position);

        });

        fragmentMainInfoBinding.citizenCategorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                IdAndTitle idAndTitle = (IdAndTitle) fragmentMainInfoBinding.citizenCategorySpinner.getSelectedItem();
                switch (idAndTitle.getId()){
                    case 1:
                    case 3:
                        fragmentMainInfoBinding.citizenshipLayout.setVisibility(View.VISIBLE);
                        fragmentMainInfoBinding.documentIssueOrganizationsEditText.setVisibility(View.GONE);
                        fragmentMainInfoBinding.documentIssueOrganizationsSpinner.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        fragmentMainInfoBinding.citizenshipLayout.setVisibility(View.GONE);
                        fragmentMainInfoBinding.documentIssueOrganizationsEditText.setVisibility(View.GONE);
                        fragmentMainInfoBinding.documentIssueOrganizationsSpinner.setVisibility(View.VISIBLE);
                        break;
                    case 4:
                    case 5:
                        fragmentMainInfoBinding.citizenshipLayout.setVisibility(View.VISIBLE);
                        fragmentMainInfoBinding.documentIssueOrganizationsEditText.setVisibility(View.VISIBLE);
                        fragmentMainInfoBinding.documentIssueOrganizationsSpinner.setVisibility(View.GONE);
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    private void setCountries(int selectedId){
        MutableLiveData<List<IdAndTitle>> countryMutableLiveData = new MutableLiveData<>();
        mainViewModel.makeRetrofitRequest(AdmissionRetrofit.getApi(1).getCountries(),countryMutableLiveData);
        countryMutableLiveData.observe(this, strings -> {
            ArrayAdapter<IdAndTitle> arrayAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_dropdown_item,strings);
            fragmentMainInfoBinding.citizenCountriesSpinner.setAdapter(arrayAdapter);
            int position = arrayAdapter.getPosition(getIdAndTitleFromListById(strings,selectedId));
            fragmentMainInfoBinding.citizenCountriesSpinner.setSelection(position);
        });
    }

    private void setNationalities(int selectedId){
        MutableLiveData<List<IdAndTitle>> nationalitiesMutableLiveData = new MutableLiveData<>();
        mainViewModel.makeRetrofitRequest(AdmissionRetrofit.getApi(1).getNationalities(),nationalitiesMutableLiveData);
        nationalitiesMutableLiveData.observe(this, strings -> {
            ArrayAdapter<IdAndTitle> arrayAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_dropdown_item,strings);
            fragmentMainInfoBinding.nationalitiesSpinner.setAdapter(arrayAdapter);
            int position = arrayAdapter.getPosition(getIdAndTitleFromListById(strings,selectedId));
            fragmentMainInfoBinding.nationalitiesSpinner.setSelection(position);
        });
    }

    private void setMaritalStatuses(int selectedId){
        MutableLiveData<List<IdAndTitle>> maritalStatusMutableLiveData = new MutableLiveData<>();
        mainViewModel.makeRetrofitRequest(AdmissionRetrofit.getApi(1).getMaritalStatuses(),maritalStatusMutableLiveData);
        maritalStatusMutableLiveData.observe(this, strings -> {
            ArrayAdapter<IdAndTitle> arrayAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_dropdown_item,strings);
            fragmentMainInfoBinding.maritalStatusesSpinner.setAdapter(arrayAdapter);
            int position = arrayAdapter.getPosition(getIdAndTitleFromListById(strings,selectedId));
            fragmentMainInfoBinding.maritalStatusesSpinner.setSelection(position);
        });
    }

    private void setDocumentTypes(int selectedId){
        MutableLiveData<List<IdAndTitle>> documentTypeMutableLiveData = new MutableLiveData<>();
        mainViewModel.makeRetrofitRequest(AdmissionRetrofit.getApi(1).getDocumentTypes(),documentTypeMutableLiveData);
        documentTypeMutableLiveData.observe(this, strings -> {
            ArrayAdapter<IdAndTitle> arrayAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_dropdown_item,strings);
            fragmentMainInfoBinding.documentTypesSpinner.setAdapter(arrayAdapter);
            int position = arrayAdapter.getPosition(getIdAndTitleFromListById(strings,selectedId));
            fragmentMainInfoBinding.documentTypesSpinner.setSelection(position);
        });
    }

    private void setDocumentIssueOrganizations(int selectedId){
        MutableLiveData<List<IdAndTitle>> documentIssueOrgsMutableLiveData = new MutableLiveData<>();
        mainViewModel.makeRetrofitRequest(AdmissionRetrofit.getApi(1).getDocumentIssueOrgs(),documentIssueOrgsMutableLiveData);
        documentIssueOrgsMutableLiveData.observe(this, strings -> {
            ArrayAdapter<IdAndTitle> arrayAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_dropdown_item,strings);
            fragmentMainInfoBinding.documentIssueOrganizationsSpinner.setAdapter(arrayAdapter);
            int position = arrayAdapter.getPosition(getIdAndTitleFromListById(strings,selectedId));
            fragmentMainInfoBinding.documentIssueOrganizationsSpinner.setSelection(position);
        });
    }

    private void setHearAboutTypes(int selectedId){
        MutableLiveData<List<IdAndTitle>> hearAboutTypesMutableLiveData = new MutableLiveData<>();
        mainViewModel.makeRetrofitRequest(AdmissionRetrofit.getApi(1).getHearAboutTypes(),hearAboutTypesMutableLiveData);
        hearAboutTypesMutableLiveData.observe(this, strings -> {
            ArrayAdapter<IdAndTitle> arrayAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_dropdown_item,strings);
            fragmentMainInfoBinding.hearAboutTypesSpinner.setAdapter(arrayAdapter);
            int position = arrayAdapter.getPosition(getIdAndTitleFromListById(strings,selectedId));
            fragmentMainInfoBinding.hearAboutTypesSpinner.setSelection(position);
        });
    }
    private void setPhotos(){
        LoginViewModel authViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        authViewModel.getImageUrl();
        authViewModel.getBitmap().observe(this, bitmap -> {
            fragmentMainInfoBinding.setPhoto(bitmap);
        });

        mainInfoViewModel.getFirstBitmapMutableLiveData().observe(this, bitmap -> {
            fragmentMainInfoBinding.setFirstBitmap(bitmap);
        });
        mainInfoViewModel.getSecondBitmapMutableLiveData().observe(this, bitmap -> {
            fragmentMainInfoBinding.setSecondBitmap(bitmap);
        });
    }

    public void saveMainInfo(MainInfo mainInfo){
        mainInfo.setCellphone(fragmentMainInfoBinding.cellphoneEditText.getRawText());
        mainInfo.setMale(fragmentMainInfoBinding.maleRadioButton.isChecked());
        mainInfo.setCitizenCategory(((IdAndTitle)fragmentMainInfoBinding.citizenCategorySpinner.getSelectedItem()).getId());
        if(fragmentMainInfoBinding.citizenCountriesSpinner.getSelectedItem() != null){
            mainInfo.setCitizenship(((IdAndTitle)fragmentMainInfoBinding.citizenCountriesSpinner.getSelectedItem()).getId());
        }
        mainInfo.setNationality(((IdAndTitle)fragmentMainInfoBinding.nationalitiesSpinner.getSelectedItem()).getId());
        mainInfo.setMaritalStatus(((IdAndTitle)fragmentMainInfoBinding.maritalStatusesSpinner.getSelectedItem()).getId());
        mainInfo.setDocumentType(((IdAndTitle)fragmentMainInfoBinding.documentTypesSpinner.getSelectedItem()).getId());
        mainInfo.setDocumentIssueOrganization(((IdAndTitle)fragmentMainInfoBinding.documentIssueOrganizationsSpinner.getSelectedItem()).getId());
        mainInfo.setHearAboutId(((IdAndTitle)fragmentMainInfoBinding.hearAboutTypesSpinner.getSelectedItem()).getId());


        File file = new File("/storage/emulated/0/Download/Corrections 6.jpg");
        Toast.makeText(getContext(), "isExist = "+file.exists(), Toast.LENGTH_SHORT).show();

        RequestBody requestFile =  RequestBody.create(MediaType.parse("image"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(), requestFile);
        RequestBody fullName = RequestBody.create(MediaType.parse("multipart/form-data"), "Your Name");
        mainInfo.setPhoto(body);


        mainInfoViewModel.saveMainInfo(mainInfo);
    }

}
