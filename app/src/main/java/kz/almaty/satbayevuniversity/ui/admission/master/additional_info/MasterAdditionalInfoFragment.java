package kz.almaty.satbayevuniversity.ui.admission.master.additional_info;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

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
import kz.almaty.satbayevuniversity.data.entity.admission.additional_info_master.AdditionalInfo;
import kz.almaty.satbayevuniversity.data.network.AdmissionRetrofit;
import kz.almaty.satbayevuniversity.databinding.FragmentAdditionalInfoBinding;
import kz.almaty.satbayevuniversity.ui.admission.MainViewModel;
import kz.almaty.satbayevuniversity.ui.admission.bachelor.main_info.MainInfoViewModel;
import kz.almaty.satbayevuniversity.utils.Utils;

public class MasterAdditionalInfoFragment extends Fragment {
    FragmentAdditionalInfoBinding fragmentAdditionalInfoBinding;
    AdditionalInfoViewModel additionalInfoViewModel;
    MainViewModel mainViewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentAdditionalInfoBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_additional_info,container,false);
        fragmentAdditionalInfoBinding.setHandler(this);

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        additionalInfoViewModel = ViewModelProviders.of(this).get(AdditionalInfoViewModel.class);
        additionalInfoViewModel.getResponseMutableLiveData().observe(this, s -> {
            Toast.makeText(getContext(),s,Toast.LENGTH_SHORT).show();
        });
        setCheckBoxes();

        additionalInfoViewModel.getAdditionalInfo();
        additionalInfoViewModel.getAdditionalInfoMutableLiveData().observe(this, additionalInfo -> {
            fragmentAdditionalInfoBinding.setAdditionalInfo(additionalInfo);
            fragmentAdditionalInfoBinding.progressBar.setVisibility(View.GONE);

            setImageDocument(additionalInfo.getPassportDocumentID(),fragmentAdditionalInfoBinding.documentTranslationImageView1);
            setImageDocument(additionalInfo.getDiplomaDocumentID(),fragmentAdditionalInfoBinding.documentTranslationImageView2);
            setImageDocument(additionalInfo.getNostrificationDocumentID(),fragmentAdditionalInfoBinding.nostrificationImageView);
            setDisabilityStatuses(additionalInfo.getHealthInfoId());
        });
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getString(R.string.additional));
        return fragmentAdditionalInfoBinding.getRoot();
    }

    public static MasterAdditionalInfoFragment newInstance(){
        return new MasterAdditionalInfoFragment();
    }
    private void setDisabilityStatuses(int selectedId){
        MutableLiveData<List<IdAndTitle>> disabilityStatuses = new MutableLiveData<>();
        mainViewModel.makeRetrofitRequest(AdmissionRetrofit.getApi(1).getDisabilityStatuses(),disabilityStatuses);
        disabilityStatuses.observe(this, strings -> {
            ArrayAdapter<IdAndTitle> arrayAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_dropdown_item,strings);
            fragmentAdditionalInfoBinding.disabilityStatusesSpinner.setAdapter(arrayAdapter);
            int position = arrayAdapter.getPosition(Utils.getIdAndTitleFromListById(strings,selectedId));
            fragmentAdditionalInfoBinding.disabilityStatusesSpinner.setSelection(position);
        });
    }
    private void setImageDocument(int  documentId, ImageView imageView){
        MutableLiveData<Bitmap> bitmapMutableLiveData = new MutableLiveData<>();
        additionalInfoViewModel.getDocumentImage(String.valueOf(documentId),bitmapMutableLiveData);
        bitmapMutableLiveData.observe(this, bitmap -> {
            MainInfoViewModel.loadImage(imageView,bitmap);
            imageView.setOnClickListener(v -> Utils.openFullImage(bitmap,getContext()));
        });
    }
    private void setCheckBoxes(){
        fragmentAdditionalInfoBinding.documentTranslationsCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            fragmentAdditionalInfoBinding.documentsTranslationsLinearLayout.setVisibility(isChecked ? View.VISIBLE : View.GONE);
        });

        fragmentAdditionalInfoBinding.nostrificationCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            fragmentAdditionalInfoBinding.nostrificationLinearLayout.setVisibility(isChecked ? View.VISIBLE : View.GONE);
        });
    }
    public void saveAdditionalInfo(AdditionalInfo additionalInfo){
        additionalInfo.setNotarizedTranslationOfPassportOrDiploma(fragmentAdditionalInfoBinding.documentTranslationsCheckBox.isChecked());
        additionalInfo.setSubmittedNostrification(fragmentAdditionalInfoBinding.nostrificationCheckBox.isChecked());
        additionalInfo.setHealthInfoId(((IdAndTitle)fragmentAdditionalInfoBinding.disabilityStatusesSpinner.getSelectedItem()).getId());
        additionalInfoViewModel.saveAdditionalInfo(additionalInfo);


    }
}
