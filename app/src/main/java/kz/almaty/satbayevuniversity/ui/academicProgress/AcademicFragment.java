package kz.almaty.satbayevuniversity.ui.academicProgress;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.Observable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.Locale;
import java.util.Objects;

import kz.almaty.satbayevuniversity.AuthViewModel;
import kz.almaty.satbayevuniversity.R;
import kz.almaty.satbayevuniversity.data.SharedPrefCache;
import kz.almaty.satbayevuniversity.data.entity.Language;
import kz.almaty.satbayevuniversity.databinding.FragmentAcademicBinding;
import kz.almaty.satbayevuniversity.ui.LoginActivity;

import static android.content.Context.MODE_PRIVATE;

public class AcademicFragment extends Fragment {
    private static final String LOG_TAG = "AcademicFragment";
    private AcademicViewModel mViewModel;
    private LottieAnimationView lottieAnimationView;
    private AuthViewModel authViewModel;

    private AcademicAdapterResponse academicAdapterResponse;

    private FragmentAcademicBinding academicFragmentBinding;


    public static AcademicFragment newInstance() {
        return new AcademicFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        academicFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_academic, container, false);
        academicFragmentBinding.emptyImage.setVisibility(View.GONE);
        academicFragmentBinding.emptyTextView.setVisibility(View.GONE);
        mViewModel = new ViewModelProvider(this).get(AcademicViewModel.class);
        authViewModel = new AuthViewModel();
        lottieAnimationView = academicFragmentBinding.lottieLoader;
        lottieAnimationView.setVisibility(View.GONE);
        return academicFragmentBinding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        academicFragmentBinding.setAcademicViewModel(mViewModel);

        if (((AppCompatActivity) requireActivity()).getSupportActionBar() != null) {
            Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).setTitle(R.string.journal);
        }

        mViewModel.registerPlayerId();

        academicFragmentBinding.journalRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        academicFragmentBinding.journalRecyclerView.setHasFixedSize(true);

        academicAdapterResponse = new AcademicAdapterResponse(getActivity());
        academicFragmentBinding.journalRecyclerView.setAdapter(academicAdapterResponse);


        String lang1 = SharedPrefCache.getLang(getActivity());
        getFromServer(lang1);


        mViewModel.getAcademicData().observe(getViewLifecycleOwner(), responseJournals -> {
            academicAdapterResponse.setResponseJournalList(responseJournals);
        });

        mViewModel.getHandleTimeout().observe(getViewLifecycleOwner(), integer -> {
            if (integer == 1) {
                Toast.makeText(getActivity(), R.string.internetConnection, Toast.LENGTH_SHORT).show();
            }
        });


        mViewModel.getHandleError().observe(getViewLifecycleOwner(), integer -> {
            if (integer == 401) {
                if (authViewModel != null) {
                    authViewModel.clearDB();
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }
            }
        });

    }

    private void getFromServer(String lang){
        if (lang.equals("kk")){
            mViewModel.getJournal("kz");
        }else{
            mViewModel.getJournal("ru");
        }
    }

    private void log(String tag, String text){
        Log.d(tag, text);
    }
}
