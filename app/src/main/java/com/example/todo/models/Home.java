package com.example.todo.models;

import com.example.todo.dbhelper.DatabaseHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Home {
    private List<Task> goals;
    private double weeklyProgressPercentage;
    private DatabaseHelper dbHelper;
    private final Context context;

    public Home(Context context) {
        goals = new ArrayList<>();
        this.context = context;
        dbHelper = new DatabaseHelper(context);
    }

    public List<Task> getGoals() {
        try {
            String query = "SELECT " +
                    "CourseName, td.TaskName, td.DueDate, td.DueTime " +
                    "from Tasks t " +
                    "inner join UserCategories uc on t.CategoryId = uc.CategoryId " +
                    "inner join Courses c on uc.CourseId = c.CourseId " +
                    "inner join TaskDetails td on td.TaskId = t.TaskId " +
                    "inner join TaskCompletion tc on t.TaskId = tc.TaskId " +
                    "where tc.IsCompleted = 0 and td.DueDate > date('now');";

            dbHelper.createDataBase();
            SQLiteDatabase db = dbHelper.getReadableDatabase();

            Cursor cursor = db.rawQuery(query, null);
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                String name = cursor.getString(0);
                String description = cursor.getString(1);
                String module = cursor.getString(2);
                String dueDate = cursor.getString(3);
                String dueTime = cursor.getString(4);
                int importance = cursor.getInt(5);
                goals.add(new Task(name, description, module, dueDate, dueTime, importance));
            }


        } catch (IOException ex) {
            Log.e(TAG, "getGoals >>" + ex.toString());
        }
        return goals;
    }
}
