package kz.almaty.satbayevuniversity.ui.individualPlan.chosenDisciplines;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import kz.almaty.satbayevuniversity.R;
import kz.almaty.satbayevuniversity.databinding.ChosenDisciplineHeaderBinding;
import kz.almaty.satbayevuniversity.databinding.ChosenDisciplineItemBinding;
import kz.almaty.satbayevuniversity.databinding.FragmentChosenDisciplinesIndividualPlanBinding;
import kz.almaty.satbayevuniversity.databinding.ItemTranscriptBinding;
import kz.almaty.satbayevuniversity.ui.HomeActivity;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

public class ChosenDisciplineAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ChosenDisciplineEventListener{

    private List<Object> chosenDisciplines = new ArrayList<>();
    private Context context;

    public ChosenDisciplineAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        if (chosenDisciplines.get(position) instanceof Semesters)
            return 0;
        return 1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0:
                ChosenDisciplineHeaderBinding chosenDisciplineItemBinding =
                    DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.chosen_discipline_header, parent, false);
                return new ViewHolderHeader(chosenDisciplineItemBinding);
            case 1:
                ChosenDisciplineItemBinding chosenDisciplineItemBinding1 =
                    DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.chosen_discipline_item, parent, false);
                return new ViewHolderBody(chosenDisciplineItemBinding1);
        }
        return null;
    }

    public void setChosenDisciplines(List<Object> list){
        this.chosenDisciplines = list;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == 0){
            ViewHolderHeader viewHolderHeader = (ViewHolderHeader) holder;
            Object chosenDiscipline = chosenDisciplines.get(position);
            viewHolderHeader.chosenDisciplineItemBinding.setDescipline((Semesters) chosenDiscipline);
        }else{
            ViewHolderBody viewHolderBody = (ViewHolderBody) holder;
            Object chosenDiscipline = chosenDisciplines.get(position);
            viewHolderBody.chosenDisciplineItemBinding.getClickListener();
            viewHolderBody.chosenDisciplineItemBinding.setClickListener(this);
            viewHolderBody.chosenDisciplineItemBinding.setChosenDisciplineItem((ChosenDiscipline)chosenDiscipline);
        }
    }

    @Override
    public int getItemCount() {
        if (chosenDisciplines != null)
            return chosenDisciplines.size();
        return 0;
    }

    @Override
    public void choseDisciplineClicked(ChosenDiscipline chosenDiscipline) {
        ChosenDisciplineDialogFragment disciplineDialogFragment = new ChosenDisciplineDialogFragment();
        disciplineDialogFragment.setChosenDisciplineDialog(chosenDiscipline);
        HomeActivity homeActivity = (HomeActivity) context;
        disciplineDialogFragment.show(homeActivity.getSupportFragmentManager(), "chosenDisciplineDialogFragment");
    }

    class ViewHolderHeader extends RecyclerView.ViewHolder {
        ChosenDisciplineHeaderBinding chosenDisciplineItemBinding;
        ViewHolderHeader(@NonNull ChosenDisciplineHeaderBinding chosenDisciplineItemBinding) {
            super(chosenDisciplineItemBinding.getRoot());
            this.chosenDisciplineItemBinding = chosenDisciplineItemBinding;
        }
    }

    class ViewHolderBody extends RecyclerView.ViewHolder{
        ChosenDisciplineItemBinding chosenDisciplineItemBinding;
        public ViewHolderBody(@NonNull ChosenDisciplineItemBinding chosenDisciplineItemBinding) {
            super(chosenDisciplineItemBinding.getRoot());
            this.chosenDisciplineItemBinding = chosenDisciplineItemBinding;
        }
    }
}
