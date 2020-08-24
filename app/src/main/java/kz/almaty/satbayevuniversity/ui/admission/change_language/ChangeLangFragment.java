package kz.almaty.satbayevuniversity.ui.admission.change_language;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;

import kz.almaty.satbayevuniversity.R;
import kz.almaty.satbayevuniversity.data.entity.Language;

public class ChangeLangFragment extends DialogFragment {
    private ArrayList<Language> languages = new ArrayList<>();
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        return builder.create();
    }
}
