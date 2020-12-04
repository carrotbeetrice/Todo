package com.example.todo.ui.home;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.todo.models.Home;
import com.example.todo.models.Task;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;

public class HomeViewModel extends ViewModel {

    //    private MutableLiveData<String> mText;
    // private MutableLiveData<List<Task>> mGoals;
    protected Home homeModel;
    private MutableLiveData<Integer> weeklyProgress;

    public HomeViewModel() {
    }

    public void setContext(Context context) {
        homeModel = new Home(context);
    }

    public LiveData<Integer> getWeeklyProgress() {
        weeklyProgress = new MutableLiveData<>();
        weeklyProgress.setValue(homeModel.getWeeklyProgress());
        return weeklyProgress;
    }

//    public LiveData<List<Task>> getGoals() {
//        mGoals = new MutableLiveData<>();
//        mGoals.setValue(homeModel.getGoals());
//        return mGoals;
//    }
}