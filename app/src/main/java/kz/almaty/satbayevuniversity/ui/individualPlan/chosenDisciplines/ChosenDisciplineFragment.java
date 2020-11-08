package kz.almaty.satbayevuniversity.ui.individualPlan.chosenDisciplines;

import android.annotation.SuppressLint;
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
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistryOwner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
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

public class ChosenDisciplineFragment extends Fragment{

    private FragmentChosenDisciplinesIndividualPlanBinding individualPlanBinding;
    private ChosenDisciplineAdapter chosenDisciplineAdapter;
    public ObservableBoolean getEmptyBoolean = new ObservableBoolean();
    public ObservableBoolean loadRv = new ObservableBoolean();
    private Context context;
    private SharedPreferences sPref;
    private List<ChosenDiscipline> list = new ArrayList<>();
    private final String SAVED_OBJECTS = "saved_objects";
    private ConnectivityManager connManager = (ConnectivityManager) App.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
    private NetworkInfo activeNetwork = connManager.getActiveNetworkInfo();
    private boolean firstTime = true;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        individualPlanBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_chosen_disciplines_individual_plan,container,false);
        View view = individualPlanBinding.getRoot();
        individualPlanBinding.emptyImage.setVisibility(view.GONE);
        individualPlanBinding.emptyTextView.setVisibility(view.GONE);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("ClickableViewAccessibility")
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
                            SaveDiscipline(list);
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


    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState: ");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach: ");
    }


    public void SaveDiscipline(List<Object> chosenDisciplines){
        sPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        Gson gson = new Gson();
        String gson_to_string = gson.toJson(chosenDisciplines);
        ed.putString(SAVED_OBJECTS,gson_to_string);
        ed.apply();
    }

    public List<Object> GetDisciplines(){
        Gson gson = new Gson();
        String gson_string = sPref.getString(SAVED_OBJECTS, "");
        Type type = new TypeToken<List<Object>>(){}.getType();
        List<Object> disciplines = gson.fromJson(gson_string,type);
        return disciplines;
    }

    public static ChosenDisciplineFragment getInstance() {return new ChosenDisciplineFragment();}

}