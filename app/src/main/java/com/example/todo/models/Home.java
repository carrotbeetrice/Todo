package com.example.todo.models;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.todo.dbhelper.DatabaseHelper;

import java.io.IOException;
import java.util.List;

public class Home {
    protected static final String TAG = "Home";
    private List<Task> goals;
    private double weeklyProgressPercentage;
    private DatabaseHelper dbHelper;
    private final Context context;

    public Home(Context context) {
        this.context = context;
        dbHelper = new DatabaseHelper(context);
    }

    public List<Task> getGoals(Context context) throws IOException {
        try {
            String query = ""; //TODO: Update query

            dbHelper.createDataBase();
            SQLiteDatabase db = dbHelper.getReadableDatabase();

            Cursor cursor = db.rawQuery(query, null);
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {

            }

            return goals;
        } catch (IOException ex) {
            Log.e(TAG, "getGoals >>" + ex.toString());
            throw ex;
        }
    }

}
