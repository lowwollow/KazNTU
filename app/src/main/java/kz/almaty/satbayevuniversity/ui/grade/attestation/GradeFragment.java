package kz.almaty.satbayevuniversity.ui.grade.attestation;

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

import kz.almaty.satbayevuniversity.R;
import kz.almaty.satbayevuniversity.data.SharedPrefCache;
import kz.almaty.satbayevuniversity.data.entity.Language;
import kz.almaty.satbayevuniversity.databinding.FragmentGradeBinding;

public class GradeFragment extends Fragment {

    private GradeViewModel mViewModel;

    private FragmentGradeBinding gradeFragmentBinding;
    private AttestationAdapter attestationAdapter;
    public static GradeFragment newInstance() {
        return new GradeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        gradeFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_grade, container, false);
        View view = gradeFragmentBinding.getRoot();
        gradeFragmentBinding.emptyImage.setVisibility(View.INVISIBLE);
        gradeFragmentBinding.emptyTextView.setVisibility(View.INVISIBLE);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(GradeViewModel.class);

        gradeFragmentBinding.setGrade(mViewModel);

        gradeFragmentBinding.gradeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        gradeFragmentBinding.gradeRecyclerView.setHasFixedSize(true);
        gradeFragmentBinding.gradeRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));

        attestationAdapter = new AttestationAdapter();
        gradeFragmentBinding.gradeRecyclerView.setAdapter(attestationAdapter);

        String lang1 = SharedPrefCache.getLang(getActivity());
        getFromServer(lang1);

        mViewModel.getAttestationLiveDate().observe(getViewLifecycleOwner(), attestations -> {
            attestationAdapter.setAttestationList(attestations);
         });
        mViewModel.getHandleTimeout().observe(getViewLifecycleOwner(), aBoolean -> {
            if (aBoolean) {
                Toast.makeText(getActivity(), R.string.internetConnection, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getFromServer(String lang){
        if (lang.equals("kk")){
            mViewModel.getAttestation("kz");
        }else {
            mViewModel.getAttestation("ru");
        }
    }

    private void log(String tag, String text){
        Log.d(tag, text);
    }

}
