package com.example.todo.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.todo.R;
import com.example.todo.models.Task;

import java.util.List;

public class DailyGoalsAdapter extends ArrayAdapter<Task> {
    private final Activity context;
    private final List<Task> dailyGoals;

    static class TaskViewHolder {
        public TextView textTaskName;
        public TextView textTaskImportance;
        public TextView textModuleName;
        public TextView textDueDate;
        public TextView textDueTime;
    }

    public DailyGoalsAdapter(Activity context, List<Task> dailyGoals) {
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
            rowView = inflater.inflate(R.layout.home_row_item, null);

            // Configure view holder
            TaskViewHolder taskViewHolder = new TaskViewHolder();
            taskViewHolder.textTaskName = (TextView) rowView.findViewById(R.id.task_name);
            taskViewHolder.textTaskImportance = (TextView) rowView.findViewById(R.id.task_importance);
            taskViewHolder.textModuleName = (TextView) rowView.findViewById(R.id.module_name);
            taskViewHolder.textDueDate = (TextView) rowView.findViewById(R.id.due_date);
            taskViewHolder.textDueTime = (TextView) rowView.findViewById(R.id.due_time);

            rowView.setTag(taskViewHolder);
        }

        // Fill data
        TaskViewHolder holder = (TaskViewHolder) rowView.getTag();
        Task goal = dailyGoals.get(position);

        holder.textTaskName.setText(goal.taskName);
        holder.textTaskImportance.setText(generateImportance(goal.importance));
        holder.textModuleName.setText(goal.module);
        holder.textDueDate.setText(goal.dueDate);
        holder.textDueTime.setText(goal.dueTime);

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