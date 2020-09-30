package kz.almaty.satbayevuniversity.ui.individualPlan.deferedDisciplnes;

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

import kz.almaty.satbayevuniversity.R;
import kz.almaty.satbayevuniversity.data.network.KaznituRetrofit;
import kz.almaty.satbayevuniversity.databinding.FragmentDeferedDisciplinesIndividualPlanBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

public class DeferedDisciplineFragment extends Fragment {

    private FragmentDeferedDisciplinesIndividualPlanBinding individualPlanBinding;
    private DeferedDisciplineAdapter individualPlanAdapter;
    public ObservableBoolean getEmptyBoolean = new ObservableBoolean();
    public ObservableBoolean loadRv = new ObservableBoolean();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        individualPlanBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_defered_disciplines_individual_plan, container, false);
        View view = individualPlanBinding.getRoot();
        individualPlanBinding.emptyImage.setVisibility(view.INVISIBLE);
        individualPlanBinding.emptyTextView.setVisibility(view.INVISIBLE);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        individualPlanAdapter = new DeferedDisciplineAdapter();

        individualPlanBinding.individualPlanRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        individualPlanBinding.individualPlanRecyclerView.setHasFixedSize(true);
        individualPlanBinding.individualPlanRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));
        individualPlanBinding.individualPlanRecyclerView.setAdapter(individualPlanAdapter);


        KaznituRetrofit.getApi().updateDeferedDiscipline().enqueue(new Callback<DeferedDisciplineGroup>(){
            @Override
            public void onResponse(Call<DeferedDisciplineGroup> call, Response<DeferedDisciplineGroup> response) {
                if (response.isSuccessful()) {
                    individualPlanAdapter.setIndividualPlanList(response.body().getIndividualPlanList());
                }
            }

            @Override
            public void onFailure(Call<DeferedDisciplineGroup> call, Throwable t) {
                //      loadRv.set(false);
                Log.d(TAG, "onResponse: FAIL");
            }
        });
    }

    public static DeferedDisciplineFragment newInstance() { return new DeferedDisciplineFragment(); }
}
