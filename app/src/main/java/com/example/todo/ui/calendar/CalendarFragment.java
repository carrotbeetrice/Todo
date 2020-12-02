package com.example.todo.ui.calendar;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.todo.R;
import com.example.todo.adapter.CalendarAdapter;
import com.example.todo.adapter.DailyGoalsAdapter;
import com.example.todo.models.Task;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.io.IOException;
import java.util.List;

public class CalendarFragment extends Fragment {

    private CalendarViewModel calendarViewModel;
    private ListView listView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        calendarViewModel =
                ViewModelProviders.of(this).get(CalendarViewModel.class);

        View root = inflater.inflate(R.layout.fragment_calendar, container, false);

        final MaterialCalendarView materialCalendarView = (MaterialCalendarView) root.findViewById(R.id.calendarView);
        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                int daySelected = materialCalendarView.getSelectedDate().getDay();
                int monthSelected = materialCalendarView.getSelectedDate().getMonth();
                int yearSelected = materialCalendarView.getSelectedDate().getYear();
                String dateSelected =  String.format("%d-%02d-%02d", yearSelected , monthSelected + 1 , daySelected);
                //Toast.makeText(getActivity(), dateSelected, Toast.LENGTH_SHORT).show();

                try{
                    calendarViewModel.getDairyGoals(dateSelected).observe(getViewLifecycleOwner(),new Observer<List<Task>>() {
                        @Override
                        public void onChanged(List<Task> tasks) {
                            CalendarAdapter adapter = new CalendarAdapter(getActivity(), tasks);
                            listView.setAdapter(adapter);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        calendarViewModel.setContext(getActivity());

        listView = root.findViewById(R.id.text_calendar);
        /*
        calendarViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
         */
        return root;
    }


}