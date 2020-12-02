package com.example.todo.models;

public class Module {
    public int categoryId;
    public String courseCode;
    public String courseName;

    public Module(int categoryId, String courseCode, String courseName) {
        this.categoryId = categoryId;
        this.courseCode = courseCode;
        this.courseName = courseName;
    }

}
