package com.example.todo.ui.addtask;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.todo.R;
import com.example.todo.adapter.ModulesAdapter;
import com.example.todo.models.Courses;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AddTaskFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private AddTaskViewModel addTaskViewModel;
    private List<String> moduleList = new ArrayList<>();
    private EditText editTaskName, editTaskDescription, editDueDate, editDueTime;
    private Spinner moduleSpinner;
    private Button btnDatePicker, btnTimePicker;
    private int mYear, mMonth, mDay, mHour, mMinute;

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
                moduleList = strings;
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, strings);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                moduleSpinner.setAdapter(adapter);
                moduleSpinner.setOnItemSelectedListener(AddTaskFragment.this);
            }
        });

        return root;


    }

    private void setWidgetReferences(View view){
        editTaskName = view.findViewById(R.id.task_name_input);
        editTaskDescription = view.findViewById(R.id.task_description_input);
        moduleSpinner = view.findViewById(R.id.module_name);
        editDueDate = view.findViewById(R.id.due_date_input);
        btnDatePicker = view.findViewById(R.id.select_date_button);
        editDueTime = view.findViewById(R.id.due_time_input);
        btnTimePicker = view.findViewById(R.id.select_time_button);
    }

    private void setEventListeners() {
        // Event Listener for Due Date picker
        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get current date
                final Calendar calendar = Calendar.getInstance();
                mYear = calendar.get(Calendar.YEAR);
                mMonth = calendar.get(Calendar.MONTH);
                mDay = calendar.get(Calendar.DAY_OF_MONTH);

                // Launch a DatePickerDialog
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        editDueDate.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                }
                }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        // Event Listener for Due Time picker
        btnTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                mHour = calendar.get(Calendar.HOUR_OF_DAY);
                mMinute = calendar.get(Calendar.MINUTE);

                // Launch a TimePickerDialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        editDueTime.setText(hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, true);
                timePickerDialog.show();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // Set selected module
        addTaskViewModel.task.module = moduleList.get(position);

        Toast.makeText(getContext(), "Smth selected idk", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}