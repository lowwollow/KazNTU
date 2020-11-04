package kz.almaty.satbayevuniversity.ui.umkd.estimateteacher;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import kz.almaty.satbayevuniversity.R;
import kz.almaty.satbayevuniversity.ui.umkd.UmkdAdapter;
import kz.almaty.satbayevuniversity.ui.umkd.UmkdFragment;

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
        Button btn1 = v.findViewById(R.id.files);
        Button btn2 = v.findViewById(R.id.estimate);
        Bundle extras = getActivity().getIntent().getExtras();
        Log.d("intentVal", "onCreateView: " + extras);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // to do
            }
        });
        return v;
    }

}
