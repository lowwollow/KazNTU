package kz.almaty.satbayevuniversity.ui.academicProgress;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import kz.almaty.satbayevuniversity.AuthViewModel;
import kz.almaty.satbayevuniversity.R;
import kz.almaty.satbayevuniversity.databinding.FragmentAcademicBinding;
import kz.almaty.satbayevuniversity.ui.LoginActivity;

public class AcademicFragment extends Fragment {
    private static final String LOG_TAG = "AcademicFragment";
    private AcademicViewModel mViewModel;
    private AuthViewModel authViewModel;

    private AcademicAdapterResponse academicAdapterResponse;
    private FragmentAcademicBinding academicFragmentBinding;

    public static AcademicFragment newInstance() {
        return new AcademicFragment();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        academicFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_academic, container, false);
        academicFragmentBinding.emptyImage.setVisibility(View.INVISIBLE);
        academicFragmentBinding.emptyTextView.setVisibility(View.INVISIBLE);
        View view = academicFragmentBinding.getRoot();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(AcademicViewModel.class);
        authViewModel = ViewModelProviders.of(this).get(AuthViewModel.class);
        academicFragmentBinding.setAcademicViewModel(mViewModel);

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.journal);

//        mViewModel.registerPlayerId();

        academicFragmentBinding.journalRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        academicFragmentBinding.journalRecyclerView.setHasFixedSize(true);

        academicAdapterResponse = new AcademicAdapterResponse(getActivity());

        academicFragmentBinding.journalRecyclerView.setAdapter(academicAdapterResponse);

        mViewModel.getJournal();

        mViewModel.getAcademicData().observe(this, responseJournals -> {
            academicAdapterResponse.setResponseJournalList(responseJournals);
            });


        mViewModel.getHandleTimeout().observe(this, integer -> {
            if (integer == 1) {
                Toast.makeText(getActivity(), R.string.internetConnection, Toast.LENGTH_SHORT).show();
            }
        });

        mViewModel.getHandleError().observe(this, integer -> {
            if (integer == 401) {
                authViewModel.clearDB();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }
}