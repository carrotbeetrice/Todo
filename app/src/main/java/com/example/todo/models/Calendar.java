package com.example.todo.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.todo.dbhelper.DatabaseHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Calendar {
    protected static final String TAG = "Calendar";
    private DatabaseHelper dbHelper;

    public Calendar (Context context){
        dbHelper = new DatabaseHelper(context);
    }

    public List<Task> getGoals(String dateSelected) {
        List<Task> dailyTasks = new ArrayList<>();
        try{
            String query = "select" +
                    "  t.TaskId," +
                    "  td.TaskName," +
                    "  td.TaskDescription," +
                    "  i.ImportanceLevel," +
                    "  c.CourseName," +
                    "  td.DueTime" +
                    "  from Tasks t" +
                    "  inner join UserCategories uc on t.CategoryId = uc.CategoryId" +
                    "  inner join Courses c on uc.CourseId = c.CourseId" +
                    "  inner join TaskDetails td  on td.TaskId = t.TaskId" +
                    "  inner join TaskCompletion tc on t.TaskId = tc.TaskId" +
                    "  inner join Importance i on td.ImportanceId = i.ImportanceId" +
                    "  where td.DueDate = \"" + dateSelected + "\" and tc.IsCompleted = 0;";

            dbHelper.createDataBase();
            SQLiteDatabase db = dbHelper.getReadableDatabase();

            Cursor cursor = db.rawQuery(query, null);
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                int taskId = cursor.getInt(0);
                String taskName = cursor.getString(1);
                String taskDescription = cursor.getString(2);
                int importanceLevel = cursor.getInt(3);
                String modulesName = cursor.getString(4);
                String dueTime = cursor.getString(5);
                dailyTasks.add(new Task(taskId, modulesName, taskName, taskDescription, dueTime, importanceLevel));
                cursor.moveToNext();
            }
        } catch (IOException ex) {
            Log.e(TAG, "getGoals >>" + ex.toString());
        } catch (Exception ex) {
            Log.e(TAG, "getGoals >>" + ex.toString());
        }
        return dailyTasks;
    }

    public void markTaskCompleted(int taskId) {
        try {
            dbHelper.createDataBase();
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            // Get current dates and time
            SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm");

            String currentDate = dateFormatter.format(new Date());
            String currentTime = timeFormatter.format(new Date(System.currentTimeMillis()));

            ContentValues cv = new ContentValues();
            cv.put("IsCompleted", 1);
            cv.put("CompletionDate", currentDate);
            cv.put("CompletionTime", currentTime);

            db.update("TaskCompletion", cv, "TaskId=" + taskId, null);
            db.close();

        } catch (IOException ex) {

        } catch (Exception ex) {

        }
    }


}
