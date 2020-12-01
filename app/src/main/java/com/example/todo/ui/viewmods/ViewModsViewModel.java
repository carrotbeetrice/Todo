package com.example.todo.ui.viewmods;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.todo.models.Home;
import com.example.todo.models.ModsModel;
import com.example.todo.models.Task;

import java.io.IOException;
import java.util.List;

public class ViewModsViewModel extends ViewModel {

//    private MutableLiveData<String> mText;
private MutableLiveData<List<Task>> mMods;
    protected ModsModel modsModel;

    public ViewModsViewModel() {

    }


    public void setContext(Context context) {
        modsModel = new ModsModel(context);
    }


    public LiveData<List<Task>> getMods() throws IOException {
        mMods = new MutableLiveData<>();
        mMods.setValue(modsModel.getMods());
        return mMods;

    }
}