package kz.almaty.satbayevuniversity.ui.admission.bachelor.choosing_education;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import kz.almaty.satbayevuniversity.R;

public class ChoosingEducationProgrammFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getString(R.string.choosing_education_programm));
        View view = getLayoutInflater().inflate(R.layout.fragment_choosing_education_programm,container,false);
        return view;
    }

    public static ChoosingEducationProgrammFragment newInstance(){
        return new ChoosingEducationProgrammFragment();
    }
}
