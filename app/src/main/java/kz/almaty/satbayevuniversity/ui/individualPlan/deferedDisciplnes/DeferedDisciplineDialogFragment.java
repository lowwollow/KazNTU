package kz.almaty.satbayevuniversity.ui.individualPlan.deferedDisciplnes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import kz.almaty.satbayevuniversity.R;
import kz.almaty.satbayevuniversity.data.entity.individualPlan.deferedDiscipline.DeferredDiscipline1;
import kz.almaty.satbayevuniversity.databinding.DeferedDisciplineDialogBinding;

public class DeferedDisciplineDialogFragment extends DialogFragment {
    private DeferedDisciplineDialogBinding deferedDisciplineDialogFragment;
    private DeferredDiscipline1 deferedDiscipline;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        deferedDisciplineDialogFragment  = DataBindingUtil.inflate(inflater, R.layout.defered_discipline_dialog, container, false);
        deferedDisciplineDialogFragment.setDeferedDiscipline(deferedDiscipline);
        View view = deferedDisciplineDialogFragment.getRoot();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void setDeferedDiscipline(DeferredDiscipline1 deferedDiscipline){
        this.deferedDiscipline = deferedDiscipline;
    }
}
