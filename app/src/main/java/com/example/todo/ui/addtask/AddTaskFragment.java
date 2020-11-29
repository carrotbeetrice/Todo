package com.example.todo.ui.addtask;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.todo.R;
import com.example.todo.adapter.ModulesAdapter;
import com.example.todo.models.Courses;

import java.util.List;

public class AddTaskFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private AddTaskViewModel addTaskViewModel;
    Spinner moduleSpinner;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        addTaskViewModel =
                ViewModelProviders.of(this).get(AddTaskViewModel.class);
        View root = inflater.inflate(R.layout.fragment_addtask, container, false);

        setWidgetReferences(root);
        setEventListeners();

        addTaskViewModel.setAddTaskModel(getActivity());

        addTaskViewModel.getCourses().observe(getViewLifecycleOwner(), new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, strings);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                moduleSpinner.setAdapter(adapter);
                moduleSpinner.setOnItemSelectedListener(AddTaskFragment.this);
            }
        });

        return root;


    }

    private void setWidgetReferences(View view){
        moduleSpinner = view.findViewById(R.id.module_name);
    }

    private void setEventListeners() {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getContext(), "Smth selected idk", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}