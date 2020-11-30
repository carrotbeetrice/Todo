package com.example.todo.ui.addtask;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.view.inputmethod.InputMethodManager;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.ScrollView;
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
import com.example.todo.models.Task;
import com.example.todo.utils.InputValidation;

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
    private Button btnDatePicker, btnTimePicker, btnClear, btnAddTask;
    private RatingBar importanceRating;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private boolean taskAddedSuccessfully = false;

    // Shared preferences stuff
    private SharedPreferences preferences;
    private String sharedPrefFile = "com.example.todo.addtasksharedprefs";
    private final static String TASK_NAME_KEY = "TASK_NAME_KEY";
    private final static String TASK_DESC_KEY = "TASK_DESC_KEY";
    //    private final static String MODULE_KEY = "MODULE_KEY";
    private final static String DUE_DATE_KEY = "DUE_DATE_KEY";
    private final static String DUE_TIME_KEY = "DUE_TIME_KEY";
    private final static String IMPORTANCE_KEY = "IMPORTANCE_KEY";

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
                // Create adapter for module spinner
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_spinner_item, strings);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                moduleSpinner.setAdapter(adapter);

                // Spinner click listener
                moduleSpinner.setOnItemSelectedListener(AddTaskFragment.this);
            }
        });

        retrieveSharedPreferences();

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
        importanceRating = view.findViewById(R.id.importance_input);
        btnClear = view.findViewById(R.id.clear_button);
        btnAddTask = view.findViewById(R.id.addtask_button);
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
                        String date = String.format("%d-%02d-%02d", year, month + 1, dayOfMonth);
                        editDueDate.setText(date);
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
                        String time = String.format("%02d:%02d", hourOfDay, minute);
                        editDueTime.setText(time);
                    }
                }, mHour, mMinute, true);
                timePickerDialog.show();
            }
        });

        // Event listener for clear button
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showClearDialog();
            }
        });

        // Event lister for add task button
        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddTaskDialog();
            }
        });
    }


    private void showClearDialog() {
        AlertDialog.Builder clearDialogBuilder = new AlertDialog.Builder(getContext());
        clearDialogBuilder.setMessage("Are you sure you want to clear all your entries?");

        // Set positive button
        clearDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Clear all the entries
                clearAllEntries();
                clearSharedPreferences();

                Toast.makeText(getContext(), "Cleared all entries", Toast.LENGTH_LONG).show();
            }
        });

        // Set negative button
        clearDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog clearDialog = clearDialogBuilder.create();
        clearDialog.show();

    }


    private void showAddTaskDialog() {
        AlertDialog.Builder addTaskDialogBuilder = new AlertDialog.Builder(getContext());
        addTaskDialogBuilder.setMessage("Are you sure you want to save this task?");

        // Set positive button
        addTaskDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Check for invalid entries
                String taskNameInput = editTaskName.getText().toString();
                String taskDescriptionInput = editTaskDescription.getText().toString();
                String moduleSelected = moduleSpinner.getSelectedItem().toString();
                String dueDateInput = editDueDate.getText().toString();
                String dueTimeInput = editDueTime.getText().toString();
                float importanceInput = importanceRating.getRating();

                if (!InputValidation.isValidTextInput(taskNameInput)) {
                    dismissWithToast(dialog,"Task name cannot be empty");
                } else if (!InputValidation.isValidTextInput(dueDateInput)) {
                    dismissWithToast(dialog, "Please set due date");
                } else if (!InputValidation.isValidDueDate(dueDateInput)) {
                    dismissWithToast(dialog, "Invalid date");
                } else if (!InputValidation.isValidRatingInput(importanceInput)) {
                    dismissWithToast(dialog, "Please set task importance");
                } else if (!InputValidation.isValidDueTime(dueTimeInput)) {
                    dismissWithToast(dialog, "Invalid time");
                } else {
                    // Send to viewmodel
                    addTaskViewModel.setTask(taskNameInput, taskDescriptionInput, moduleSelected,
                            dueDateInput, dueTimeInput, (int) importanceInput);

                    String message;
                    if (addTaskViewModel.taskAdded()) {
                        message = "Task added!";
                        taskAddedSuccessfully = true;
                    }
                    else {
                        message = "Something went wrong!";
                    }

                    dismissWithToast(dialog, message);
                }

            }
        });

        // Set negative button
        addTaskDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog addTaskDialog = addTaskDialogBuilder.create();
        addTaskDialog.show();
    }

    private void dismissWithToast(DialogInterface dialog, String message) {
        dialog.dismiss();
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }


    private void retrieveSharedPreferences() {
        preferences = getActivity().getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE);

        // Reset contents of Widgets
        String taskNameSaved = preferences.getString(TASK_NAME_KEY, "");
        String taskDescSaved = preferences.getString(TASK_DESC_KEY, "");
//        String moduleSaved = preferences.getString(MODULE_KEY, "");
        String dueDateSaved = preferences.getString(DUE_DATE_KEY, "");
        String dueTimeSaved = preferences.getString(DUE_TIME_KEY, "");
        float importanceSaved = preferences.getFloat(IMPORTANCE_KEY, 0);

        editTaskName.setText(taskNameSaved);
        editTaskDescription.setText(taskDescSaved);
        editDueDate.setText(dueDateSaved);
        editDueTime.setText(dueTimeSaved);

        // Set previously selected module as selected in spinner
//        if (moduleSaved != "") {
//            ArrayAdapter<String> spinnerAdapter = (ArrayAdapter) moduleSpinner.getAdapter();
//            int position = spinnerAdapter.getPosition(moduleSaved);
//            moduleSpinner.setSelection(position);
//        }

        if (importanceSaved > 0) {
            importanceRating.setRating(importanceSaved);
        }

    }

    private void clearSharedPreferences() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
    }

    private void clearAllEntries() {
        editTaskName.setText("");
        editTaskDescription.setText("");
        moduleSpinner.setSelection(0);
        editDueDate.setText("");
        editDueTime.setText("");
        importanceRating.setRating(0);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        // Set selected module
//        addTaskViewModel.task.module = moduleList.get(position);
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    @Override
    public void onPause() {
        super.onPause();

        if (taskAddedSuccessfully) {
            clearSharedPreferences();
        } else {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(TASK_NAME_KEY, String.valueOf(editTaskName.getText()));
            editor.putString(TASK_DESC_KEY, String.valueOf(editTaskDescription.getText()));
//        editor.putString(MODULE_KEY, moduleSpinner.getSelectedItem().toString());
            editor.putString(DUE_DATE_KEY, String.valueOf(editDueDate.getText()));
            editor.putString(DUE_TIME_KEY, String.valueOf(editDueTime.getText()));
            editor.putFloat(IMPORTANCE_KEY, importanceRating.getRating());

            editor.apply();
        }
    }
}