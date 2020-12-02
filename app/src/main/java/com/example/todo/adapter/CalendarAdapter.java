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
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo.R;
import com.example.todo.models.Task;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.CalendarAdapterViewHolder> {
    // private final Activity context;
    private List<Task> dailyGoals = new ArrayList<>();

    public static class CalendarAdapterViewHolder extends RecyclerView.ViewHolder {
        public TextView textCalendarTaskName;
        public TextView textCalendarTaskDescription;
        public TextView textCalendarTaskImportance;
        public TextView textCalendarModuleName;
        public TextView textCalendarDueTime;

        public CalendarAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            textCalendarTaskName = itemView.findViewById(R.id.calendar_task_name);
            textCalendarTaskDescription = itemView.findViewById(R.id.calendar_task_description);
            textCalendarTaskImportance = itemView.findViewById(R.id.calendar_task_importance);
            textCalendarModuleName = itemView.findViewById(R.id.calendar_module_name);
            textCalendarDueTime = itemView.findViewById(R.id.calendar_due_time);
        }

        private void bind(Task task) {
            String taskName = task.taskName;
            String taskDescription = task.description;
            int taskImportance = task.importance;
            String taskModuleName = task.module;
            String taskDueTime = task.dueTime;

            // Bind data to non-null fields
            textCalendarTaskName.setText(taskName);
            textCalendarTaskImportance.setText(generateImportance(taskImportance));
            textCalendarModuleName.setText(taskModuleName);

            // Check for null before binding
            bindOrHideTextView(textCalendarTaskDescription, taskDescription);
            bindOrHideTextView(textCalendarDueTime, taskDueTime);

        }

        private String generateImportance(int importance) {
            String importanceString = "";

            for (int i = 0; i < importance; i++) {
                importanceString += "!";
            }

            return importanceString;
        }

        private void bindOrHideTextView(TextView textView, String data) {
            if (data == null) {
                textView.setVisibility(View.GONE);
            } else {
                textView.setText(data);
                textView.setVisibility(View.VISIBLE);
            }
        }
    }

    @NonNull
    @Override
    public CalendarAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.calendar_row_items, parent, false);
        return new CalendarAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarAdapterViewHolder holder, int position) {
        holder.bind(dailyGoals.get(position));
    }

    @Override
    public int getItemCount() {
        return (dailyGoals == null) ? 0 : dailyGoals.size();
    }

    public void setDailyGoals(List<Task> dailyGoals) {
        this.dailyGoals = dailyGoals;
        notifyDataSetChanged();
    }

    public Task removeTask(int position) {
        Task completedTask = dailyGoals.get(position);
        dailyGoals.remove(completedTask);
        notifyItemRemoved(position);
        return completedTask;
    }



//    public CalendarAdapter(Activity context, List<Task> dailyGoals) {
//        super(context, R.layout.home_row_item, dailyGoals);
//        this.context = context;
//        this.dailyGoals = dailyGoals;
//    }
//
//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        View rowView = convertView;
//
//        // Reuse views
//        if (rowView == null) {
//            LayoutInflater inflater = context.getLayoutInflater();
//            rowView = inflater.inflate(R.layout.calendar_row_items, null);
//
//            // Configure view holder
//            CalendarAdapter.CalendarAdapterViewHolder viewHolder = new CalendarAdapter.CalendarAdapterViewHolder();
//            viewHolder.textCalendarTaskName = (TextView) rowView.findViewById(R.id.calendar_task_name);
//            viewHolder.textCalendarTaskImportance = (TextView) rowView.findViewById(R.id.calendar_task_importance);
//            viewHolder.textCalendarModuleName = (TextView) rowView.findViewById(R.id.calendar_module_name);
//            viewHolder.textCalendarDueTime = (TextView) rowView.findViewById(R.id.calendar_due_time);
//
//            rowView.setTag(viewHolder);
//        }
//
//        // Fill data
//        CalendarAdapter.CalendarAdapterViewHolder holder = (CalendarAdapter.CalendarAdapterViewHolder) rowView.getTag();
//        Task goal = dailyGoals.get(position);
//
//        holder.textCalendarTaskName.setText(goal.taskName);
//        holder.textCalendarTaskImportance.setText(generateImportance(goal.importance));
//        holder.textCalendarModuleName.setText(goal.module);
//        holder.textCalendarDueTime.setText(goal.dueTime);
//
//        return rowView;
//
//    }

}
