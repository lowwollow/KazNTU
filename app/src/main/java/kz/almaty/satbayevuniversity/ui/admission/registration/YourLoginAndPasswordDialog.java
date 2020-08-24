package kz.almaty.satbayevuniversity.ui.admission.registration;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import kz.almaty.satbayevuniversity.R;
import kz.almaty.satbayevuniversity.data.entity.admission.registration.Account;
import kz.almaty.satbayevuniversity.ui.admission.bachelor.BachelorActivity;
import kz.almaty.satbayevuniversity.ui.admission.bachelor.main_info.MainInfoFragment;
import kz.almaty.satbayevuniversity.ui.admission.doctor.DoctorActivity;
import kz.almaty.satbayevuniversity.ui.admission.master.MasterActivity;

public class YourLoginAndPasswordDialog extends DialogFragment {
    Account account;
    public YourLoginAndPasswordDialog(Account account){
        this.account = account;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(getResources().getString(R.string.your_login_and_password,account.getLogin(),account.getPassword()));
        builder.setPositiveButton("ОК", (dialog, which) -> {
                startActivity();
             });
        return builder.create();
    }
    private void startActivity(){
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("registration", Context.MODE_PRIVATE);
        int levelId = sharedPreferences.getInt("levelId",1);
        Intent intent = new Intent(getContext(),BachelorActivity.class);
        switch (levelId){
            case 1:
                intent = new Intent(getContext(),BachelorActivity.class);
                break;
            case 2:
                intent = new Intent(getContext(), MasterActivity.class);
                break;
            case 3:
                intent = new Intent(getContext(), DoctorActivity.class);
                break;
        }
        startActivity(intent);
        getActivity().finish();
    }
}
