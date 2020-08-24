package kz.almaty.satbayevuniversity.ui.academicProgress;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import kz.almaty.satbayevuniversity.R;
import kz.almaty.satbayevuniversity.utils.Storage;
import kz.almaty.satbayevuniversity.data.entity.academic.ResponseJournal;
import kz.almaty.satbayevuniversity.databinding.JournalItemBinding;
import kz.almaty.satbayevuniversity.ui.HomeActivity;
import kz.almaty.satbayevuniversity.ui.academicProgress.detailFragment.DetailFragment;

import java.io.Serializable;
import java.util.List;

    public class AcademicAdapterResponse extends RecyclerView.Adapter<AcademicAdapterResponse.ViewHolder>
        implements ResponseEventListener {

    private List<ResponseJournal> responseJournalList;
    private Context context;

    public AcademicAdapterResponse(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public AcademicAdapterResponse.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        JournalItemBinding journalItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.journal_item, parent, false);
        return new ViewHolder(journalItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull AcademicAdapterResponse.ViewHolder holder, int position) {
        ResponseJournal currentResponseJournal = responseJournalList.get(position);
        holder.journalItemBinding.setJournal(currentResponseJournal);
        holder.journalItemBinding.setItemClickListener(this);
    }

    @Override
    public int getItemCount() {
        return (responseJournalList == null) ? 0 : responseJournalList.size();
    }

    void setResponseJournalList(List<ResponseJournal> responseJournalList) {
        this.responseJournalList = responseJournalList;
        notifyDataSetChanged();
    }

    @Override
    public void cardClicked(ResponseJournal f) {
        DetailFragment detailFragment = new DetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("detail", (Serializable) f.getDates());
        detailFragment.setArguments(bundle);
        Storage.getInstance().setResponseJournal(f);
        HomeActivity activity = (HomeActivity) context;
        detailFragment.show(activity.getSupportFragmentManager(), "Dialog");
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private JournalItemBinding journalItemBinding;
        ViewHolder(@NonNull JournalItemBinding journalItemBinding) {
            super(journalItemBinding.getRoot());
            this.journalItemBinding = journalItemBinding;
            }
    }
}

