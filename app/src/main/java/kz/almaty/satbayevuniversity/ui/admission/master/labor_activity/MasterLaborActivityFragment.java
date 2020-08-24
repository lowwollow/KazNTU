package kz.almaty.satbayevuniversity.ui.admission.master.labor_activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.ArrayList;
import java.util.List;

import kz.almaty.satbayevuniversity.R;
import kz.almaty.satbayevuniversity.data.entity.admission.labor_activity_master.DocumentScan;
import kz.almaty.satbayevuniversity.data.entity.admission.labor_activity_master.LaborActivity;
import kz.almaty.satbayevuniversity.data.entity.admission.labor_activity_master.PlaceOfWork;
import kz.almaty.satbayevuniversity.databinding.FragmentLaborActivityBinding;
import kz.almaty.satbayevuniversity.databinding.LayoutImageViewBinding;
import kz.almaty.satbayevuniversity.databinding.LayoutPlaceOfWorkBinding;
import kz.almaty.satbayevuniversity.ui.admission.master.additional_info.AdditionalInfoViewModel;
import kz.almaty.satbayevuniversity.utils.Utils;

public class MasterLaborActivityFragment extends Fragment {
    FragmentLaborActivityBinding fragmentLaborActivityBinding;
    MasterLaborActivityViewModel masterLaborActivityViewModel;
    LaborActivity laborActivity;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentLaborActivityBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_labor_activity,container,false);
        fragmentLaborActivityBinding.setHandler(this);

        masterLaborActivityViewModel = ViewModelProviders.of(this).get(MasterLaborActivityViewModel.class);
        masterLaborActivityViewModel.getResponseMutableLiveData().observe(this, s -> {
            Toast.makeText(getContext(),s,Toast.LENGTH_SHORT).show();
        });
        masterLaborActivityViewModel.getLaborActivity();
        masterLaborActivityViewModel.getLaborActivityMutableLiveData().observe(this, laborActivity -> {
            this.laborActivity = laborActivity;
            fragmentLaborActivityBinding.progressBar.setVisibility(View.GONE);
            for(PlaceOfWork placeOfWork : laborActivity.getPlacesOfWork()){
                addPlaceOfWork(placeOfWork);
            }
            for(DocumentScan employmentHistoryScan : laborActivity.getEmploymentHistoryScan()){
                addDocumentImages(employmentHistoryScan);
            }

        });

        fragmentLaborActivityBinding.addPlaceOfWorkFAB.setOnClickListener(v -> {
            PlaceOfWork newPlaceOfWork = new PlaceOfWork();
            addPlaceOfWork(newPlaceOfWork);
            laborActivity.getPlacesOfWork().add(newPlaceOfWork);
        });
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getString(R.string.labor_activity));
        return fragmentLaborActivityBinding.getRoot();
    }

    public static MasterLaborActivityFragment newInstance(){
        return new MasterLaborActivityFragment();
    }

    public void addPlaceOfWork(PlaceOfWork placeOfWork){
        LayoutPlaceOfWorkBinding layoutPlaceOfWorkBinding  = DataBindingUtil.inflate(getLayoutInflater(),R.layout.layout_place_of_work,null,false);
        layoutPlaceOfWorkBinding.setPlaceOfWork(placeOfWork);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(0,20,0,0);
        layoutPlaceOfWorkBinding.getRoot().setLayoutParams(layoutParams);

        layoutPlaceOfWorkBinding.removePlaceOfWork.setOnClickListener(v -> {
            fragmentLaborActivityBinding.mainLayout.removeView(layoutPlaceOfWorkBinding.getRoot());
            laborActivity.getPlacesOfWork().remove(placeOfWork);
        });
        fragmentLaborActivityBinding.mainLayout.addView(layoutPlaceOfWorkBinding.getRoot());
    }
    private void addDocumentImages(DocumentScan employmentHistoryScan){
        AdditionalInfoViewModel additionalInfoViewModel = ViewModelProviders.of(this).get(AdditionalInfoViewModel.class);
        MutableLiveData<Bitmap> bitmapMutableLiveData = new MutableLiveData<>();
        additionalInfoViewModel.getDocumentImage(employmentHistoryScan.getDocumentID(),bitmapMutableLiveData);


        LayoutImageViewBinding layoutImageViewBinding = DataBindingUtil.inflate(getLayoutInflater(),R.layout.layout_image_view,null,false);
        fragmentLaborActivityBinding.employmentHistoryLayout.addView(layoutImageViewBinding.getRoot());

        bitmapMutableLiveData.observe(this, bitmap -> {
            layoutImageViewBinding.setBitmap(bitmap);
            layoutImageViewBinding.imageView.setOnClickListener(v -> {
                Utils.openFullImage(bitmap,getContext());
            });
        });
    }
    public void saveLaborActivity(){
        masterLaborActivityViewModel.saveLaborActivity(laborActivity);
    }
}
