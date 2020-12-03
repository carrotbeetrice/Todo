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
    private DatabaseHelper dbHelper;
    private final Context context;

    public Home(Context context) {
        // goals = new ArrayList<>();
        this.context = context;
        dbHelper = new DatabaseHelper(context);
    }

    public int getWeeklyProgress() {
        try {
            dbHelper.createDataBase();
            SQLiteDatabase db = dbHelper.getReadableDatabase();

            String getStartEndOfWeekQuery = "select StartDate, EndDate from AcademicWeeks where StartDate < date('now') and EndDate > date('now');";

            String weekStartDate, weekEndDate;

            Cursor startEndCursor = db.rawQuery(getStartEndOfWeekQuery, null);
            startEndCursor.moveToFirst();

            weekStartDate = startEndCursor.getString(0);
            weekEndDate = startEndCursor.getString(1);

            startEndCursor.close();

            String getWeeklyTotalTasksQuery = "select count(*) from TaskDetails where DueDate >= '" +
                    weekStartDate + "' and DueDate <= '" + weekEndDate + "';";

            Cursor weeklyTasksCursor = db.rawQuery(getWeeklyTotalTasksQuery, null);
            weeklyTasksCursor.moveToFirst();

            int totalWeeklyTasks = weeklyTasksCursor.getInt(0);

            weeklyTasksCursor.close();

            if (totalWeeklyTasks == 0) {
                return 0;
            }

            String getCompletedTasksTotalQuery = "select count(*) from TaskDetails td " +
                    "inner join TaskCompletion tc on td.TaskId = tc.TaskId " +
                    "where DueDate >= '" + weekStartDate + "' and DueDate <= '" + weekEndDate +  "' and IsCompleted = 1;";

            Cursor completedWeeklyTasksCursor = db.rawQuery(getCompletedTasksTotalQuery, null);
            completedWeeklyTasksCursor.moveToFirst();

            int completedWeeklyTasks = completedWeeklyTasksCursor.getInt(0);

            int weeklyProgressPercentage = (100 * completedWeeklyTasks) / totalWeeklyTasks;

            return weeklyProgressPercentage;

        } catch (IOException ex) {
            Log.e(TAG, "getWeeklyProgress >>" + ex.toString());
            return 0;
        }
    }

}