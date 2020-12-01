package com.example.todo.models;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.todo.dbhelper.DatabaseHelper;

import java.io.IOException;

public class User {
    protected static final String TAG = "User";
    private DatabaseHelper dbHelper;
    private final Context context;

    public User(Context context) {
        this.context = context;
        dbHelper = new DatabaseHelper(context);
    }

    public String getUserName() {
        String userName = "";

        try {
            String query = "Select UserName from User;";

            dbHelper.createDataBase();
            SQLiteDatabase db = dbHelper.getReadableDatabase();

            Cursor cursor = db.rawQuery(query, null);

            while (cursor.moveToNext()) {
                userName = cursor.getString(0);
            }

            cursor.close();
            db.close();

        } catch (IOException ex) {
            Log.e(TAG, "getUserName >>" + ex.toString());
        } finally {

            return userName;
        }

    }

}
