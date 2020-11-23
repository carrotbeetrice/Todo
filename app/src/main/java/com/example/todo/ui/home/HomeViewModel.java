package com.example.todo.ui.home;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.todo.models.Home;
import com.example.todo.models.Task;

import java.io.IOException;
import java.util.List;

public class HomeViewModel extends ViewModel {

//    private MutableLiveData<String> mText;
    private MutableLiveData<List<Task>> mGoals;
    private Home homeModel;

    public HomeViewModel(Context context) throws IOException {
//        mText = new MutableLiveData<>();
//        mText.setValue("This is home fragment");
       // homeModel = new Home(context);
        mGoals.setValue(homeModel.getGoals());
    }

//    public LiveData<String> getText() {
//        return mText;
//    }

    public LiveData<List<Task>> getGoals() {
        return mGoals;
    }
}