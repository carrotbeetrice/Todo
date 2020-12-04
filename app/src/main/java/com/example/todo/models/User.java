package com.example.todo.models;

import android.content.ContentValues;
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
            String query = "select UserName from User;";

            dbHelper.createDataBase();
            SQLiteDatabase db = dbHelper.getReadableDatabase();

            Cursor cursor = db.rawQuery(query, null);

            while (cursor.moveToNext()) {
                userName = cursor.getString(0);
            }

            cursor.close();
            db.close();

        } catch (IOException ex) {
            Log.e(TAG, "getUserName >>" + ex.toString());        } catch (Exception ex) {
            Log.e(TAG, "getUserName >>" + ex.toString());
        } finally {
            return userName;
        }
    }

    public boolean addUser(String userName, String studentId) {
        try {
            dbHelper.createDataBase();
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues cv = new ContentValues();

            cv.put("StudentId", studentId);
            cv.put("UserName", userName);

            db.insert("User", null, cv);
            db.close();

            return true;
        } catch (IOException ex) {
            return false;
        }
    }

}
