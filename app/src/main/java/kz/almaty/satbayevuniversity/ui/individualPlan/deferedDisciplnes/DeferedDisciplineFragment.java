package kz.almaty.satbayevuniversity.ui.individualPlan.deferedDisciplnes;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableBoolean;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import kz.almaty.satbayevuniversity.R;
import kz.almaty.satbayevuniversity.data.App;
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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        individualPlanBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_defered_disciplines_individual_plan, container, false);
        View view = individualPlanBinding.getRoot();
        individualPlanBinding.notifyChange();
        individualPlanBinding.emptyImage.setVisibility(view.GONE);
        individualPlanBinding.emptyTextView.setVisibility(view.GONE);
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
        mViewModel = ViewModelProviders.of(this).get(DeferredDisciplineViewModel.class);

        mViewModel.getDeferredDiscipline();

        mViewModel.getDeferredDisciplineLiveData().observe(this, deferredDiscipline1s -> {
            individualPlanAdapter.setIndividualPlanList(deferredDiscipline1s);
        });
    }
    public static DeferedDisciplineFragment getInstance() { return new DeferedDisciplineFragment(); }
}
