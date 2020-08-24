package kz.almaty.satbayevuniversity.ui.academicProgress.detailFragment;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import kz.almaty.satbayevuniversity.R;
import kz.almaty.satbayevuniversity.data.entity.academic.DatesItem;
import kz.almaty.satbayevuniversity.databinding.DetailResponseBinding;

import java.util.ArrayList;
import java.util.List;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.ViewHolder> {
    private List<DatesItem> datesItems;
    private DetailResponseBinding detailResponseBinding;
    private Context context;

    public DetailAdapter(List<DatesItem> datesItems, Context context) {
        this.datesItems = datesItems;
        this.context = context;
    }

    @NonNull
    @Override
    public DetailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        detailResponseBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.detail_response, parent, false);
        return new ViewHolder(detailResponseBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailAdapter.ViewHolder holder, int position) {
        DatesItem datesItem = datesItems.get(position);
        holder.detailResponseBinding.setDateItem(datesItem);

        if (!datesItem.isAttended()){
            holder.detailResponseBinding.detailImg.setBackgroundColor(Color.rgb(255,147,147));
        }
}

    @Override
    public int getItemCount() {
        return (datesItems == null) ? 0 : datesItems.size();
    }

    public void setDatesItems(ArrayList<DatesItem> datesItems) {
        this.datesItems = datesItems;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private DetailResponseBinding detailResponseBinding;

        public ViewHolder(@NonNull DetailResponseBinding detailResponseBinding) {
            super(detailResponseBinding.getRoot());
            this.detailResponseBinding = detailResponseBinding;
        }
    }
}
