package kz.almaty.satbayevuniversity.ui.grade.transcriptFragment;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import kz.almaty.satbayevuniversity.utils.Storage;
import kz.almaty.satbayevuniversity.data.entity.grade.transcript.SemestersItem;



public class TranscriptDialogViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    public ObservableField<SemestersItem> semestersItemObservableField = new ObservableField<>();

    public TranscriptDialogViewModel() {
        semestersItemObservableField.set(Storage.getInstance().getSemestersItem());
        semestersItemObservableField.notifyChange();
    }

}
