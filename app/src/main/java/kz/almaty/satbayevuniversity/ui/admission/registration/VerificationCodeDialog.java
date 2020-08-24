package kz.almaty.satbayevuniversity.ui.admission.registration;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import kz.almaty.satbayevuniversity.R;
import kz.almaty.satbayevuniversity.data.entity.admission.email_verification.Data;
import kz.almaty.satbayevuniversity.databinding.DialogVerificationCodeBinding;

public class VerificationCodeDialog extends DialogFragment {
    DialogVerificationCodeBinding dialogVerificationCodeBinding;
    RegistrationActivity registrationActivity;
    Data data;

    public VerificationCodeDialog(RegistrationActivity registrationActivity, Data data){
        this.registrationActivity = registrationActivity;
        this.data = data;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        dialogVerificationCodeBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()),R.layout.dialog_verification_code,null,false);
        dialogVerificationCodeBinding.setRegistrationActivity(registrationActivity);
        dialogVerificationCodeBinding.setData(data);
        builder.setView(dialogVerificationCodeBinding.getRoot());
        return builder.create();
    }
}
