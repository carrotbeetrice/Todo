package com.example.todo.ui.addtask;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.todo.models.AddTaskModel;
import com.example.todo.models.Courses;
import com.example.todo.models.Reminders;

import java.util.List;

public class AddTaskViewModel extends ViewModel {

//    private MutableLiveData<String> mText;
    protected AddTaskModel addTaskModel;
    private MutableLiveData<List<Courses>> mCourses;
    private MutableLiveData<List<Reminders>> mReminders;

    public AddTaskViewModel() {
    }

    public void setAddTaskModel(Context context) {
        addTaskModel = new AddTaskModel(context);
        addTaskModel.getDropDownData();
    }

    public LiveData<List<Courses>> getCourses() {
        mCourses = new MutableLiveData<>();
        mCourses.setValue(addTaskModel.getCourses());
        return mCourses;
    }

    public LiveData<List<Reminders>> getReminders() {
        mReminders = new MutableLiveData<>();
        mReminders.setValue(addTaskModel.getReminders());
        return mReminders;
    }

}