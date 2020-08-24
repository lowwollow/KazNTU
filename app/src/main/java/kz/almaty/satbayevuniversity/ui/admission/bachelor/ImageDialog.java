package kz.almaty.satbayevuniversity.ui.admission.bachelor;

import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import kz.almaty.satbayevuniversity.R;
import kz.almaty.satbayevuniversity.databinding.DialogImageBinding;
import kz.almaty.satbayevuniversity.ui.admission.bachelor.main_info.MainInfoFragment;

public class ImageDialog extends DialogFragment{
    Bitmap bitmap;
    public ImageDialog(Bitmap bitmap){
        this.bitmap = bitmap;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        DialogImageBinding dialog_image = DataBindingUtil.inflate(inflater,R.layout.dialog_image,container,false);
        dialog_image.setBitmap(bitmap);
        dialog_image.setImageDialog(this);

        return dialog_image.getRoot();
    }


    @Override
    public void onResume() {
        super.onResume();

        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0)); }

        public void closeFullImage(){
            dismiss();
        }
}
