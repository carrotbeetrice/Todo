package com.example.todo.ui.viewmods;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ViewModsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ViewModsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is viewmods fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}