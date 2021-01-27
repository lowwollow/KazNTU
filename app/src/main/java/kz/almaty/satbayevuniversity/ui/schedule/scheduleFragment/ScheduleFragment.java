package kz.almaty.satbayevuniversity.ui.schedule.scheduleFragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.kizitonwose.calendarview.CalendarView;
import com.kizitonwose.calendarview.model.CalendarDay;
import com.kizitonwose.calendarview.ui.DayBinder;
import com.kizitonwose.calendarview.ui.ViewContainer;

import org.threeten.bp.DayOfWeek;
import org.threeten.bp.LocalDate;
import org.threeten.bp.YearMonth;
import org.threeten.bp.format.DateTimeFormatter;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;
import java.util.Objects;

import kz.almaty.satbayevuniversity.AuthViewModel;
import kz.almaty.satbayevuniversity.R;
import kz.almaty.satbayevuniversity.data.AccountDao;
import kz.almaty.satbayevuniversity.data.App;
import kz.almaty.satbayevuniversity.data.AppDatabase;
import kz.almaty.satbayevuniversity.data.SharedPrefCache;
import kz.almaty.satbayevuniversity.data.entity.Language;
import kz.almaty.satbayevuniversity.data.entity.schedule.Schedule;
import kz.almaty.satbayevuniversity.databinding.FragmentScheduleBinding;
import kz.almaty.satbayevuniversity.ui.LoginActivity;
import kz.almaty.satbayevuniversity.utils.LocaleHelper;
import kz.almaty.satbayevuniversity.utils.OnSwipeTouchListener;

import static android.content.ContentValues.TAG;

public class ScheduleFragment extends Fragment implements Cloneable{
    LocalDate selectedDate,oldDate;
    CalendarView calendarView;
    ArrayList<Schedule> localScheduleList;
    private ScheduleViewModel mViewModel;
    private AuthViewModel authViewModel;
    private ScheduleAdapter scheduleAdapter;
    private ImageView imageView;
    private TextView textView;
    LocalDate currentDay = LocalDate.now();

    private AppDatabase db = App.getInstance().getDatabase();
    private AccountDao accountDao = db.accountDao();
    private RecyclerView recyclerView;
    private ConstraintLayout emptyConstraint;
    FragmentScheduleBinding scheduleFragmentBinding;
    private int i = 0;

    private ConnectivityManager connManager = (ConnectivityManager)App.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
    private NetworkInfo activeNetwork = connManager.getActiveNetworkInfo();

    private DateTimeFormatter dayOfMonthFormatter = DateTimeFormatter.ofPattern("d");
    private DateTimeFormatter dayOfWeekFormatter = DateTimeFormatter.ofPattern("EE");

    public ScheduleFragment() {}

    public static ScheduleFragment newInstance() {
        return new ScheduleFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        scheduleFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_schedule, container, false);
        View view = scheduleFragmentBinding.getRoot();
        emptyConstraint = view.findViewById(R.id.emptyConstraint);
        calendarView =  view.findViewById(R.id.weekCalendar);
        return view;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ScheduleViewModel.class);
        authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);
        scheduleFragmentBinding.setSchedule(mViewModel);
        scheduleFragmentBinding.scheduleRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        scheduleFragmentBinding.scheduleRecyclerView.setHasFixedSize(true);
        scheduleFragmentBinding.scheduleRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        scheduleAdapter = new ScheduleAdapter(getActivity());
        scheduleFragmentBinding.scheduleRecyclerView.setAdapter(scheduleAdapter);
        scheduleFragmentBinding.scheduleRecyclerView.setItemAnimator(null);

        String lang = getResources().getConfiguration().locale.toString();
        getFromServer(lang);

        setDateSchedule(currentDay);

        if (((AppCompatActivity)getActivity()).getSupportActionBar() != null) {
            ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.schedule);
        }
        scheduleFragmentBinding.scheduleRecyclerView.setNestedScrollingEnabled(false);
        //swipe weekCalendar
        scheduleFragmentBinding.scheduleRecyclerView.setOnTouchListener(new OnSwipeTouchListener(getContext()) {
            public void onSwipeRight() {
                oldDate = currentDay;
                currentDay = currentDay.minusDays(1);
                setDateSchedule(currentDay);
                calendarView.notifyDateChanged(oldDate);
                selectedDate = currentDay;
                calendarView.notifyDateChanged(selectedDate);
                calendarView.scrollToDate(currentDay);
            }

            public void onSwipeLeft() {
                oldDate = currentDay;
                currentDay = currentDay.plusDays(1);
                setDateSchedule(currentDay);
                calendarView.notifyDateChanged(oldDate);
                selectedDate = currentDay;
                calendarView.notifyDateChanged(selectedDate);
                calendarView.scrollToDate(currentDay);
            }
        });


        mViewModel.getHandleTimeout().observe(getViewLifecycleOwner(), integer -> {
            if (integer == 1) {
                Toast.makeText(getActivity(), R.string.internetConnection, Toast.LENGTH_SHORT).show();
            }
        });

        mViewModel.getHandleError().observe(getViewLifecycleOwner(), integer -> {
            if (integer == 401) {
                authViewModel.clearDB();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        setCalendar();
    }

    private void setDateSchedule(LocalDate date) {
        mViewModel.getScheduleLiveData().observe(getViewLifecycleOwner(), scheduleList -> {

            ArrayList<Schedule> result = new ArrayList<>();
            localScheduleList = new ArrayList<>(Arrays.asList(
                    new Schedule("7:50", "8:40", 1),
                    new Schedule("8:55", "9:45", 3),
                    new Schedule("10:00", "10:50", 5),
                    new Schedule("11:05", "11:55", 7),
                    new Schedule("12:10", "13:00", 9),
                    new Schedule("13:15", "14:05", 11),
                    new Schedule("14:20", "15:10", 13),
                    new Schedule("15:25", "16:15", 15),
                    new Schedule("16:30", "17:20", 17),
                    new Schedule("17:30", "18:20", 19),
                    new Schedule("18:30", "19:20", 21),
                    new Schedule("19:30", "20:20", 23),
                    new Schedule("20:30", "21:20", 25),
                    new Schedule("21:30", "22:20", 27)));
            for (Schedule schedule : scheduleList) {
                if (schedule.getDayOfWeekId() == date.getDayOfWeek().getValue()) {
                    result.add(Schedule.copy(schedule));
                }
            }
            function:
                for (int i=0;i<localScheduleList.size();i++) {
                for (int j=0;j<result.size();j++) {
                    if(i == localScheduleList.size()-1 && localScheduleList.get(i).getStartTimeId() == result.get(j).getStartTimeId() ){
                        continue function;
                    }else if (localScheduleList.get(i).getStartTimeId() == result.get(j).getStartTimeId() ) {
                        if (localScheduleList.get(i+1).getEndTime().contains(result.get(j).getEndTime())) {
                                Schedule schedule1 = Schedule.copy(result.get(j));
                                schedule1.setStartTime(localScheduleList.get(i + 1).getStartTime());
                                schedule1.setStartTimeId(result.get(j).getStartTimeId() + 2);
                                result.add(schedule1);
                                result.get(j).setEndTime(localScheduleList.get(i).getEndTime());
                        }
                        continue function;
                    }
                }
                result.add(localScheduleList.get(i));
            }

            Collections.sort(result, Schedule.CompareId);

            for (Schedule schedule : result) {
                if (schedule.getDayOfWeekId() == 0){
                    i++;
                }
            }
            if(i==14){
                emptyConstraint.setVisibility(View.VISIBLE);
            } else{
                emptyConstraint.setVisibility(View.INVISIBLE);
            }
            scheduleAdapter.setScheduleList(result);
            i = 0;
        });

    }
    private void setCalendar(){
        class DayViewContainer extends ViewContainer {
            CalendarDay day;
            public DayViewContainer(View view) {
                super(view);
                final TextView dayOfMonthText = view.findViewById(R.id.dayOfMonthText);
                dayOfMonthText.setOnClickListener(v -> {
                    oldDate = selectedDate;
                    selectedDate = day.getDate();
                    currentDay = day.getDate();
                    setDateSchedule(currentDay);
                    calendarView.notifyDateChanged(selectedDate);
                    if(oldDate!=null)
                        calendarView.notifyDateChanged(oldDate);
                });
            }
        }
        calendarView.setDayBinder(new DayBinder<DayViewContainer>(){

            @Override
            public DayViewContainer create(View view) {
                return new DayViewContainer(view);
            }
            @Override
            public void bind(DayViewContainer dayViewContainer, CalendarDay calendarDay) {

                dayViewContainer.day = calendarDay;
                TextView dayOfMonth = dayViewContainer.getView().findViewById(R.id.dayOfMonthText);
                dayOfMonth.setText(dayOfMonthFormatter.format(calendarDay.getDate()));

                     if(calendarDay.getDate().equals(selectedDate)){
                        dayOfMonth.setBackgroundResource(R.drawable.day_seleceted_bg);
                        dayOfMonth.setTextColor(getResources().getColor(R.color.colorWhite));
                    }else if(calendarDay.getDate().equals(LocalDate.now())){
                        dayOfMonth.setBackgroundResource(R.drawable.today_bg);
                        dayOfMonth.setTextColor(getResources().getColor(R.color.black));
                    }else{
                        dayOfMonth.setBackground(null);
                        dayOfMonth.setTextColor(getResources().getColor(R.color.black));
                    }
            }
        });
        YearMonth currentMonth = YearMonth.now();
        YearMonth firstMonth = currentMonth.minusMonths(1);
        YearMonth lastMonth = currentMonth.plusMonths(1);
        DayOfWeek firstDayOfWeek = DayOfWeek.MONDAY;
        calendarView.setup(firstMonth, lastMonth, firstDayOfWeek);
        calendarView.scrollToDate(LocalDate.now());
    }

    private void getFromServer(String lang){
        if (lang.equals("kk")){
            mViewModel.getSchedule("kz");
        }else {
            mViewModel.getSchedule("ru");
        }
    }
}
