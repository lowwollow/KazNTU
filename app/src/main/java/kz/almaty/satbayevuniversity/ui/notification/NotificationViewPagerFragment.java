package kz.almaty.satbayevuniversity.ui.notification;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import kz.almaty.satbayevuniversity.R;
import kz.almaty.satbayevuniversity.ui.notification.portalNotification.PortalNotificationFragment;
import kz.almaty.satbayevuniversity.ui.notification.pushNotification.PushNotificationFragment;
import kz.almaty.satbayevuniversity.ui.schedule.CustomViewPager;
import kz.almaty.satbayevuniversity.ui.viewPager.ViewPagerAdapter;

public class NotificationViewPagerFragment extends Fragment {
    private CustomViewPager viewPager;
    private TabLayout tabLayout;
    private ViewPagerAdapter viewPagerAdapter;

    public static NotificationViewPagerFragment newInstance() {
        return new NotificationViewPagerFragment();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState) {
        View view = layoutInflater.inflate(R.layout.fragment_view_pager_schedule, container, false);
        viewPager = view.findViewById(R.id.viewPagerSchedule);
        tabLayout = view.findViewById(R.id.tabLayoutSchedule);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewPager.setPagingEnabled(true);
        viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        viewPagerAdapter.addFragment(new PortalNotificationFragment(), "Портал");
        viewPagerAdapter.addFragment(new PushNotificationFragment(), "Push");

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0)
                    viewPager.setAllowedSwipeDirection(CustomViewPager.SwipeDirection.all);
                else
                    viewPager.setAllowedSwipeDirection(CustomViewPager.SwipeDirection.left);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}
