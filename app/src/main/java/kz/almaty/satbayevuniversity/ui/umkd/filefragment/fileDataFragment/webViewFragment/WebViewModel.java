package kz.almaty.satbayevuniversity.ui.umkd.filefragment.fileDataFragment.webViewFragment;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.File;

import kz.almaty.satbayevuniversity.data.App;
import kz.almaty.satbayevuniversity.utils.Storage;

public class WebViewModel extends ViewModel {
    private static final String TAG = "WebViewViewModel";
    private MutableLiveData<File> docFile = new MutableLiveData<>();
    public ObservableBoolean loadPB = new ObservableBoolean();


    void getFileFromServer(){
        String fileName = Storage.getInstance().getFileName();
        File file = new File(App.getContext().getExternalFilesDir(null), fileName);
        DownloadManager downloadManager = (DownloadManager)App.getContext().getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse("http://ssomobile.satbayev.university/api/File/Download?fileID=" + Storage.getInstance().getCourseId());
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setDestinationInExternalFilesDir(App.getContext(),null,fileName);
        downloadManager.enqueue(request);
        docFile.setValue(file);
    }
    MutableLiveData<File> getDownloadFileLiveData(){
        if (docFile == null){
            docFile = new MutableLiveData<>();
        }
        return docFile;
    }

}
