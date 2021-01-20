package kz.almaty.satbayevuniversity.ui.umkd.estimateteacher;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import kz.almaty.satbayevuniversity.R;
import kz.almaty.satbayevuniversity.data.SharedPrefCache;
import kz.almaty.satbayevuniversity.data.entity.umkd.Umkd;
import kz.almaty.satbayevuniversity.ui.HomeActivity;
import kz.almaty.satbayevuniversity.ui.umkd.UmkdAdapter;
import kz.almaty.satbayevuniversity.ui.umkd.filefragment.FileFragment;
import kz.almaty.satbayevuniversity.utils.Storage;

public class EstimateTeacherBottomShitDialog extends BottomSheetDialogFragment {
    private Context context;
    public EstimateTeacherBottomShitDialog(){}
    public EstimateTeacherBottomShitDialog(Context context) {this.context = context;}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.modal_bottom_sheet, container, false);
        LinearLayout files = (LinearLayout) v.findViewById(R.id.files);
        LinearLayout estimate = (LinearLayout) v.findViewById(R.id.estimate);
        files.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UmkdAdapter temp = new UmkdAdapter();
                Umkd umkd = temp.getUmkd();
                FileFragment fileFragment = new FileFragment();
                Storage.getInstance().setCourseCode(umkd.getCourseCode());
                Storage.getInstance().setInstructorID(String.valueOf(umkd.getInstructorId()));
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fileFragment, "fileFragment")
                        .addToBackStack("")
                        .commit();
                hideDialog();
            }
        });
        estimate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UmkdAdapter adapter = new UmkdAdapter();
                int instructorId = adapter.getUmkd().getInstructorId();
                EstimateTeacherActivity es = new EstimateTeacherActivity();
                es.getRating(instructorId);
                es.getMutableData().observe(getActivity(), data->{
                    //Log.d("testLivedata", "onClick: " + data.get(0).getDescription());
                    //String desc = data.get(0).getDescription();
                    //double rating = data.get(0).getRating();
                    if (!data.isEmpty()){
                        Toast.makeText(getContext(), R.string.already_appreciated, Toast.LENGTH_SHORT).show();
                    }else{
                        Intent in = new Intent(getActivity(), EstimateTeacherActivity.class);
                        startActivity(in);
                        hideDialog();
                    }
                });
            }
        });
        return v;
    }

    private void hideDialog(){
        this.dismiss();
    }
}
