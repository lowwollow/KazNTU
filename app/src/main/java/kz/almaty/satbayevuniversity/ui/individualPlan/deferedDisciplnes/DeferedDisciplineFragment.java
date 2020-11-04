package kz.almaty.satbayevuniversity.ui.individualPlan.deferedDisciplnes;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
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
import kz.almaty.satbayevuniversity.data.network.KaznituRetrofit;
import kz.almaty.satbayevuniversity.databinding.FragmentDeferedDisciplinesIndividualPlanBinding;
import kz.almaty.satbayevuniversity.ui.individualPlan.chosenDisciplines.ChosenDiscipline;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

public class DeferedDisciplineFragment extends Fragment {

    private FragmentDeferedDisciplinesIndividualPlanBinding individualPlanBinding;
    private DeferedDisciplineAdapter individualPlanAdapter;
    public ObservableBoolean getEmptyBoolean = new ObservableBoolean();
    public ObservableBoolean loadRv = new ObservableBoolean();
    private ConnectivityManager connManager = (ConnectivityManager) App.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
    private NetworkInfo activeNetwork = connManager.getActiveNetworkInfo();
    private List<DeferedDiscipline> list = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        individualPlanBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_defered_disciplines_individual_plan, container, false);
        View view = individualPlanBinding.getRoot();
        individualPlanBinding.emptyImage.setVisibility(view.GONE);
        individualPlanBinding.emptyTextView.setVisibility(view.GONE);
        loadRv.set(true);
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
        if (connManager != null) {
            if (connManager.getActiveNetworkInfo() != null && activeNetwork.isConnected()) {
                KaznituRetrofit.getApi().updateDeferedDiscipline().enqueue(new Callback<DeferedDisciplineGroup>() {
                    @Override
                    public void onResponse(Call<DeferedDisciplineGroup> call, Response<DeferedDisciplineGroup> response) {
                        if (response.isSuccessful()) {
                            individualPlanAdapter.setIndividualPlanList(response.body().getIndividualPlanList());
                        }
                    }

                    @Override
                    public void onFailure(Call<DeferedDisciplineGroup> call, Throwable t) {
                        // need process the fail
                    }
                });

            } else {
                getEmptyBoolean.set(true);
            }
        }
    }

    public static DeferedDisciplineFragment newInstance() { return new DeferedDisciplineFragment(); }
}
