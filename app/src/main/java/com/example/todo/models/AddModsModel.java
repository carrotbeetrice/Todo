package com.example.todo.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.todo.dbhelper.DatabaseHelper;

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
        String query = "select c.CourseId, c.CourseCode, c.CourseName from Courses c where c.CourseId not in (SELECT CourseId from UserCategories);";

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

            String insert = "insert into UserCategories (CourseId) select CourseId from Courses where CourseName = \"" + moduleName + "\";";
            db.execSQL(insert);

            db.close();

        } catch (IOException e) {
            e.printStackTrace();
        }




        }


    }

