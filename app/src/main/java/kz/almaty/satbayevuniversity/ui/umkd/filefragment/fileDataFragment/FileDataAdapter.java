package kz.almaty.satbayevuniversity.ui.umkd.filefragment.fileDataFragment;

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
import kz.almaty.satbayevuniversity.data.entity.umkd.Course;
import kz.almaty.satbayevuniversity.databinding.FragmentFileDataItemBinding;
import kz.almaty.satbayevuniversity.ui.HomeActivity;
import kz.almaty.satbayevuniversity.ui.umkd.filefragment.fileDataFragment.webViewFragment.WebViewFragment;
import kz.almaty.satbayevuniversity.utils.Storage;


public class FileDataAdapter  extends RecyclerView.Adapter<FileDataAdapter.ViewHolder> implements FileDataClickListener{
    private List<Course> courseList;
    private Context context;

    FileDataAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public FileDataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FragmentFileDataItemBinding fileDataFragmentItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.fragment_file_data_item, parent, false);
        return new FileDataAdapter.ViewHolder(fileDataFragmentItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull FileDataAdapter.ViewHolder holder, int position) {
        Course currentCourse = courseList.get(position);
        holder.fileDataFragmentItemBinding.setCourse(currentCourse);
        holder.fileDataFragmentItemBinding.setFiledataclickListener(this);
        int lastthreeLetters = currentCourse.getFileName().length()-3;
        if(lastthreeLetters>=0){
            String newString = currentCourse.getFileName().substring(lastthreeLetters);
            switch (newString){
                case "ocx":
                    holder.fileDataFragmentItemBinding.fileImage.setImageResource(R.drawable.doc_icon);
                    break;
                case "pdf":
                    holder.fileDataFragmentItemBinding.fileImage.setImageResource(R.drawable.pdf_icon);
                    break;
                case "doc":
                    holder.fileDataFragmentItemBinding.fileImage.setImageResource(R.drawable.doc_icon);
                    break;
                case "xls":
                    holder.fileDataFragmentItemBinding.fileImage.setImageResource(R.drawable.xls_icon);
                    break;
                case "PDF":
                    holder.fileDataFragmentItemBinding.fileImage.setImageResource(R.drawable.pdf_icon);
                    break;
                case "zip":
                    holder.fileDataFragmentItemBinding.fileImage.setImageResource(R.drawable.zip_icon);
                    break;
            }
        }

    }

    @Override
    public int getItemCount() {
        return (courseList == null) ? 0 : courseList.size();}

    void setFileDataList(List<Course> courseList) {
        this.courseList = courseList;
        notifyDataSetChanged();
    }

    @Override
    public void FileDataClick(Course course) {
        if (course.getChildren().size() == 0) {
            WebViewFragment webViewFragment = new WebViewFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("WebViewFragment", course);
            Storage.getInstance().setCourseId(course.getId());
            Storage.getInstance().setFileName(course.getFileName());
            webViewFragment.setArguments(bundle);
            HomeActivity activity = (HomeActivity) context;
            activity.getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.slide_up, R.anim.slide_down, R.anim.slide_up, R.anim.slide_down)
                    .replace(R.id.fragment_container, webViewFragment)
                    .addToBackStack("")
                    .commit();
        }else{
            FileDataFragment fileDataFragment = new FileDataFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("FileDataFragment", (Serializable) course.getChildren());
            Storage.getInstance().setCourseId(course.getId());
            Storage.getInstance().setFileName(course.getFileName());
            fileDataFragment.setArguments(bundle);
            HomeActivity activity = (HomeActivity) context;
            activity.getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fileDataFragment, "fileDataFragment")
                    .addToBackStack("")
                    .commit();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        FragmentFileDataItemBinding fileDataFragmentItemBinding;
        public ViewHolder(@NonNull FragmentFileDataItemBinding fileDataFragmentItemBinding) {
            super(fileDataFragmentItemBinding.getRoot());
            this.fileDataFragmentItemBinding = fileDataFragmentItemBinding;
        }
    }
}
