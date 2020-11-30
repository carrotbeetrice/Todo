package com.example.todo.utils;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidation {

    private static final String LOGCAT_TAG = "InputValidation";

    // Check if text input is null or an empty string
    public static boolean isValidTextInput(String input) {
        return !input.isEmpty() && input != null;
    }

    // Check if due date is later than current date
    public static boolean isValidDueDate(String dateString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            Date todayDate = dateFormat.parse(dateFormat.format(new Date()));
            Date setDueDate = dateFormat.parse(dateString);

            // compareResult is less than 0 if the set due date is earlier than the current date
            int compareResult = setDueDate.compareTo(todayDate);

            return compareResult >= 0;
        } catch (ParseException ex) {
            Log.e(LOGCAT_TAG, "isValidDueDate >>" + ex.getMessage() );
            return false;
        }

    }

    // Check if due date input is valid
    public static boolean isValidDueTime(String timeString) {
        String regex = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(timeString);
        return m.matches();
    }

    // Check if rating input is zero
    public static boolean isValidRatingInput(float ratingInput) {
        return ratingInput > 0;
    }

}
