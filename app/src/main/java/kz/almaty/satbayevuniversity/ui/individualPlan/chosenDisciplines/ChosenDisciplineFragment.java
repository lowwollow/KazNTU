package kz.almaty.satbayevuniversity.ui.individualPlan.chosenDisciplines;

import android.content.Context;
import android.content.SharedPreferences;
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
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import kz.almaty.satbayevuniversity.R;
import kz.almaty.satbayevuniversity.data.App;
import kz.almaty.satbayevuniversity.data.entity.grade.transcript.SemestersItem;
import kz.almaty.satbayevuniversity.data.network.KaznituRetrofit;
import kz.almaty.satbayevuniversity.databinding.ChosenDisciplineItemBinding;
import kz.almaty.satbayevuniversity.databinding.FragmentChosenDisciplinesIndividualPlanBinding;
import kz.almaty.satbayevuniversity.ui.individualPlan.deferedDisciplnes.DeferedDisciplineGroup;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

public class ChosenDisciplineFragment extends Fragment {

    private FragmentChosenDisciplinesIndividualPlanBinding individualPlanBinding;
    private ChosenDisciplineAdapter chosenDisciplineAdapter;
    public ObservableBoolean getEmptyBoolean = new ObservableBoolean();
    public ObservableBoolean loadRv = new ObservableBoolean();
    private Context context;
    private SharedPreferences sPref;
    private List<ChosenDiscipline> list = new ArrayList<>();
    private final String SAVED_OBJECT = "saved_object";
    private ConnectivityManager connManager = (ConnectivityManager) App.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
    private NetworkInfo activeNetwork = connManager.getActiveNetworkInfo();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        individualPlanBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_chosen_disciplines_individual_plan,container,false);
        View view = individualPlanBinding.getRoot();
        individualPlanBinding.emptyImage.setVisibility(view.GONE);
        individualPlanBinding.emptyTextView.setVisibility(view.GONE);
        loadRv.set(true);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        individualPlanBinding.chosenDisciplineRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        individualPlanBinding.chosenDisciplineRecyclerView.setHasFixedSize(true);
        individualPlanBinding.chosenDisciplineRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        chosenDisciplineAdapter = new ChosenDisciplineAdapter(getActivity());
        individualPlanBinding.chosenDisciplineRecyclerView.setAdapter(chosenDisciplineAdapter);
        if (activeNetwork != null) {
            if (connManager.getActiveNetworkInfo() != null && activeNetwork.isConnected()) {
                KaznituRetrofit.getApi().updateChosenDiscipline().enqueue(new Callback<ChosenDisciplineGroup>() {
                    @Override
                    public void onResponse(Call<ChosenDisciplineGroup> call, Response<ChosenDisciplineGroup> response) {
                        if (response.isSuccessful()) {
                            List<Object> list = new ArrayList<>(response.body().chosenDisciplineList.size() * 2);
                            for (int i = 0; i < response.body().getChosenDisciplineList().size(); i++) {
                                list.add(response.body().getChosenDisciplineList().get(i));
                                list.addAll(response.body().getChosenDisciplineList().get(i).getChosenDisciplineList());
                            }
                            chosenDisciplineAdapter.setChosenDisciplines(list);
                        }
                    }

                    @Override
                    public void onFailure(Call<ChosenDisciplineGroup> call, Throwable t) {
                    }
                });
            } else {
                getEmptyBoolean.set(true);
                Toast.makeText(getActivity(), "Нет доступа к сети", Toast.LENGTH_LONG).show();
            }
        }

    }

    public void SaveDiscipline(ChosenDiscipline chosenDiscipline){
        sPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        Gson gson = new Gson();
        String gson_to_string = gson.toJson(chosenDiscipline);
        ed.putString(SAVED_OBJECT,gson_to_string);
        ed.apply();
    }

    public ChosenDiscipline GetDiscipline(){
        Gson gson = new Gson();
        String gson_string = sPref.getString(SAVED_OBJECT, "");
        ChosenDiscipline discipline = gson.fromJson(gson_string,ChosenDiscipline.class);
        return discipline;
    }

    public ChosenDisciplineFragment getChosenDisciplineFragment() {return new ChosenDisciplineFragment();}
}
