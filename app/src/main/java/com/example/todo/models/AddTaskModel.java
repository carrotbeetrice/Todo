package com.example.todo.models;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.todo.dbhelper.DatabaseHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddTaskModel {
    protected static final String TAG = "AddTaskModel";
    private DatabaseHelper dbHelper;
    private final Context context;
    private List<Courses> courses;
    private List<Reminders> reminders;

    public AddTaskModel(Context context) {
        courses = new ArrayList<>();
        reminders = new ArrayList<>();
        this.context = context;
        dbHelper = new DatabaseHelper(context);
    }

    public List<Courses> getCourses() {
        return courses;
    }

    public List<Reminders> getReminders() {
        return reminders;
    }

    public void getDropDownData() {
        try {
            dbHelper.createDataBase();
            SQLiteDatabase db = dbHelper.getReadableDatabase();

            // Hopefully this works
            queryCourses(db);
            queryReminders(db);
        } catch (IOException ex) {
            Log.e(TAG, "getDropDownData >>" + ex.toString());
        }
    }

    private void queryCourses(SQLiteDatabase db) {
        String query = "SELECT CourseId, CourseCode, CourseName FROM Courses;";

        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            int courseId = cursor.getInt(0);
            String courseCode = cursor.getString(1);
            String courseName = cursor.getString(2);
            courses.add(new Courses(courseId, courseCode, courseName));
            cursor.moveToNext();
        }

    }

    private void queryReminders(SQLiteDatabase db) {
        String query = "SELECT ReminderId, ReminderDescription FROM Reminders;";

        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            int reminderId = cursor.getInt(0);
            String reminderDescription = cursor.getString(1);
            reminders.add(new Reminders(reminderId, reminderDescription));
            cursor.moveToNext();
        }

    }
}
