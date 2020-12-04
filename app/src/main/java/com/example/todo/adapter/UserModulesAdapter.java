package com.example.todo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo.R;
import com.example.todo.models.Module;

import java.util.ArrayList;
import java.util.List;

public class UserModulesAdapter extends RecyclerView.Adapter<UserModulesAdapter.UserModulesViewHolder> {

    private List<Module> userModules = new ArrayList<>();

    public static class UserModulesViewHolder extends RecyclerView.ViewHolder {
        public TextView textCourseName;
        public TextView textCourseCode;

        public UserModulesViewHolder(@NonNull View itemView) {
            super(itemView);
            textCourseName = itemView.findViewById(R.id.user_course_name);
            textCourseCode = itemView.findViewById(R.id.user_course_code);
        }

        private void bind(Module module) {
            String courseName = module.courseName;
            String courseCode = module.courseCode;

            textCourseName.setText(courseName);
            textCourseCode.setText(courseCode);
        }
    }

    @NonNull
    @Override
    public UserModulesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.user_mods_row_item, parent, false);
        return new UserModulesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserModulesViewHolder holder, int position) {
        holder.bind(userModules.get(position));
    }

    @Override
    public int getItemCount() {
        return (userModules == null) ? 0 : userModules.size();
    }

    public void setUserModules(List<Module> userModules) {
        this.userModules = userModules;
        notifyDataSetChanged();
    }

    public int removeModule(int position) {
        Module removedModule = userModules.get(position);
        userModules.remove(removedModule);
        notifyItemRemoved(position);
        return removedModule.categoryId;
    }

}
