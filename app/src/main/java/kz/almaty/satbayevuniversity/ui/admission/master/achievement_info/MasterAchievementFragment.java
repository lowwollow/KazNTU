package kz.almaty.satbayevuniversity.ui.admission.master.achievement_info;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableBoolean;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.ArrayList;
import java.util.List;

import kz.almaty.satbayevuniversity.AuthViewModel;
import kz.almaty.satbayevuniversity.R;
import kz.almaty.satbayevuniversity.data.entity.admission.IdAndTitle;
import kz.almaty.satbayevuniversity.data.entity.admission.achievement_info_master.AchievementInfo;
import kz.almaty.satbayevuniversity.data.entity.admission.achievement_info_master.ScienceWork;
import kz.almaty.satbayevuniversity.data.entity.admission.achievement_info_master.ScienceWorkScan;
import kz.almaty.satbayevuniversity.data.entity.admission.additional_info_master.AdditionalInfo;
import kz.almaty.satbayevuniversity.data.network.AdmissionRetrofit;
import kz.almaty.satbayevuniversity.databinding.FragmentMasterAchievementInfoBinding;
import kz.almaty.satbayevuniversity.databinding.LayoutIeltsBinding;
import kz.almaty.satbayevuniversity.databinding.LayoutImageViewBinding;
import kz.almaty.satbayevuniversity.databinding.LayoutScientificWorkBinding;
import kz.almaty.satbayevuniversity.ui.admission.MainViewModel;
import kz.almaty.satbayevuniversity.ui.admission.master.additional_info.AdditionalInfoViewModel;
import kz.almaty.satbayevuniversity.utils.Utils;

import static kz.almaty.satbayevuniversity.ui.admission.bachelor.main_info.MainInfoViewModel.loadImage;

public class MasterAchievementFragment extends Fragment  {
    FragmentMasterAchievementInfoBinding fragmentAchievementInfoBinding;
    AchievementInfo achievementInfo;
    MasterAchievementInfoViewModel masterAchievementInfoViewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentAchievementInfoBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_master_achievement_info, container, false);
        fragmentAchievementInfoBinding.setHandler(this);
        setCheckBoxes();

        masterAchievementInfoViewModel = ViewModelProviders.of(this).get(MasterAchievementInfoViewModel.class);
        masterAchievementInfoViewModel.getResponseMutableLiveData().observe(this, s -> {
            Toast.makeText(getContext(),s,Toast.LENGTH_SHORT).show();
        });
        masterAchievementInfoViewModel.getAchievementInfo();
        masterAchievementInfoViewModel.getAchievementInfoMutableLiveData().observe(this, achievementInfo -> {
            this.achievementInfo = achievementInfo;
            fragmentAchievementInfoBinding.setAchievementInfo(achievementInfo);

            if(achievementInfo.getToeflCert() != null && achievementInfo.getToeflCert().getToeflTypeId() !=null){
                setToefl(achievementInfo.getToeflCert().getToeflTypeId());
            }
            for(ScienceWork scienceWork : achievementInfo.getScienceWorks()){
                addScientificWork(scienceWork);
            }
            setCertificateImage(fragmentAchievementInfoBinding.ieltsLinearLayout.ieltsImageView,achievementInfo.getIeltsCert().getDocumentID());
            setCertificateImage(fragmentAchievementInfoBinding.toeflLinearLayout.toeflImageView,achievementInfo.getToeflCert().getDocumentID());
        });

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getString(R.string.achievement));
        return fragmentAchievementInfoBinding.getRoot();
    }

    public static MasterAchievementFragment newInstance() {
        return new MasterAchievementFragment();
    }

    public void addScientificWork(ScienceWork scienceWork){
        LayoutScientificWorkBinding layoutScientificWorkBinding = DataBindingUtil.inflate(getLayoutInflater(),R.layout.layout_scientific_work,null,false);

        if(scienceWork.getScienceWorkScan() != null){
            for(ScienceWorkScan scienceWorkScan : scienceWork.getScienceWorkScan()){
                View view = addImageDocuments(scienceWorkScan.getDocumentID());
                layoutScientificWorkBinding.scienceWorkImagesLayout.addView(view);
            }
        }
        if(scienceWork.getDoiScan() != null){
            for(ScienceWorkScan doIScan : scienceWork.getDoiScan()){
                View view = addImageDocuments(doIScan.getDocumentID());
                layoutScientificWorkBinding.doiImagesLayout.addView(view);
            }
        }

        layoutScientificWorkBinding.setScienceWork(scienceWork);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0,20,0,0);

        layoutScientificWorkBinding.closeScientificWork.setOnClickListener(v -> {
            fragmentAchievementInfoBinding.scientificWorksLayout.removeView(layoutScientificWorkBinding.getRoot());
            if(fragmentAchievementInfoBinding.scientificWorksLayout.getChildCount()==0 && fragmentAchievementInfoBinding.hasScienceWorkCheckBox.isChecked()){
                fragmentAchievementInfoBinding.hasScienceWorkCheckBox.setChecked(false);
            }
        });
        fragmentAchievementInfoBinding.scientificWorksLayout.addView(layoutScientificWorkBinding.getRoot(),layoutParams);
        Toast.makeText(getContext(),"Добавлен новый научный труд",Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("RestrictedApi")
    private void setCheckBoxes(){
            fragmentAchievementInfoBinding.hasScienceWorkCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                fragmentAchievementInfoBinding.addScientificWorkButton.setVisibility(isChecked ? View.VISIBLE : View.GONE);
                if (isChecked && fragmentAchievementInfoBinding.scientificWorksLayout.getChildCount() == 0){
                    addNewScienceWork();
                }else if(!isChecked){
                    fragmentAchievementInfoBinding.scientificWorksLayout.removeAllViews();
                    achievementInfo.getScienceWorks().clear();
                }
            });

        fragmentAchievementInfoBinding.ieltsCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                fragmentAchievementInfoBinding.ieltsLinearLayout.getRoot().setVisibility(isChecked ? View.VISIBLE : View.GONE);
        });

        fragmentAchievementInfoBinding.toeflCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                fragmentAchievementInfoBinding.toeflLinearLayout.getRoot().setVisibility(isChecked ? View.VISIBLE : View.GONE);
        });
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

    private void setToefl(int selectedId){
        MainViewModel mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        MutableLiveData<List<IdAndTitle>> idAndTitleMutableLiveData = new MutableLiveData<>();
        mainViewModel.makeRetrofitRequest(AdmissionRetrofit.getApi(1).getToeflTypes(),idAndTitleMutableLiveData);
        idAndTitleMutableLiveData.observe(this, idAndTitles -> {
            ArrayAdapter<IdAndTitle> arrayAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_dropdown_item,idAndTitles);
            fragmentAchievementInfoBinding.toeflLinearLayout.toeflTypeSpinner.setAdapter(arrayAdapter);
            int position = arrayAdapter.getPosition(Utils.getIdAndTitleFromListById(idAndTitles,selectedId));
            fragmentAchievementInfoBinding.toeflLinearLayout.toeflTypeSpinner.setSelection(position);
        });
    }
    private void setCertificateImage(ImageView imageView,String documentId){
        AdditionalInfoViewModel additionalInfoViewModel = ViewModelProviders.of(this).get(AdditionalInfoViewModel.class);
        MutableLiveData<Bitmap> bitmapMutableLiveData = new MutableLiveData<>();
        additionalInfoViewModel.getDocumentImage(documentId,bitmapMutableLiveData);

        bitmapMutableLiveData.observe(this, bitmap -> {
            fragmentAchievementInfoBinding.progressBar.setVisibility(View.GONE);
            loadImage(imageView,bitmap);
            imageView.setOnClickListener(v -> {
                Utils.openFullImage(bitmap,getContext());
            });
        });
    }
    public void saveAchievementInfo(){
        achievementInfo.setHasScienceWorks(fragmentAchievementInfoBinding.hasScienceWorkCheckBox.isChecked());
        achievementInfo.getIeltsCert().setHas(fragmentAchievementInfoBinding.ieltsCheckBox.isChecked());
        achievementInfo.getToeflCert().setHas(fragmentAchievementInfoBinding.toeflCheckBox.isChecked());
        masterAchievementInfoViewModel.saveAchievementInfo(achievementInfo);
    }
    public void addNewScienceWork(){
        ScienceWork newScienceWork = new ScienceWork();
        achievementInfo.getScienceWorks().add(newScienceWork);
        addScientificWork(newScienceWork);
    }
}
