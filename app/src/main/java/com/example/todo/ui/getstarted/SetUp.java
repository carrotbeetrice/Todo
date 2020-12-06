package com.example.todo.ui.getstarted;

import android.content.Intent;
import android.opengl.GLDebugHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.todo.R;
import com.example.todo.ui.main.MainActivity;
import com.example.todo.utils.InputValidation;

public class SetUp extends AppCompatActivity {

    SetUpViewModel setUpViewModel;
    EditText editTextStudentName, editTextStudentId;
    Button setUpButton, setUpBackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        // Set view model
        setUpViewModel = ViewModelProviders.of(this).get(SetUpViewModel.class);
        setUpViewModel.setContext(this);

        setWidgetReferences();
        setEventListeners();

    }

    private void setWidgetReferences() {
        editTextStudentName = findViewById(R.id.get_user_name);
        editTextStudentId = findViewById(R.id.get_user_student_id);
        setUpButton = findViewById(R.id.setup_button);
        setUpBackButton = findViewById(R.id.setup_back_button);
    }

    private void setEventListeners() {
        // Set up button listener
        setUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputName = editTextStudentName.getText().toString();
                String inputId = editTextStudentId.getText().toString();

                // Perform input validation
                if (!InputValidation.isValidTextInput(inputName)) {
                    failedValidationToast(R.string.invalid_name_toast);
                } else if (!InputValidation.isValidTextInput(inputId)) {
                    failedValidationToast(R.string.invalid_id_toast);
                } else {

                    if (setUpViewModel.registerUser(inputId, inputName)) {
                        Toast.makeText(SetUp.this, "Set up success!", Toast.LENGTH_SHORT).show();
                        Intent setUpIntent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(setUpIntent);
                    } else {
                        Toast.makeText(SetUp.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });

        // Back button listener
        setUpBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent setUpBackIntent = new Intent(getApplicationContext(), GetStarted.class);
                startActivity(setUpBackIntent);
            }
        });
    }

    private void failedValidationToast(int message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

}
