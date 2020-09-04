package kz.almaty.satbayevuniversity.ui.schedule;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;

import kz.almaty.satbayevuniversity.R;
import kz.almaty.satbayevuniversity.ui.schedule.exams.ExamsFragment;
import kz.almaty.satbayevuniversity.ui.schedule.scheduleFragment.ScheduleFragment;
import kz.almaty.satbayevuniversity.ui.viewPager.ViewPagerAdapter;

public class ViewPagerSchedule extends Fragment {

    private CustomViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private TabLayout tabLayout;


    public static ViewPagerSchedule newInstance() {
        return new ViewPagerSchedule();
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_pager_schedule, container, false);

        tabLayout = view.findViewById(R.id.tabLayoutSchedule);
        viewPager = view.findViewById(R.id.viewPagerSchedule);
        return view;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewPager.setPagingEnabled(false);
        viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        viewPagerAdapter.addFragment(new ScheduleFragment(), getString(R.string.schedule));
        viewPagerAdapter.addFragment(new ExamsFragment(), getString(R.string.exam));

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }
}
