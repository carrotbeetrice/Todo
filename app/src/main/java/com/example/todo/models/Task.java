package com.example.todo.models;

import java.sql.Time;
import java.util.Date;

public class Task {
    public String name;
    public String description;
    public String module;
    public Date dueDate;
    public Time dueTime;
    public int importance;

    public Task(String name, String description, String module, Date dueDate, Time dueTime, int importance) {
        this.name = name;
        this.description = description;
        this.module = module;
        this.dueDate = dueDate;
        this.dueTime = dueTime;
        this.importance = importance;
    }

}
