package com.example.todo.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.todo.R;
import com.example.todo.models.Task;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class CalendarAdapter extends ArrayAdapter<Task>{
    private final Activity context;
    private final List<Task> dailyGoals;

    static class ViewHolder {
        public TextView textCalendarTaskName;
        public TextView textCalendarTaskImportance;
        public TextView textCalendarModuleName;
        public TextView textCalendarDueTime;
    }

    public CalendarAdapter(Activity context, List<Task> dailyGoals) {
        super(context, R.layout.home_row_item, dailyGoals);
        this.context = context;
        this.dailyGoals = dailyGoals;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View rowView = convertView;

        // Reuse views
        if (rowView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.calendar_row_items, null);

            // Configure view holder
            CalendarAdapter.ViewHolder viewHolder = new CalendarAdapter.ViewHolder();
            viewHolder.textCalendarTaskName = (TextView) rowView.findViewById(R.id.calendar_task_name);
            viewHolder.textCalendarTaskImportance = (TextView) rowView.findViewById(R.id.calendar_task_importance);
            viewHolder.textCalendarModuleName = (TextView) rowView.findViewById(R.id.calendar_module_name);
            viewHolder.textCalendarDueTime = (TextView) rowView.findViewById(R.id.calendar_due_time);

            rowView.setTag(viewHolder);
        }

        // Fill data
        CalendarAdapter.ViewHolder holder = (CalendarAdapter.ViewHolder) rowView.getTag();
        Task goal = dailyGoals.get(position);

        holder.textCalendarTaskName.setText(goal.taskName);
        holder.textCalendarTaskImportance.setText(generateImportance(goal.importance));
        holder.textCalendarModuleName.setText(goal.module);
        holder.textCalendarDueTime.setText(goal.dueTime);

        return rowView;

    }

    private String generateImportance(int importance) {
        String importanceString = "";

        for (int i = 0; i < importance; i++) {
            importanceString += "!";
        }

        return importanceString;
    }
}
