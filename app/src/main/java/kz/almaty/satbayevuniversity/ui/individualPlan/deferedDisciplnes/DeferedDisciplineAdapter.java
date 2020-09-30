package kz.almaty.satbayevuniversity.ui.individualPlan.deferedDisciplnes;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import kz.almaty.satbayevuniversity.R;
import kz.almaty.satbayevuniversity.databinding.ItemIndividualPlanBinding;

public class DeferedDisciplineAdapter extends RecyclerView.Adapter<DeferedDisciplineAdapter.MyViewHolder> {

    List<DeferedDiscipline> deferedDisciplineList = new ArrayList<>();

    @NonNull
    @Override
    public DeferedDisciplineAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemIndividualPlanBinding itemIndividualPlanBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_individual_plan,parent,false);
        return new DeferedDisciplineAdapter.MyViewHolder(itemIndividualPlanBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull DeferedDisciplineAdapter.MyViewHolder holder, int position) {
        DeferedDiscipline deferedDiscipline = deferedDisciplineList.get(position);
        holder.individualPlanBinding.setIndividualPlanItem(deferedDiscipline);
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



    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private ItemIndividualPlanBinding individualPlanBinding;
        public MyViewHolder(@NonNull ItemIndividualPlanBinding individualPlanBinding) {
            super(individualPlanBinding.getRoot());
            this.individualPlanBinding = individualPlanBinding;
        }
    }
}
