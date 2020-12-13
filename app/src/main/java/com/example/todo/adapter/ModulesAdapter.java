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
import com.example.todo.models.Courses;

import java.util.List;

public class ModulesAdapter extends ArrayAdapter<Courses> {
    private final Activity context;
    private final List<Courses> modules;

    static class ModulesViewHolder {
        public TextView textCourseCode;
        public TextView textCourseName;
    }

    public ModulesAdapter(Activity context, List<Courses> modules) {
        super(context, R.layout.course_item, modules);
        this.context = context;
        this.modules = modules;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View rowView = convertView;

        if (rowView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.course_item, null);

            // Configure view holder
            ModulesViewHolder modulesViewHolder = new ModulesViewHolder();
            modulesViewHolder.textCourseName = rowView.findViewById(R.id.course_name);

            rowView.setTag(modulesViewHolder);
        }

        // Fill data
        ModulesViewHolder holder = (ModulesViewHolder) rowView.getTag();
        Courses module = modules.get(position);

        holder.textCourseCode.setText(module.code);
        holder.textCourseName.setText(module.courseName);

        return rowView;
    }
}
