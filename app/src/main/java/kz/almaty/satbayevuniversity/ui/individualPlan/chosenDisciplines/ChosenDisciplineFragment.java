package kz.almaty.satbayevuniversity.ui.individualPlan.chosenDisciplines;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableBoolean;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistryOwner;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import kz.almaty.satbayevuniversity.R;
import kz.almaty.satbayevuniversity.data.App;
import kz.almaty.satbayevuniversity.data.SharedPrefCache;
import kz.almaty.satbayevuniversity.data.entity.Language;
import kz.almaty.satbayevuniversity.data.entity.grade.transcript.SemestersItem;
import kz.almaty.satbayevuniversity.data.entity.individualPlan.choosenDiscipline.ChosenDiscipline1;
import kz.almaty.satbayevuniversity.data.entity.individualPlan.choosenDiscipline.Semesters1;
import kz.almaty.satbayevuniversity.data.network.KaznituRetrofit;
import kz.almaty.satbayevuniversity.databinding.ChosenDisciplineItemBinding;
import kz.almaty.satbayevuniversity.databinding.FragmentChosenDisciplinesIndividualPlanBinding;
import kz.almaty.satbayevuniversity.ui.individualPlan.deferedDisciplnes.DeferedDisciplineGroup;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

public class ChosenDisciplineFragment extends Fragment{

    private FragmentChosenDisciplinesIndividualPlanBinding individualPlanBinding;
    private ChosenDisciplineAdapter chosenDisciplineAdapter;
    public ObservableBoolean getEmptyBoolean = new ObservableBoolean();
    public ObservableBoolean loadRv = new ObservableBoolean();
    private ConnectivityManager connManager = (ConnectivityManager) App.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
    private NetworkInfo activeNetwork = connManager.getActiveNetworkInfo();
    private boolean firstTime = true;
    private ChosenDisciplineViewModel mViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        individualPlanBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_chosen_disciplines_individual_plan,container,false);
        View view = individualPlanBinding.getRoot();
        individualPlanBinding.emptyImage.setVisibility(view.GONE);
        individualPlanBinding.emptyTextView.setVisibility(view.GONE);

        individualPlanBinding.chosenDisciplineRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        individualPlanBinding.chosenDisciplineRecyclerView.setHasFixedSize(true);
        individualPlanBinding.chosenDisciplineRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        chosenDisciplineAdapter = new ChosenDisciplineAdapter(getActivity());

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ChosenDisciplineViewModel.class);
        individualPlanBinding.setChosenDiscipline(mViewModel);
        individualPlanBinding.chosenDisciplineRecyclerView.setAdapter(chosenDisciplineAdapter);


        String lang1 = SharedPrefCache.getLang(getActivity());
        getFromServer(lang1);

        mViewModel.getChosenDisciplinesData().observe(getViewLifecycleOwner(), chosenDiscipline1s -> {
            ArrayList<Object> list = new ArrayList<>(chosenDiscipline1s.size() * 8);
            for (Semesters1 x : chosenDiscipline1s){
                list.add(x);
                list.addAll(x.getChosenDisciplineList());
            }
            chosenDisciplineAdapter.setChosenDisciplines(list);
        });

        mViewModel.getHandleTimeout().observe(getViewLifecycleOwner(), aBoolean -> {
            if (aBoolean) {
                Toast.makeText(getActivity(), R.string.internetConnection, Toast.LENGTH_SHORT).show();
            }
        });
}
    private void getFromServer(String lang){
        if (lang.equals("kk")){
            mViewModel.getChosenDiscipline("kz");
        }else {
            mViewModel.getChosenDiscipline("ru");
        }
    }
    private void log(String tag, String text){
        Log.d(tag, text);
    }
    public static ChosenDisciplineFragment getInstance() {return new ChosenDisciplineFragment();}
}
