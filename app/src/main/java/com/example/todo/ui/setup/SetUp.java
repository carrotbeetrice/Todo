package com.example.todo.ui.setup;

import android.content.Intent;
import android.opengl.GLDebugHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.todo.R;
import com.example.todo.ui.getstarted.GetStarted;
import com.example.todo.ui.main.MainActivity;
import com.example.todo.ui.splash.SplashScreen;

public class SetUp extends AppCompatActivity {

    EditText name, studentID;
    Button setUpButton, setUpBackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        name = (EditText) findViewById(R.id.get_user_name);
        studentID = (EditText) findViewById(R.id.get_user_student_id);
        setUpButton = (Button) findViewById(R.id.setup_button);
        setUpBackButton = (Button) findViewById(R.id.setup_back_button);

        setUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent setUpIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(setUpIntent);
            }
        });

        setUpBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent setUpBackIntent = new Intent(getApplicationContext(), GetStarted.class);
                startActivity(setUpBackIntent);
            }
        });
    }
}