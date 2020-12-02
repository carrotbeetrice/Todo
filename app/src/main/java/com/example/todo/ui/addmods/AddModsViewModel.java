package com.example.todo.ui.addmods;


import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.todo.models.AddModsModel;

import java.util.List;


public class AddModsViewModel extends ViewModel {
    private MutableLiveData<List<String>> mMods;
    private AddModsModel addModsModel;



    public void setAddModsModel(Context context) {
        addModsModel = new AddModsModel(context);
        addModsModel.getDropDownData();
    }

    public LiveData<List<String>> getMods() {
        mMods = new MutableLiveData<>();
        mMods.setValue(addModsModel.getMods());
        return mMods;
    }











}








