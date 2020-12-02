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

public class Calendar {
    protected static final String TAG = "calendarx.x";
    private DatabaseHelper dbHelper;
    private final Context context;

    public Calendar (Context context){
        this.context = context;
        dbHelper = new DatabaseHelper(context);
    }

    public List<Task> getGoals(String dateSelected) throws IOException{
        List<Task> dairyTask = new ArrayList<>();
        try{
            String query = "select" +
                    "  td.TaskName," +
                    "  i.ImportanceLevel," +
                    "  c.CourseName," +
                    "  td.DueTime" +
                    "  from Tasks t" +
                    "  inner join UserCategories uc on t.CategoryId = uc.CategoryId" +
                    "  inner join Courses c on uc.CourseId = c.CourseId" +
                    "  inner join TaskDetails td  on td.TaskId = t.TaskId" +
                    "  inner join TaskCompletion tc on t.TaskId = tc.TaskId" +
                    "  inner join Importance i on td.ImportanceId = i.ImportanceId" +
                    "  where td.DueDate = \"" + dateSelected + "\";";

            dbHelper.createDataBase();
            SQLiteDatabase db = dbHelper.getReadableDatabase();

            Cursor cursor = db.rawQuery(query, null);
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                String taskName = cursor.getString(0);
                int importanceLevel = cursor.getInt(1);
                String modulesName = cursor.getString(2);
                String dueTime = cursor.getString(3);
                dairyTask.add(new Task(taskName , modulesName , null , dueTime , importanceLevel));
                cursor.moveToNext();
            }
        } catch (IOException ex) {
            Log.e(TAG, "getGoals >>" + ex.toString());
        } catch (Exception ex) {
            Log.e(TAG, "getGoals >>" + ex.toString());
        }
        return dairyTask;
    }


}
