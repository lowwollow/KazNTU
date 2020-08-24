package kz.almaty.satbayevuniversity.ui.umkd.filefragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.List;

import kz.almaty.satbayevuniversity.R;
import kz.almaty.satbayevuniversity.data.entity.umkd.File;
import kz.almaty.satbayevuniversity.databinding.ItemFileBinding;
import kz.almaty.satbayevuniversity.ui.HomeActivity;
import kz.almaty.satbayevuniversity.ui.umkd.filefragment.fileDataFragment.FileDataFragment;

public class FileAdapter extends RecyclerView.Adapter<FileAdapter.ViewHolder> implements FileClickListener{
    private List<File> fileList;
    private Context context;

    public FileAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public FileAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFileBinding fileItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_file, parent, false);
        return new FileAdapter.ViewHolder(fileItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull FileAdapter.ViewHolder holder, int position) {
        File currentFile = fileList.get(position);
        holder.fileItemBinding.setFile(currentFile);
        holder.fileItemBinding.setFileItemClickListener(this);
    }

    @Override
    public int getItemCount() {
        return (fileList == null) ? 0 : fileList.size();}

    void setFilelList(List<File> filelList) {
        this.fileList = filelList;
        notifyDataSetChanged();
    }

    @Override
    public void FileClick(File file) {
        FileDataFragment fileDataFragment= new FileDataFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("FileDataFragment", (Serializable) file.getChildren());
        fileDataFragment.setArguments(bundle);
        HomeActivity activity = (HomeActivity) context;
        activity.getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left,
                        R.anim.enter_from_left, R.anim.exit_to_right)
                .replace(R.id.fragment_container, fileDataFragment, "fileDataFragment")
                .addToBackStack("")
                .commit();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ItemFileBinding fileItemBinding;
        ViewHolder(@NonNull ItemFileBinding fileItemBinding) {
            super(fileItemBinding.getRoot());
            this.fileItemBinding = fileItemBinding;
        }
    }
}
