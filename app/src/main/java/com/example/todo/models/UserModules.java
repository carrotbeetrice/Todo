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


public class UserModules {
    protected static final String TAG = "Mods";
    private DatabaseHelper dbHelper;
    private final Context context;
    private List<Module> modules;

    public UserModules(Context context){
        this.context = context;
        modules = new ArrayList<>();
        dbHelper = new DatabaseHelper(context);
    }

    // TODO: Just change everything here honestly
    public List<Module> getMods() {
        try {
            String query = "Select uc.CategoryId, c.CourseCode, c.CourseName from UserCategories uc " +
                    "inner join Courses c on uc.CourseId = c.CourseId;";

            dbHelper.createDataBase();
            SQLiteDatabase db = dbHelper.getReadableDatabase();

            Cursor cursor = db.rawQuery(query, null);
            
            while (cursor.moveToNext()) {
                int categoryId = cursor.getInt(0);
                String courseCode = cursor.getString(1);
                String courseName = cursor.getString(2);
                modules.add(new Module(categoryId, courseCode, courseName));
            }

            cursor.close();
            db.close();

        } catch (IOException ex) {
            Log.e(TAG, "getMods >>" + ex.toString());
        } catch (Exception ex) {
            Log.e(TAG, "getMods >>" + ex.toString());
        } finally {
            return modules;
        }

    }


    public void update(int position){
//        SQLiteDatabase db = dbHelper.getReadableDatabase();
//        ContentValues cv = new ContentValues();
//        mods.remove(position);
//        cv.put("IsCompleted", 1);
//        db.update("TaskCompletion", cv, "TaskID = ?",null);
    }





}






