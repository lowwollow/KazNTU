package kz.almaty.satbayevuniversity.ui.notification.portalNotification;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import kz.almaty.satbayevuniversity.R;
import kz.almaty.satbayevuniversity.databinding.FragmentNotificationBinding;

public class PortalNotificationFragment extends Fragment {

    private PortalNotificationViewModel mViewModel;
    private FragmentNotificationBinding fragmentNotificationBinding;
    private PortalNotificationAdapter notificationAdapter;
    public static final String NOTIFICATION_TAG = "PortalNotificationFragment";

    public static PortalNotificationFragment newInstance() {
        return new PortalNotificationFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        fragmentNotificationBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_notification, container, false);
        View view = fragmentNotificationBinding.getRoot();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PortalNotificationViewModel.class);
        fragmentNotificationBinding.setNotification(mViewModel);
        mViewModel.getNotification();

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.notifications);

        fragmentNotificationBinding.notificationRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        fragmentNotificationBinding.notificationRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        notificationAdapter = new PortalNotificationAdapter(getActivity());
        fragmentNotificationBinding.notificationRecyclerView.setAdapter(notificationAdapter);
        getNotification();

        mViewModel.getHandleTimeout().observe(getViewLifecycleOwner(), aBoolean -> {
            if (aBoolean) {
                Toast.makeText(getActivity(), R.string.internetConnection, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getNotification() {
        mViewModel.getNotificationMutableLiveData().observe(getViewLifecycleOwner(), notifications -> {
            notificationAdapter.setResponseNotificationList(notifications);
        });
    }


}
