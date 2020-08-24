package kz.almaty.satbayevuniversity.ui.academicProgress.detailFragment;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import kz.almaty.satbayevuniversity.utils.Storage;
import kz.almaty.satbayevuniversity.data.entity.academic.ResponseJournal;

public class DetailViewModel extends ViewModel {
    public ObservableField<ResponseJournal> responseJournalObservableField = new ObservableField<>();

    public DetailViewModel() {
        responseJournalObservableField.set(Storage.getInstance().getResponseJournal());
        responseJournalObservableField.notifyChange();
    }

}
