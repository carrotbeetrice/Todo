package com.example.todo.models;

import java.sql.Time;
import java.util.Date;

public class Task {
    public int courseId;
    public String module;
    public String taskName;
    public String description;
    public String dueDate;
    public String dueTime;
    public int importance;
    public int cueRemindersId;

    public Task() {
    }

    public Task(String name, String module, String dueDate, String dueTime, int importance) {
        this.taskName = name;
        this.module = module;
        this.dueDate = dueDate;
        this.dueTime = dueTime;
        this.importance = importance;
    }

}
