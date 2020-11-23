package com.example.todo.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.todo.R;
import com.example.todo.adapter.DailyGoalsAdapter;
import com.example.todo.models.Task;

import java.io.IOException;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private ListView listView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        homeViewModel.setContext(getActivity());

//        final TextView textView = root.findViewById(R.id.text_home);
        final ProgressBar progressBar = root.findViewById(R.id.weekly_progress_bar);
        listView = root.findViewById(R.id.daily_tasks_list);

        try {
            List<Task> dailyGoals = (List<Task>) homeViewModel.getGoals();

            DailyGoalsAdapter adapter = new DailyGoalsAdapter(getActivity(), dailyGoals);
            listView.setAdapter(adapter);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        homeViewModel.getWeeklyProgress().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                progressBar.setProgress(integer);
            }
        });

        return root;
    }
}