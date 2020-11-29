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

public class Home {
    protected static final String TAG = "Home";
    private List<Task> goals;
    private double weeklyProgressPercentage;
    private DatabaseHelper dbHelper;
    private final Context context;

    public Home(Context context) {
        goals = new ArrayList<>();
        this.context = context;
        dbHelper = new DatabaseHelper(context);
    }

    public List<Task> getGoals() throws IOException {
        try {
            String query = "SELECT " +
                    "c.CourseName, td.TaskName, td.DueDate, td.DueTime, i.ImportanceLevel " +
                    "from Tasks t " +
                    "inner join UserCategories uc on t.CategoryId = uc.CategoryId " +
                    "inner join Courses c on uc.CourseId = c.CourseId " +
                    "inner join TaskDetails td on td.TaskId = t.TaskId " +
                    "inner join TaskCompletion tc on t.TaskId = tc.TaskId " +
                    "inner join Importance i on i.ImportanceId = td.ImportanceId " +
                    "where tc.IsCompleted = 0;";

            dbHelper.createDataBase();
            SQLiteDatabase db = dbHelper.getReadableDatabase();

            Cursor cursor = db.rawQuery(query, null);
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                String module = cursor.getString(0);
                String taskName = cursor.getString(1);
                String dueDate = cursor.getString(2);
                String dueTime = cursor.getString(3);
                int importance = cursor.getInt(4);
                goals.add(new Task(module, taskName, dueDate, dueTime, importance));
                cursor.moveToNext();
            }


        } catch (IOException ex) {
            Log.e(TAG, "getGoals >>" + ex.toString());
        } catch (Exception ex) {
            Log.e(TAG, "getGoals >>" + ex.toString());
        }
        return goals;
    }

}