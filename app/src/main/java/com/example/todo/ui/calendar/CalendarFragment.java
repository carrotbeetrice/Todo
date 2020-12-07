package com.example.todo.ui.calendar;

import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo.R;
import com.example.todo.adapter.CalendarAdapter;
import com.example.todo.models.Task;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CalendarFragment extends Fragment {

    private CalendarViewModel calendarViewModel;
    private RecyclerView calendarRecyclerView;
    private CalendarAdapter calendarAdapter;
    private List<Task> dailyTasks = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        calendarViewModel =
                ViewModelProviders.of(this).get(CalendarViewModel.class);
        calendarViewModel.setContext(getActivity());

        View root = inflater.inflate(R.layout.fragment_calendar, container, false);

        final MaterialCalendarView materialCalendarView = root.findViewById(R.id.calendarView);

        calendarRecyclerView = root.findViewById(R.id.calendar_task_list);
        addTouchHelper();

        calendarAdapter = new CalendarAdapter();

        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                int daySelected = materialCalendarView.getSelectedDate().getDay();
                int monthSelected = materialCalendarView.getSelectedDate().getMonth();
                int yearSelected = materialCalendarView.getSelectedDate().getYear();
                String dateSelected =  String.format("%d-%02d-%02d", yearSelected , monthSelected + 1 , daySelected);

                calendarViewModel.getDailyGoals(dateSelected).observe(getViewLifecycleOwner(),new Observer<List<Task>>() {
                    @Override
                    public void onChanged(List<Task> tasks) {
                        dailyTasks = tasks;

                        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                        layoutManager.setOrientation(RecyclerView.VERTICAL);

                        calendarAdapter.setDailyGoals(tasks);

                        calendarRecyclerView.setLayoutManager(layoutManager);
                        calendarRecyclerView.setAdapter(calendarAdapter);

                    }
                });

            }
        });

        materialCalendarView.setDateSelected(new Date(), true);

        return root;
    }


    private void addTouchHelper() {

        // Swipe left to mark complete
        ItemTouchHelper.SimpleCallback itemTouchHelperCallBack = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                Task completedTask = calendarAdapter.removeTask(position);

                calendarViewModel.setTaskCompleted(completedTask.taskId);

            }

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };

        new ItemTouchHelper(itemTouchHelperCallBack).attachToRecyclerView(calendarRecyclerView);
    }



}