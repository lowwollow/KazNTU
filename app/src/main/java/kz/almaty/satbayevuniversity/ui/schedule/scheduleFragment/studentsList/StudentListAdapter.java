package kz.almaty.satbayevuniversity.ui.schedule.scheduleFragment.studentsList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import kz.almaty.satbayevuniversity.R;
import kz.almaty.satbayevuniversity.data.entity.schedule.Student;
import kz.almaty.satbayevuniversity.databinding.ItemStudentBinding;

public class StudentListAdapter extends RecyclerView.Adapter<StudentListAdapter.ViewHolder> {
    Context context;
    List<Student> studentList = new ArrayList<>();
    public StudentListAdapter(Context context){
        this.context = context;
    }
    @NonNull
    @Override
    public StudentListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemStudentBinding studentItemBinding = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.item_student,parent,false);
        return new ViewHolder(studentItemBinding);

    }

    @Override
    public void onBindViewHolder(@NonNull StudentListAdapter.ViewHolder holder, int position) {
        Student student = studentList.get(position);
        holder.studentItemBinding.setStudent(student);
    }

    @Override
    public int getItemCount() {
        return (studentList==null)? 0 : studentList.size();
    }

    public void setStudentList(List<Student> studentList){
        this.studentList.clear();
        this.studentList.addAll(studentList);
        notifyDataSetChanged();
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        ItemStudentBinding studentItemBinding;
       public ViewHolder(ItemStudentBinding studentItemBinding){
           super(studentItemBinding.getRoot());
           this.studentItemBinding = studentItemBinding;
       }
   }
}
