package kz.almaty.satbayevuniversity.ui.admission;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import kz.almaty.satbayevuniversity.R;

public class ContinueRegistrationDialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(getResources().getString(R.string.continue_registration_text));
        builder.setPositiveButton(getResources().getString(R.string.ok), (dialog, which) -> getActivity().finish());
        return builder.create();
    }


}
