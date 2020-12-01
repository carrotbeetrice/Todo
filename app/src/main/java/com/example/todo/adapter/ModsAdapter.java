package com.example.todo.adapter;

import android.app.Activity;
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

import java.util.ArrayList;
import java.util.List;

public class ModsAdapter extends ArrayAdapter<Task> {
    private final Activity context;
    private List<Task> tasks;

    static class ModsHolder{
        private TextView modName;
        private TextView taskTitle;
        private TextView deadlineDate;
        private TextView deadlineTime;
    }

        public ModsAdapter(Activity context, List<Task> tasks) {
            super(context, R.layout.mods, tasks);
            this.context = context;
            this.tasks = tasks;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View modView = convertView;
                if (modView == null){
                    LayoutInflater inflater = context.getLayoutInflater();
                    modView = inflater.inflate(R.layout.mods, null);

                    // Configure view holder
                    ModsHolder modsHolder = new ModsHolder();

                    modsHolder.modName = (TextView) modView.findViewById(R.id.mod_name);
                    modsHolder.taskTitle = (TextView) modView.findViewById(R.id.task_title);
                    modsHolder.deadlineDate = (TextView) modView.findViewById(R.id.deadline_date);
                    modsHolder.deadlineTime = (TextView) modView.findViewById(R.id.deadline_time);

                    modView.setTag(modsHolder);

                }

            // Fill data
            ModsAdapter.ModsHolder holder = (ModsAdapter.ModsHolder) modView.getTag();
            Task task = tasks.get(position);

            holder.modName.setText(task.module);
            holder.taskTitle.setText(task.taskName);
            holder.deadlineDate.setText(task.dueDate);
            holder.deadlineTime.setText(task.dueTime);

            return modView;
        }


        }


