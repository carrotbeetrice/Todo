package com.example.todo.ui.getstarted;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.todo.models.User;

public class SetUpViewModel extends ViewModel {

    protected User userModel;

    public void setContext(Context context) {
        userModel = new User(context);
    }

    public boolean registerUser(String studentId, String userName) {
        return userModel.addUser(userName, studentId);
    }

}
