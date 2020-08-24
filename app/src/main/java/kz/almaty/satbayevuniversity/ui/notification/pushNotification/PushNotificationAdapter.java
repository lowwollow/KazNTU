package kz.almaty.satbayevuniversity.ui.notification.pushNotification;


import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import kz.almaty.satbayevuniversity.R;
import kz.almaty.satbayevuniversity.data.entity.notification.PushNotification;
import kz.almaty.satbayevuniversity.databinding.ItemPushNotificationBinding;

public class PushNotificationAdapter  extends RecyclerView.Adapter<PushNotificationAdapter.ViewHolder> {
    HashMap<PushNotification, Runnable> pendingRunnables = new HashMap<>();
    private static final int PENDING_REMOVAL_TIMEOUT = 3000;
    private Handler handler = new Handler();
    boolean undoOn;
    public List<PushNotification> listOfPushNotification = new ArrayList<>();
    List<PushNotification> itemsPendingRemoval = new ArrayList<>();
    ItemPushNotificationBinding pushNotificationItemBinding;
    PushNotificationViewModel pushNotificationViewModel;
    Context context;
    PushNotificationAdapter(Context context, PushNotificationViewModel pushNotificationViewModel){
        this.context = context;
        this.pushNotificationViewModel = pushNotificationViewModel;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        pushNotificationItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_push_notification,parent,false);
        return new ViewHolder(pushNotificationItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        PushNotification pushNotification = listOfPushNotification.get(position);
        holder.pushNotificationItemBinding.setPushNotification(pushNotification);

        holder.pushNotificationItemBinding.setLanguage(getCurrentLanguageFromApp());

        if(pushNotification.getTextRus().contains("посещаемость") || pushNotification.getTextRus().contains("пропуск")){
            holder.pushNotificationItemImageView.setImageResource(R.drawable.running_orange);
        }else{
            holder.pushNotificationItemImageView.setImageResource(R.drawable.point);
        }

    }
    public String getCurrentLanguageFromApp(){
        Locale locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
             locale = context.getResources().getConfiguration().getLocales().get(0);
        }else{
             locale = context.getResources().getConfiguration().locale;
        }
        return locale.getDefault().toString();
    }
    @Override
    public int getItemCount() {
        return listOfPushNotification.size();
    }
    public void setNotificationList(List<PushNotification> listOfPushNotification){
        this.listOfPushNotification.clear();
        this.listOfPushNotification.addAll(listOfPushNotification);
        notifyDataSetChanged();
    }


    public boolean isUndoOn() {
        return undoOn;
    }
    public boolean isPendingRemoval(int position) {
        PushNotification pushNotification = listOfPushNotification.get(position);
        return itemsPendingRemoval.contains(pushNotification);
    }
    public void pendingRemoval(int position) {
        final PushNotification pushNotification = listOfPushNotification.get(position);
        if (!itemsPendingRemoval.contains(pushNotification)) {
            itemsPendingRemoval.add(pushNotification);
            // this will redraw row in "undo" state
            notifyItemChanged(position);
            // let's create, store and post a runnable to remove the item
            Runnable pendingRemovalRunnable = new Runnable() {
                @Override
                public void run() {
                    remove(listOfPushNotification.indexOf(pushNotification));
                }
            };
            handler.postDelayed(pendingRemovalRunnable, PENDING_REMOVAL_TIMEOUT);
            pendingRunnables.put(pushNotification, pendingRemovalRunnable);
        }
    }
    public void remove(int position) {
        PushNotification pushNotification = listOfPushNotification.get(position);
        if (itemsPendingRemoval.contains(pushNotification)) {
            itemsPendingRemoval.remove(pushNotification);
        }
        if (listOfPushNotification.contains(pushNotification)) {
            listOfPushNotification.remove(position);
            notifyItemRemoved(position);

            int pushId = pushNotification.getId();
            pushNotificationViewModel.removeItem(pushId);
            if(listOfPushNotification.isEmpty()){
                pushNotificationViewModel.isEmpty.set(true);
            }

        }
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        ItemPushNotificationBinding pushNotificationItemBinding;
        ImageView pushNotificationItemImageView;
        public ViewHolder(ItemPushNotificationBinding pushNotificationItemBinding) {
            super(pushNotificationItemBinding.getRoot());

            this.pushNotificationItemBinding = pushNotificationItemBinding;
            pushNotificationItemImageView = pushNotificationItemBinding.pushNotificationItemImageView;
        }
    }
}
