package kz.almaty.satbayevuniversity.ui.individualPlan.deferedDisciplnes;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import kz.almaty.satbayevuniversity.R;
import kz.almaty.satbayevuniversity.databinding.ItemIndividualPlanBinding;
import kz.almaty.satbayevuniversity.ui.HomeActivity;
import kz.almaty.satbayevuniversity.ui.individualPlan.chosenDisciplines.ChosenDisciplineAdapter;

public class DeferedDisciplineAdapter extends RecyclerView.Adapter<DeferedDisciplineAdapter.MyViewHolder> implements DeferedDisciplineEventListener{

    List<DeferedDiscipline> deferedDisciplineList = new ArrayList<>();
    private Context context;
    public DeferedDisciplineAdapter(Context context) {
        this.context = context;
    }
    @NonNull
    @Override
    public DeferedDisciplineAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemIndividualPlanBinding itemIndividualPlanBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_individual_plan, parent,false);
        return new DeferedDisciplineAdapter.MyViewHolder(itemIndividualPlanBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull DeferedDisciplineAdapter.MyViewHolder holder, int position) {
        DeferedDiscipline deferedDiscipline = deferedDisciplineList.get(position);
        holder.individualPlanBinding.setClickListener(this);
        holder.individualPlanBinding.setDeferedDiscipline(deferedDiscipline);
    }

    @Override
    public int getItemCount(){
        if (deferedDisciplineList != null)
            return deferedDisciplineList.size();
        return 0;
    }

    public void setIndividualPlanList(List<DeferedDiscipline> deferedDisciplineList){
        this.deferedDisciplineList = deferedDisciplineList;
        notifyDataSetChanged();
    }

    @Override
    public void deferedDisciplineClicked(DeferedDiscipline deferedDiscipline) {
        Log.d("defDecClicked", "deferedDisciplineClicked: ");
        DeferedDisciplineDialogFragment deferedDisciplineDialogFragment = new DeferedDisciplineDialogFragment();
        deferedDisciplineDialogFragment.setDeferedDiscipline(deferedDiscipline);
        HomeActivity homeActivity = (HomeActivity) context;
        deferedDisciplineDialogFragment.show(homeActivity.getSupportFragmentManager(), "deferedDisciplineDialogFragment");
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        private ItemIndividualPlanBinding individualPlanBinding;
        public MyViewHolder(@NonNull ItemIndividualPlanBinding individualPlanBinding){
            super(individualPlanBinding.getRoot());
            this.individualPlanBinding = individualPlanBinding;
        }
    }
}
