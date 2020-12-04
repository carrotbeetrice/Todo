package com.example.todo.ui.getstarted;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.todo.R;
import com.example.todo.ui.setup.SetUp;

public class GetStarted extends AppCompatActivity {

    Button getStartedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getstarted);
        getSupportActionBar().hide();

        getStartedButton = findViewById(R.id.getstarted_button);
        getStartedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent getStartedIntent = new Intent(getApplicationContext(), SetUp.class);
                startActivity(getStartedIntent);
            }
        });
    }
}