package com.example.todo.ui.viewmods;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.todo.models.Module;
import com.example.todo.models.UserModules;

import java.util.List;

public class ViewModsViewModel extends ViewModel {

    protected UserModules userModules;
    private MutableLiveData<List<Module>> modules;

    public ViewModsViewModel() {
    }


    public void setContext(Context context) {
        userModules = new UserModules(context);
    }

    public MutableLiveData<List<Module>> getModules() {
        modules = new MutableLiveData<>();
        modules.setValue(userModules.getMods());
        return modules;
    }

    public boolean removeModule(int moduleId) {
        return userModules.deleteModule(moduleId);
    }
}