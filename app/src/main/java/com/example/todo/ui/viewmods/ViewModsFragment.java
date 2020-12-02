package com.example.todo.ui.viewmods;

import android.content.Intent;
import android.os.Bundle;
import android.text.style.AlignmentSpan;
import android.util.Log;
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
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo.AddMods.AddModsActivity;
import com.example.todo.R;
import com.example.todo.adapter.ModsAdapter;
import com.example.todo.adapter.ModulesAdapter;
import com.example.todo.models.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.List;

public class ViewModsFragment extends Fragment {

    // TODO: This one too
    private ViewModsViewModel viewModsViewModel;
//    private ListView listView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             final ViewGroup container, Bundle savedInstanceState) {
        viewModsViewModel =
                ViewModelProviders.of(this).get(ViewModsViewModel.class);



        View root = inflater.inflate(R.layout.fragment_viewmods, container, false);
        viewModsViewModel.setContext(getActivity());

        FloatingActionButton fab = root.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(), AddModsActivity.class);
                        startActivity(intent);

                    }



                }
        );






















        RecyclerView recyclerView = root.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        final ModsAdapter adapter = new ModsAdapter();
        recyclerView.setAdapter(adapter);


//        try {
//            viewModsViewModel.getMods().observe(getViewLifecycleOwner(), new Observer<List<Task>>() {
//                    @Override
//                    public void onChanged(@Nullable List<Task> tasks) {
//                        adapter.setTasks(tasks);
//                    }});
//
//            }catch(IOException e) {
//                e.printStackTrace();
//            }
//        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT| ItemTouchHelper.RIGHT) {
//            @Override
//            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
//                return false;
//            }
//
//            @Override
//            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//                int position = viewHolder.getAdapterPosition();
//                viewModsViewModel.updateMods(position);
//                adapter.notifyDataSetChanged();
//
//
//
//
//            }
//        }).attachToRecyclerView(recyclerView);
        return root;
    }}





    //
    //
    //        viewModsViewModel.setContext(getActivity());

    //
    //
    //        listView = root.findViewById(R.id.uncompleted_task_list);
    //
    //
    //
    //        try {
    //            viewModsViewModel.getMods().observe(getViewLifecycleOwner(), new Observer<List<Task>>() {
    //                @Override
    //                public void onChanged(@Nullable List<Task> tasks) {
    //                    ModsAdapter adapter = new ModsAdapter(getActivity(),tasks);
    //                    listView.setAdapter(adapter);
    //
    //                    }
    //
    //
    //
    //            });
    //        } catch (IOException e) {
    //            e.printStackTrace();
    //        }
    //        return root;
    //    }
    //}
