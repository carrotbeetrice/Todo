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
    private MutableLiveData<List<Task>> mGoals;
    protected Home homeModel;
    private MutableLiveData<Integer> weeklyProgress;

    public HomeViewModel() {
//        mText = new MutableLiveData<>();
//        mText.setValue("Weekly Progress");

        // TODO: Implement class to programmatically determine weekly progress
        weeklyProgress = new MutableLiveData<>();
        weeklyProgress.setValue(75);

    }

    public void setContext(Context context) {
        homeModel = new Home(context);
    }

//    public LiveData<String> getText() {
//        return mText;
//    }

    public LiveData<Integer> getWeeklyProgress() {
        return weeklyProgress;
    }

    public LiveData<List<Task>> getGoals() throws IOException {
        mGoals = new MutableLiveData<>();
        mGoals.setValue(homeModel.getGoals());
        return mGoals;
    }
}