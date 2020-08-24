package kz.almaty.satbayevuniversity.ui.grade.transcriptFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;

import kz.almaty.satbayevuniversity.R;
import kz.almaty.satbayevuniversity.databinding.FragmentTranscriptDialogBinding;

public class TranscriptDialogFragment extends DialogFragment {

    private TranscriptDialogViewModel mViewModel;
    private FragmentTranscriptDialogBinding transcriptDialogFragmentBinding;


    public static TranscriptDialogFragment newInstance() {
        return new TranscriptDialogFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        transcriptDialogFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_transcript_dialog, container, false);
        View view = transcriptDialogFragmentBinding.getRoot();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(TranscriptDialogViewModel.class);
        // TODO: Use the ViewModel
        transcriptDialogFragmentBinding.setViewModel(new TranscriptDialogViewModel());
    }

}
