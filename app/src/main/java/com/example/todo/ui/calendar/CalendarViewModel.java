package com.example.todo.ui.calendar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.Toast;


import com.example.todo.R;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

public class CalendarViewModel extends ViewModel {
    //MaterialCalendarView materialCalendarView = (MaterialCalendarView) findViewById(R.id.calendarView);

    private MutableLiveData<String> mText;
    CalendarView simpleCalendarView;

    public CalendarViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is calendar fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}