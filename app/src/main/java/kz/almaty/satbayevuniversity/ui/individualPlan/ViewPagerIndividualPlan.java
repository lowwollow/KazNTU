package kz.almaty.satbayevuniversity.ui.individualPlan;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import kz.almaty.satbayevuniversity.R;
import kz.almaty.satbayevuniversity.ui.individualPlan.chosenDisciplines.ChosenDisciplineFragment;
import kz.almaty.satbayevuniversity.ui.individualPlan.deferedDisciplnes.DeferedDisciplineFragment;
import kz.almaty.satbayevuniversity.ui.viewPager.ViewPagerAdapter;

public class ViewPagerIndividualPlan extends Fragment {

    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private TabLayout tabLayout;

    public static ViewPagerIndividualPlan newInstance(){
        return new ViewPagerIndividualPlan();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_pager, container, false);
        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.viewPager);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.individualPlan);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager){
        viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        viewPagerAdapter.addFragment(new ChosenDisciplineFragment(), getString(R.string.chosen));
        viewPagerAdapter.addFragment(new DeferedDisciplineFragment(), getString(R.string.delayed));
        viewPager.setAdapter(viewPagerAdapter);
    }
}
