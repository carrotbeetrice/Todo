package com.example.todo.ui.addmods;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.todo.R;

import java.util.ArrayList;
import java.util.List;

public class AddModsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private AddModsViewModel addModsViewModel;
    private Spinner moduleSpinner;
    private List<String> moduleList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmod);
        addModsViewModel = ViewModelProviders.of(this).get(AddModsViewModel.class);
        addModsViewModel.setAddModsModel(AddModsActivity.this);
        addModsViewModel.getMods().observe(this, new Observer<List<String>>() {


            @Override
            public void onChanged(List<String> strings) {
                moduleList = strings;
                moduleSpinner = findViewById(R.id.spinner);

                // Create adapter for module spinner
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddModsActivity.this, android.R.layout.simple_spinner_item, strings);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                moduleSpinner.setAdapter(adapter);

                // Spinner click listener
                moduleSpinner.setOnItemSelectedListener(AddModsActivity.this);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }



    ;
}
