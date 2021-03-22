package kz.almaty.satbayevuniversity.ui.schedule.scheduleFragment;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import kz.almaty.satbayevuniversity.R;
import kz.almaty.satbayevuniversity.data.App;
import kz.almaty.satbayevuniversity.data.entity.schedule.Schedule;
import kz.almaty.satbayevuniversity.databinding.ItemScheduleBinding;
import kz.almaty.satbayevuniversity.ui.schedule.scheduleFragment.studentsList.StudentsListFragment;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> implements ScheduleListener {
    private List<Schedule> scheduleList = new ArrayList<>();
    private ItemScheduleBinding scheduleItemBinding;
    private int[] colors = App.getContext().getResources().getIntArray(R.array.colors);
    private Context context;
    private ConnectivityManager connManager = (ConnectivityManager)App.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
    private NetworkInfo activeNetwork = connManager.getActiveNetworkInfo();

    public ScheduleAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ScheduleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        scheduleItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_schedule, parent, false);
        ViewHolder viewHolder = new ViewHolder(scheduleItemBinding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleAdapter.ViewHolder holder, int position) {
        Schedule currentSchedule = scheduleList.get(position);
        String classTypeString="";
        switch (currentSchedule.getClassType()){
            case 1:
                classTypeString = "(Лаб)";
                break;
            case 2:
                classTypeString = "(Практика)";
                break;
            case 3:
                classTypeString = "(Лекция)";
                break;
        }

        holder.scheduleItemBinding.setClassType(classTypeString);
        holder.scheduleItemBinding.setSchedule(currentSchedule);
        holder.scheduleItemBinding.setScheduleListener(this);
        if(currentSchedule.getDayOfWeekId() != 0){
            holder.scheduleItemBinding.constraintOfScheduleItem.setBackgroundColor(colors[position]);
            holder.scheduleItemBinding.listOfStudentsLayout.setVisibility(View.VISIBLE);
        }
        else{
            holder.scheduleItemBinding.constraintOfScheduleItem.setBackgroundColor(Color.TRANSPARENT);
            holder.scheduleItemBinding.listOfStudentsLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return (scheduleList == null) ? 0 : scheduleList.size();
    }

    public void setScheduleList(List<Schedule> scheduleList){
        this.scheduleList.clear();
        this.scheduleList.addAll(scheduleList);
        notifyItemRangeChanged(0, scheduleList.size());
    }

    @Override
    public void scheduleClicked(Schedule schedule) {

        if(connManager.getActiveNetworkInfo() != null && connManager.getActiveNetworkInfo().isAvailable() && activeNetwork.isConnected() ) {
                StudentsListFragment studentsListFragment = new StudentsListFragment(schedule);
                studentsListFragment.show(((AppCompatActivity) context).getSupportFragmentManager(), "studentListFragment");
        }else{
                Toast.makeText(App.getContext(), R.string.internetConnection, Toast.LENGTH_SHORT).show();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ItemScheduleBinding scheduleItemBinding;
        ViewHolder(@NonNull ItemScheduleBinding scheduleItemBinding) {
            super(scheduleItemBinding.getRoot());
            this.scheduleItemBinding = scheduleItemBinding;

        }
    }
}
