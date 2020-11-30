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

public class AddTaskModel {
    protected static final String TAG = "AddTaskModel";
    private DatabaseHelper dbHelper;
    private final Context context;
    private List<String> courses;
    // private List<Reminders> reminders;

    public AddTaskModel(Context context) {
        courses = new ArrayList<>();
        // reminders = new ArrayList<>();
        this.context = context;
        dbHelper = new DatabaseHelper(context);
    }

    public List<String> getCourses() {
        return courses;
    }

//    public List<Reminders> getReminders() {
//        return reminders;
//    }

    public void getDropDownData() {
        try {
            dbHelper.createDataBase();
            SQLiteDatabase db = dbHelper.getReadableDatabase();

            // Hopefully this works
            queryCourses(db);
            // queryReminders(db);
            db.close();
        } catch (IOException ex) {
            Log.e(TAG, "getDropDownData >>" + ex.toString());
        }
    }

    private void queryCourses(SQLiteDatabase db) {
        String query = "select c.CourseName from UserCategories uc inner join Courses c on uc.CourseId = c.CourseId;";

        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {
            String module = cursor.getString(0);
            courses.add(module);
        }

        cursor.close();

    }

    public boolean insertTaskSuccess(Task task){
        try {
            dbHelper.createDataBase();
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            // Hi profs: yes I know that performing INSERT operations like this is terrible practice.
            // I apologise for hurting your eyes with this.
            String updateTasksQuery = "insert into Tasks (CategoryId) " +
                    "select uc.CategoryId " +
                    "from UserCategories uc " +
                    "inner join Courses c on uc.CourseId = c.CourseId " +
                    "where c.CourseName = \"" + task.module + "\";";

            db.execSQL(updateTasksQuery);

            String getPKofLastInserted = "select max(TaskId) from Tasks;";

            Cursor pkCursor = db.rawQuery(getPKofLastInserted, null);
            pkCursor.moveToFirst();

            int primaryKey = pkCursor.getInt(0);

            pkCursor.close();

            // Insert into TaskDetails
            insertIntoTaskDetails(primaryKey, task, db);

            // Insert into TaskCompletion
            insertIntoTaskCompletion(primaryKey, db);

            return true;
        } catch (IOException ex) {
            return false;
        } catch (Exception ex) {
            return false;
        }

    }

    private void insertIntoTaskDetails(int primaryKey, Task task, SQLiteDatabase db) {
        ContentValues cv = new ContentValues();

        cv.put("TaskId", primaryKey);
        cv.put("TaskName", task.taskName);
        cv.put("TaskDescription", task.description);
        cv.put("DueDate", task.dueDate);
        cv.put("DueTime", task.dueTime);
        cv.put("ImportanceId",task.importance);

        db.insert("TaskDetails", null, cv);
    }

    private void insertIntoTaskCompletion(int primaryKey, SQLiteDatabase db) {
        ContentValues cv = new ContentValues();

        cv.put("TaskId", primaryKey);
        cv.put("IsCompleted", 0);
        cv.put("CompletionDate", "");
        cv.put("CompletionTime", "");

        db.insert("TaskCompletion", null, cv);
    }

//    private void queryReminders(SQLiteDatabase db) {
//        String query = "SELECT ReminderId, ReminderDescription FROM Reminders;";
//
//        Cursor cursor = db.rawQuery(query, null);
//        cursor.moveToFirst();
//
//        while (!cursor.isAfterLast()) {
//            int reminderId = cursor.getInt(0);
//            String reminderDescription = cursor.getString(1);
//            reminders.add(new Reminders(reminderId, reminderDescription));
//            cursor.moveToNext();
//        }
//
//    }
}
