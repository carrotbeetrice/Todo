package com.example.todo.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.todo.dbhelper.DatabaseHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ModsModel {
    protected static final String TAG = "Mods";
    private DatabaseHelper dbHelper;
    private final Context context;
    private List<Task> mods;


    public ModsModel(Context context){
        this.context = context;
        mods = new ArrayList<>();
        dbHelper = new DatabaseHelper(context);
    }

    public List<Task> getMods() throws IOException{
        try {
            String query = "SELECT " +
                    "tc.TaskID, c.CourseName, td.TaskName, td.DueDate, td.DueTime  " +
                    "from Tasks t " +
                    "inner join UserCategories uc on t.CategoryId = uc.CategoryId " +
                    "inner join Courses c on uc.CourseId = c.CourseId " +
                    "inner join TaskDetails td on td.TaskId = t.TaskId " +
                    "inner join TaskCompletion tc on t.TaskId = tc.TaskId " +
                    "where tc.IsCompleted = 0 " +
                    "order by DueDate asc, DueTime asc ";

            dbHelper.createDataBase();
            SQLiteDatabase db = dbHelper.getReadableDatabase();

            Cursor cursor = db.rawQuery(query, null);
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                String tID = cursor.getString(0);
                String module = cursor.getString(1);
                String taskName = cursor.getString(2);
                String dueDate = cursor.getString(3);
                String dueTime = cursor.getString(4);
                mods.add(new Task(tID, module, taskName, dueDate, dueTime));
                cursor.moveToNext();
            }

            cursor.close();
            db.close();

        } catch (IOException ex) {
            Log.e(TAG, "getMods >>" + ex.toString());
        } catch (Exception ex) {
            Log.e(TAG, "getMods >>" + ex.toString());
        } finally {

            return mods;
        }

    }
    public void update(int position){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ContentValues cv = new ContentValues();
        mods.remove(position);
        Log.i("MODS",mods.toString());
        cv.put("IsCompleted", 1);
        db.update("TaskCompletion", cv, "TaskID = ?",null);
//

    }





}






