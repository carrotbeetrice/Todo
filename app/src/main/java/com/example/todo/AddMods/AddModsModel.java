package com.example.todo.AddMods;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.todo.dbhelper.DatabaseHelper;
import com.example.todo.models.Module;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddModsModel {
    protected static final String TAG = "AddTaskModel";
    private DatabaseHelper dbHelper;
    private final Context context;
    private List<String> mods;
    private List<Module> modules;

    public AddModsModel(Context context) {
        mods = new ArrayList<>();
        this.context = context;
        dbHelper = new DatabaseHelper(context);
    }


    public void getDropDownData() {
        try {
            dbHelper.createDataBase();
            SQLiteDatabase db = dbHelper.getReadableDatabase();

            // Hopefully this works
            queryMods(db);
            // queryReminders(db);

            db.close();
        } catch (IOException ex) {
            Log.e(TAG, "getDropDownData >>" + ex.toString());
        }
    }

    private void queryMods(SQLiteDatabase db) {
        String query = "select c.CourseId, c.CourseCode, c.CourseName from Courses c";

        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {
            String module = cursor.getString(2);
            mods.add(module);

        }

        cursor.close();

    }



    public List<String> getMods() {
        return mods;
    }


    public List<Module> getModules(){return modules;}


    public void insertModules(String moduleName){
        try {
            dbHelper.createDataBase();
            SQLiteDatabase db = dbHelper.getWritableDatabase();
//                ContentValues cv = new ContentValues();
//
//                cv.put("CourseId", module.courseID);
//                cv.put("CategoryId", module.categoryId);
//
                String insert = "insert into UserCategories (CourseId) select CourseId from Courses where CourseName = \"" + moduleName + "\";";
                db.execSQL(insert);
            db.close();

            } catch (IOException e) {
            e.printStackTrace();
        }




        }


    }

//    public Module queryModules(String moduleName, SQLiteDatabase db){
//        Module module =  new Module();
//
////
//
//
//
//        String query = "SELECT c.CourseId, c.CourseCode, c.CourseName from Courses c where c.CourseName = \"" + moduleName + "\";";;
//
//        Cursor cursor = db.rawQuery(query, null);
//        cursor.moveToFirst();
//        while (cursor.moveToNext()) {
//            String courseID = cursor.getString(0);
//            String courseCode = cursor.getString(1);
//            String courseName = cursor.getString(2);
//            module = new Module( courseID,courseCode,courseName);
//            modules.add(module);
//
//        }
//
//        cursor.close();
//
//
//
//        return module;
//
//
//
//    }
//
//







//
//}
