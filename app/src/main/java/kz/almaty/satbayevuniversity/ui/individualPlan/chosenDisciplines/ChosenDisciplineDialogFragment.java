package kz.almaty.satbayevuniversity.ui.individualPlan.chosenDisciplines;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.DialogFragment;

import kz.almaty.satbayevuniversity.R;
import kz.almaty.satbayevuniversity.databinding.ChosenDisciplineItemBinding;
import kz.almaty.satbayevuniversity.databinding.FragmentDisciplineDialogBinding;

public class ChosenDisciplineDialogFragment extends DialogFragment {

    private FragmentDisciplineDialogBinding chosenDisciplineDialog;
    private ChosenDiscipline chosenDiscipline;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        chosenDisciplineDialog = DataBindingUtil.inflate(inflater, R.layout.fragment_discipline_dialog, container, false);
        chosenDisciplineDialog.setChosenDiscipline(chosenDiscipline);
        View view = chosenDisciplineDialog.getRoot();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void setChosenDisciplineDialog(ChosenDiscipline chosenDiscipline){
        this.chosenDiscipline = chosenDiscipline;
    }
}
