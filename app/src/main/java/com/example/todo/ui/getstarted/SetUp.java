package com.example.todo.ui.getstarted;

import android.content.Intent;
import android.opengl.GLDebugHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.todo.R;
import com.example.todo.dbhelper.DatabaseHelper;
import com.example.todo.ui.main.MainActivity;
import com.example.todo.ui.splash.SplashScreen;

public class SetUp extends AppCompatActivity {

    EditText name, studentID;
    Button setUpButton, setUpBackButton;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        name = (EditText) findViewById(R.id.get_user_name);
        studentID = (EditText) findViewById(R.id.get_user_student_id);
        setUpButton = (Button) findViewById(R.id.setup_button);
        setUpBackButton = (Button) findViewById(R.id.setup_back_button);
        db = new DatabaseHelper(this);

        setUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent setUpIntent = new Intent(getApplicationContext(), SplashScreen.class);
                startActivity(setUpIntent);
            }
        });

//        setUpButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String userName = name.getText().toString();
//                String userStudentID = studentID.getText().toString();
//
//                if (userName.equals("") || userStudentID.equals("")) {
//                Toast.makeText(SetUp.this, "Please enter all the fields.", Toast.LENGTH_SHORT).show();
//            }
//
//                else {
//                // Check db if user already exists or not
//                Boolean checkUserName = db.checkName(userName);
//                // If user does not exist, add to db
//                if (checkUserName == false) {
//                    Boolean insert = DatabaseHelper.insertData(userName, userStudentID);
//                    // If db has been written
//                    if (insert == true) {
//                        Toast.makeText(SetUp.this, "Registered successfully!", Toast.LENGTH_SHORT).show();
//                        Intent setUpIntent = new Intent(getApplicationContext(), MainActivity.class);
//                        startActivity(setUpIntent);
//                    }
//
//                    else {
//                        Toast.makeText(SetUp.this, "Registration failed", Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//                else {
//                    Toast.makeText(SetUp.this, "User already exists. Please sign in.", Toast.LENGTH_SHORT).show();
//                }
//            }
//        }
//    });

        setUpBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent setUpBackIntent = new Intent(getApplicationContext(), GetStarted.class);
                startActivity(setUpBackIntent);
            }
        });
    }
}
