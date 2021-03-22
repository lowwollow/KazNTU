package kz.almaty.satbayevuniversity.ui.schedule.exams;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import kz.almaty.satbayevuniversity.R;
import kz.almaty.satbayevuniversity.data.SharedPrefCache;
import kz.almaty.satbayevuniversity.data.entity.Language;
import kz.almaty.satbayevuniversity.databinding.FragmentExamsBinding;


public class ExamsFragment extends Fragment {

    private ExamsViewModel mViewModel;
    private FragmentExamsBinding examsFragmentBinding;
    private ExamAdapter examAdapter;

    public static ExamsFragment newInstance() {
        return new ExamsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
        examsFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_exams, container, false);
        View view = examsFragmentBinding.getRoot();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ExamsViewModel.class);
        examsFragmentBinding.setExams(mViewModel);
        examsFragmentBinding.examRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        examsFragmentBinding.examRecyclerView.setHasFixedSize(true);

        examAdapter = new ExamAdapter();
        examsFragmentBinding.examRecyclerView.setAdapter(examAdapter);

        String lang = getResources().getConfiguration().locale.toString();
        getFromServer(lang);

        mViewModel.getExamLiveData().observe(getViewLifecycleOwner(), examList -> {
            System.out.println(examList.size() + " :size");
            examAdapter.setExamList(examList);
        });
    }

    private void getFromServer(String lang){
        if (lang.equals("kk")){
            mViewModel.getExam("kz");
        }else {
            mViewModel.getExam("ru");
        }
    }
    private void log(String tag, String text){
        Log.d(tag, text);
    }

}
