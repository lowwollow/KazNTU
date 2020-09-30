package kz.almaty.satbayevuniversity.ui.notification.portalNotification;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import kz.almaty.satbayevuniversity.R;
import kz.almaty.satbayevuniversity.data.entity.notification.Notification;
import kz.almaty.satbayevuniversity.databinding.NotificationShortNewsBinding;
import kz.almaty.satbayevuniversity.ui.notification.portalNotification.webViewNotification.WebViewNotification;

public class PortalNotificationAdapter extends RecyclerView.Adapter<PortalNotificationAdapter.ViewHolder> implements PortalNotificationListener {
    private List<Notification> notifications;
    private Context context;

    public PortalNotificationAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public PortalNotificationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NotificationShortNewsBinding notificationShortNewsBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.notification_short_news, parent, false);
        return new PortalNotificationAdapter.ViewHolder(notificationShortNewsBinding);

    }

    @Override
    public void onBindViewHolder(@NonNull PortalNotificationAdapter.ViewHolder holder, int position) {
        Notification currentNotification = notifications.get(position);
        holder.notificationShortNewsBinding.setNotification(currentNotification);
        holder.notificationShortNewsBinding.getWebClickListener();
        holder.notificationShortNewsBinding.setWebClickListener(this);
    }

    @Override
    public int getItemCount() {
        return (notifications == null) ? 0 : notifications.size();
    }

    void setResponseNotificationList(List<Notification> notifications) {
        this.notifications = notifications;
        notifyDataSetChanged();
    }

    @Override
    public void WebClicked(Notification notification) {
        Intent in = new Intent(context, WebViewNotification.class);
        in.putExtra("WebViewNotification", notification);
        context.startActivity(in);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        NotificationShortNewsBinding notificationShortNewsBinding;
        public ViewHolder(@NonNull NotificationShortNewsBinding notificationShortNewsBinding) {
            super(notificationShortNewsBinding.getRoot());
            this.notificationShortNewsBinding = notificationShortNewsBinding;
        }
    }
}
