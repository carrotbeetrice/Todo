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


    public boolean deleteModule(int categoryId) {
        try {

            String deleteFromTaskCompletionQuery = "delete from TaskCompletion where TaskId in (" +
                    "select TaskId from Tasks where CategoryId = " + categoryId + ")";
            String deleteFromTaskDetailsQuery = "delete from TaskDetails where TaskId in (" +
                    "select TaskId from Tasks where CategoryId = " +  categoryId + ")";
            String deleteFromTasksQuery = "delete from Tasks where CategoryId = " + categoryId;
            String deleteFromModules = "delete from UserCategories where CategoryId =" + categoryId;

            dbHelper.createDataBase();
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            db.execSQL(deleteFromTaskCompletionQuery);
            db.execSQL(deleteFromTaskDetailsQuery);
            db.execSQL(deleteFromTasksQuery);
            db.execSQL(deleteFromModules);
            db.close();

            return true;
        } catch (IOException ex) {
            return false;
        }
    }

}






