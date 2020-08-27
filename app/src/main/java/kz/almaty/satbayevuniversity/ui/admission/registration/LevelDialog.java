package kz.almaty.satbayevuniversity.ui.admission.registration;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import kz.almaty.satbayevuniversity.R;
import kz.almaty.satbayevuniversity.data.App;
import kz.almaty.satbayevuniversity.databinding.DialogDegreeBinding;
import kz.almaty.satbayevuniversity.ui.admission.login.LoginActivity;

import static android.content.Context.MODE_PRIVATE;

public class LevelDialog extends DialogFragment {
    DialogDegreeBinding dialogDegreeBinding;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        dialogDegreeBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()),R.layout.dialog_degree,null,false);
        dialogDegreeBinding.setHandler(this);
        builder.setView(dialogDegreeBinding.getRoot());
        return builder.create();
    }

    public void startRegistration(int levelId){
        dismiss();
        Intent i = new Intent(getContext(), RegistrationActivity.class);
        startActivity(i);
        SharedPreferences.Editor editor = App.getContext().getSharedPreferences("registration",MODE_PRIVATE).edit();
        editor.putInt("levelId",levelId);
        editor.apply();
    }

}
