package com.example.todo.models;

import java.sql.Time;
import java.util.Date;

public class Task {
    public int taskId;
    public String module;
    public String taskName;
    public String description;
    public String dueDate;
    public String dueTime;
    public int importance;
    public int cueRemindersId;
    public String taskID;

    public Task() {
    }

    // Constructor for AddTaskViewModel
    public Task(String module, String taskName, String description, String dueDate, String dueTime, int importance) {
        this.module = module;
        this.taskName = taskName;
        this.description = description;
        this.dueDate = dueDate;
        this.dueTime = dueTime;
        this.importance = importance;
    }

    // Constructor for CalendarViewModel
    public Task(int taskId, String module, String taskName, String taskDescription, String dueTime, int importance) {
        this.taskId = taskId;
        this.module = module;
        this.taskName = taskName;
        this.description = taskDescription;
        this.dueTime = dueTime;
        this.importance = importance;
    }

    // Constructor for HomeViewModel
    public Task(String name, String module, String dueDate, String dueTime, int importance) {
        this.taskName = name;
        this.module = module;
        this.dueDate = dueDate;
        this.dueTime = dueTime;
        this.importance = importance;
    }

    //Constructor for ModsModel
    public Task(String taskID, String module , String taskName , String dueDate , String dueTime){
        this.taskID = taskID;
        this.module = module;
        this.taskName = taskName;
        this.dueDate = dueDate;
        this.dueTime = dueTime;
    }

}
