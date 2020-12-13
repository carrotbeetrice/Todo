package com.example.todo.ui.home;

import android.os.Bundle;
import android.util.Log;
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

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private ProgressBar weeklyProgressBar;
    private TextView textProgressPercentage;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        setWidgetReferences(root);

        homeViewModel.setContext(getActivity());

        homeViewModel.getWeeklyProgress().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                weeklyProgressBar.setProgress(integer);
                textProgressPercentage.setText(integer + "%");
            }
        });

        return root;
    }

    private void setWidgetReferences(View view) {
        weeklyProgressBar = view.findViewById(R.id.weekly_progress_bar);
        textProgressPercentage = view.findViewById(R.id.progress_percentage);
    }
}