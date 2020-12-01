package com.example.todo.ui.viewmods;

import android.os.Bundle;
import android.text.style.AlignmentSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo.R;
import com.example.todo.adapter.ModsAdapter;
import com.example.todo.adapter.ModulesAdapter;
import com.example.todo.models.Task;

import java.io.IOException;
import java.util.List;

public class ViewModsFragment extends Fragment {

    private ViewModsViewModel viewModsViewModel;
    private ListView listView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        viewModsViewModel =
                ViewModelProviders.of(this).get(ViewModsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_viewmods, container, false);


        viewModsViewModel.setContext(getActivity());

        listView = root.findViewById(R.id.uncompleted_task_list);



        try {
            viewModsViewModel.getMods().observe(getViewLifecycleOwner(), new Observer<List<Task>>() {
                @Override
                public void onChanged(@Nullable List<Task> tasks) {
                    ModsAdapter adapter = new ModsAdapter(getActivity(),tasks);
                    listView.setAdapter(adapter);

                    }



            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return root;
    }
}