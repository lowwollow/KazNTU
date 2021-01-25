package kz.almaty.satbayevuniversity.ui.schedule.scheduleFragment.studentsList;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;

import kz.almaty.satbayevuniversity.R;
import kz.almaty.satbayevuniversity.data.SharedPrefCache;
import kz.almaty.satbayevuniversity.data.entity.Language;
import kz.almaty.satbayevuniversity.data.entity.schedule.Schedule;
import kz.almaty.satbayevuniversity.data.entity.schedule.Student;
import kz.almaty.satbayevuniversity.databinding.FragmentStudentsListBinding;
import kz.almaty.satbayevuniversity.utils.LocaleHelper;

public class StudentsListFragment extends DialogFragment {
    FragmentStudentsListBinding fragmentStudentsListBinding;
    RecyclerView student_list_recycler_view;
    StudentListAdapter studentListAdapter;
    StudentsListViewModel viewModel;
    Schedule schedule;

    public StudentsListFragment(Schedule schedule){
        this.schedule = schedule;
    }

    public StudentsListFragment () {}

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        fragmentStudentsListBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()),R.layout.fragment_students_list,null,false);
        fragmentStudentsListBinding.setSchedule(schedule);
        fragmentStudentsListBinding.closeImageView.setOnClickListener(v -> {
            if(getDialog() != null){
                getDialog().dismiss();
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = fragmentStudentsListBinding.getRoot();
        studentListAdapter = new StudentListAdapter(getActivity());
        fragmentStudentsListBinding.studentListRecyclerView.setAdapter(studentListAdapter);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        fragmentStudentsListBinding.studentListRecyclerView.setLayoutManager(llm);
        builder.setView(view);

        viewModel = ViewModelProviders.of(this).get(StudentsListViewModel.class);


        SharedPrefCache cache = new SharedPrefCache();
        String lang = cache.getStr("language", getContext());
        Gson gson = new Gson();
        if (lang == "DNF"){
            viewModel.getStudentList(schedule.getClassId(),"ru");
        }
        else {
            try {
                Language language = gson.fromJson(lang, Language.class);
                if (language.getLanguage().equals("Казахский"))
                    viewModel.getStudentList(schedule.getClassId(),"kz");
                else {
                    viewModel.getStudentList(schedule.getClassId(),"ru");
                }
            } catch (IllegalStateException | JsonSyntaxException ignored) {}
        }

        viewModel.getLiveData().observe(this, students -> {
            studentListAdapter.setStudentList(students);
        });
        fragmentStudentsListBinding.setViewModel(viewModel);
        return builder.create();
    }

    @Override
    public void onResume() {
        super.onResume();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        getDialog().getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT,(height/10)*8);

    }
}
