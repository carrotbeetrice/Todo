package com.example.todo.ui.calendar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.Toast;

import com.example.todo.models.Calendar;
import com.example.todo.models.Task;

import com.example.todo.R;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.io.IOException;
import java.util.List;

public class CalendarViewModel extends ViewModel {

    protected Calendar calendarModel;
    private MutableLiveData<List<Task>> dailyGoals;

    public CalendarViewModel() {
    }

    public void setContext(Context context){
        calendarModel= new Calendar(context);
    }

    public LiveData<List<Task>> getDailyGoals(String dateSelected) {
        dailyGoals = new MutableLiveData<>();
        dailyGoals.setValue(calendarModel.getGoals(dateSelected));
        return dailyGoals;
    }

    public void setTaskCompleted(int taskId) {
        calendarModel.markTaskCompleted(taskId);
    }

}