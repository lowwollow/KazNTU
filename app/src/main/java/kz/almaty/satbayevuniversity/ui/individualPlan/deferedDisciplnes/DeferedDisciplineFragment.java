package kz.almaty.satbayevuniversity.ui.individualPlan.deferedDisciplnes;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.Observable;
import androidx.databinding.ObservableBoolean;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;
import java.util.List;

import kz.almaty.satbayevuniversity.R;
import kz.almaty.satbayevuniversity.data.App;
import kz.almaty.satbayevuniversity.data.SharedPrefCache;
import kz.almaty.satbayevuniversity.data.entity.Language;
import kz.almaty.satbayevuniversity.databinding.FragmentDeferedDisciplinesIndividualPlanBinding;
import kz.almaty.satbayevuniversity.ui.grade.attestation.GradeViewModel;

public class DeferedDisciplineFragment extends Fragment {

    private FragmentDeferedDisciplinesIndividualPlanBinding individualPlanBinding;
    private DeferedDisciplineAdapter individualPlanAdapter;
    public ObservableBoolean getEmptyBoolean = new ObservableBoolean();
    public ObservableBoolean loadRv = new ObservableBoolean();
    private ConnectivityManager connManager = (ConnectivityManager) App.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
    private NetworkInfo activeNetwork = connManager.getActiveNetworkInfo();
    private List<DeferedDiscipline> list = new ArrayList<>();
    private DeferredDisciplineViewModel mViewModel;
    private ImageView img;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        individualPlanBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_defered_disciplines_individual_plan, container, false);
        View view = individualPlanBinding.getRoot();
        img = individualPlanBinding.emptyImage;
        img.setVisibility(View.GONE);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        individualPlanBinding.individualPlanRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        individualPlanBinding.individualPlanRecyclerView.setHasFixedSize(true);
        individualPlanBinding.individualPlanRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));
        individualPlanAdapter = new DeferedDisciplineAdapter(getActivity());
        individualPlanBinding.individualPlanRecyclerView.setAdapter(individualPlanAdapter);
        mViewModel = new ViewModelProvider(this).get(DeferredDisciplineViewModel.class);
        individualPlanBinding.setIndividualPlan(mViewModel);

        String lang1 = SharedPrefCache.getLang(getActivity());
        getFromServer(lang1);

        mViewModel.getDeferredDisciplineLiveData().observe(getViewLifecycleOwner(), deferredDiscipline1s -> {
            individualPlanAdapter.setIndividualPlanList(deferredDiscipline1s);
        });
    }
    private void getFromServer(String lang){
        if (lang.equals("kk")){
            mViewModel.getDeferredDiscipline("kz");
        }else {
            mViewModel.getDeferredDiscipline("ru");
        }
    }
    private void log(String tag, String text){
        Log.d(tag, text);
    }
    public static DeferedDisciplineFragment getInstance() { return new DeferedDisciplineFragment(); }
}
