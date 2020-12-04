package com.example.todo.ui.splash;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.todo.models.User;

public class SplashScreenViewModel extends ViewModel {

    protected User userModel;
    private MutableLiveData<String> mUserName;

    public void setContext(Context context) {
        userModel = new User(context);
    }

    public MutableLiveData<String> getmUserName() {
        mUserName = new MutableLiveData<>();
        mUserName.setValue(userModel.getUserName());
        return mUserName;
    }
}
