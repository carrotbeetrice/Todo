package com.example.todo.ui.addtask;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.todo.models.AddTaskModel;
import com.example.todo.models.Courses;
import com.example.todo.models.Reminders;
import com.example.todo.models.Task;

import java.util.List;

public class AddTaskViewModel extends ViewModel {

    protected AddTaskModel addTaskModel;
    private MutableLiveData<List<String>> mCourses;
    // private MutableLiveData<List<Reminders>> mReminders;
    protected Task task;

    public AddTaskViewModel() {
        task = new Task();
    }

    public void setAddTaskModel(Context context) {
        addTaskModel = new AddTaskModel(context);
        addTaskModel.getDropDownData();
    }

    public LiveData<List<String>> getCourses() {
        mCourses = new MutableLiveData<>();
        mCourses.setValue(addTaskModel.getCourses());
        return mCourses;
    }

//    public LiveData<List<Reminders>> getReminders() {
//        mReminders = new MutableLiveData<>();
//        mReminders.setValue(addTaskModel.getReminders());
//        return mReminders;
//    }

}