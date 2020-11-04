package kz.almaty.satbayevuniversity.ui.umkd;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import kz.almaty.satbayevuniversity.R;
import kz.almaty.satbayevuniversity.data.entity.umkd.Umkd;
import kz.almaty.satbayevuniversity.databinding.ItemUmkdBinding;
import kz.almaty.satbayevuniversity.ui.HomeActivity;
import kz.almaty.satbayevuniversity.ui.academicProgress.AcademicFragment;
import kz.almaty.satbayevuniversity.ui.umkd.estimateteacher.EstimateTeacherBottomShitDialog;
import kz.almaty.satbayevuniversity.ui.umkd.filefragment.FileFragment;
import kz.almaty.satbayevuniversity.utils.Storage;

public class UmkdAdapter extends RecyclerView.Adapter<UmkdAdapter.ViewHolder> implements UmkdClickListener{
    private List<Umkd> umkdList;
    private Context context;

    public UmkdAdapter(Context context) {
        this.context = context;
    }
    public UmkdAdapter() {}

    @NonNull
    @Override
    public UmkdAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemUmkdBinding umkdItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_umkd, parent, false);
        return new UmkdAdapter.ViewHolder(umkdItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull UmkdAdapter.ViewHolder holder, int position) {
        Umkd umkd = umkdList.get(position);
        holder.umkdItemBinding.setUmkd(umkd);
        holder.umkdItemBinding.setImkdListener(this);
    }

    @Override
    public int getItemCount() {
        return (umkdList == null) ? 0 : umkdList.size();
    }

    void setResponseUmkdList(List<Umkd> umkdList) {
        this.umkdList = umkdList;
        notifyDataSetChanged();
    }

    @Override
    public void umkdClicked(Umkd umkd) {
        // open dialog
//        EstimateTeacherBottomShitDialog estimateTeacherBottomShitDialog = new EstimateTeacherBottomShitDialog();
//        estimateTeacherBottomShitDialog.show(((AppCompatActivity)context).getSupportFragmentManager(), "");
//
//        Intent i = new Intent(context, EstimateTeacherBottomShitDialog.class);
//        //i.putExtra("asd", "test");
//        ((Activity)(context)).startActivity(i);

        FileFragment fileFragment = new FileFragment();
        Storage.getInstance().setCourseCode(umkd.getCourseCode());
        Storage.getInstance().setInstructorID(String.valueOf(umkd.getInstructorId()));
        HomeActivity activity = (HomeActivity) context;
        activity.getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_up, R.anim.slide_down,  R.anim.slide_up, R.anim.slide_down)
                .replace(R.id.fragment_container, fileFragment, "fileFragment")
                .addToBackStack("")
                .commit();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemUmkdBinding umkdItemBinding;
        public ViewHolder(@NonNull ItemUmkdBinding umkdItemBinding) {
            super(umkdItemBinding.getRoot());
            this.umkdItemBinding = umkdItemBinding;
        }
    }

}
