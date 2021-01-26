package kz.almaty.satbayevuniversity.ui.grade.transcriptFragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;

import kz.almaty.satbayevuniversity.R;
import kz.almaty.satbayevuniversity.data.AccountDao;
import kz.almaty.satbayevuniversity.data.App;
import kz.almaty.satbayevuniversity.data.AppDatabase;
import kz.almaty.satbayevuniversity.data.SharedPrefCache;
import kz.almaty.satbayevuniversity.data.entity.Language;
import kz.almaty.satbayevuniversity.data.entity.grade.transcript.SemestersItem;
import kz.almaty.satbayevuniversity.databinding.FragmentTranscriptBinding;

public class TranscriptFragment extends Fragment {

    private FragmentTranscriptBinding transcriptFragmentBinding;
    private TranscriptAdapter transcriptAdapter;
    private AppDatabase db = App.getInstance().getDatabase();
    private AccountDao accountDao = db.accountDao();
    private TranscriptViewModel mViewModel;
    public static TranscriptFragment newInstance() {
        return new TranscriptFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        transcriptFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_transcript, container, false);
        View view = transcriptFragmentBinding.getRoot();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(TranscriptViewModel.class);
        transcriptFragmentBinding.setTranscript(mViewModel);


        transcriptFragmentBinding.transcriptRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        transcriptFragmentBinding.transcriptRecyclerView.setHasFixedSize(true);
        transcriptFragmentBinding.transcriptRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        transcriptAdapter = new TranscriptAdapter(getActivity());
        transcriptFragmentBinding.transcriptRecyclerView.setAdapter(transcriptAdapter);


        String lang = getResources().getConfiguration().locale.toString();
        getFromServer(lang);


        mViewModel.getTranscriptLiveData().observe(getViewLifecycleOwner(), semestersItems -> {
            ArrayList<Object> objects = new ArrayList<>(semestersItems.size() * 8);
            for (SemestersItem semestersItem: semestersItems){
                objects.add(semestersItem);
                objects.addAll(semestersItem.getCourses());
            }
            transcriptAdapter.setSemestersItemsList(objects);
        });
        mViewModel.getHandleTimeout().observe(getViewLifecycleOwner(), aBoolean -> {
            if (aBoolean) {
                Toast.makeText(getActivity(), R.string.internetConnection, Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void getFromServer(String lang){
        if (lang.equals("kk")){
            mViewModel.getTranscript("kz");
        }else {
            mViewModel.getTranscript("ru");
        }
    }
}
