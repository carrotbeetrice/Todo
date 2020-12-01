package com.example.todo;

import com.example.todo.utils.InputValidation;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void dateCompareCheck() {
        String inputDate = "2021-11-30";

        boolean expectedResult = true;
        boolean actualResult = InputValidation.isValidDueDate(inputDate);

        assertEquals(expectedResult, actualResult);
    }
}