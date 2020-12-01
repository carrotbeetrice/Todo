package com.example.todo.ui.splash;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.todo.models.User;

public class SplashScreenViewModel extends ViewModel {
    private User user;
    private MutableLiveData<String> userName;

    public SplashScreenViewModel() {
    }

    public void setContext(Context context) {
        user = new User(context);
    }

    public MutableLiveData<String> getUserName() {
        userName = new MutableLiveData<>();

        String name = user.getUserName();

        userName.setValue(name);
        return userName;
    }
}
