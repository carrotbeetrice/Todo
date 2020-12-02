package com.example.todo.models;

public class Module {
    public int categoryId;
    public String courseCode;
    public String courseName;
    public String courseID;

    public Module(){
        this.categoryId = 0;
        this.courseID = null;
        this.courseCode =  null;
        this.courseName = null;
    }

    public Module(int categoryId, String courseCode, String courseName) {
        this.categoryId = categoryId;
        this.courseCode = courseCode;
        this.courseName = courseName;
    }

    public Module( String courseID, String courseCode, String courseName) {
        this.courseID = courseID;
        this.courseCode = courseCode;
        this.courseName = courseName;


    }
}
