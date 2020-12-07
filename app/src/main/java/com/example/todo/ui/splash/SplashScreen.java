package com.example.todo.ui.splash;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.todo.ui.getstarted.GetStarted;
import com.example.todo.ui.main.MainActivity;
import com.example.todo.R;
import java.util.ArrayList;
import java.util.List;

public class SplashScreen extends AppCompatActivity {

    private SplashScreenViewModel splashScreenViewModel;
    private static int SPLASH_TIME_OUT = 3500; // Display splash screen for 3.5 seconds
    TextView quoteField, attributionField, welcomeLabel, userName, quoteLabel; // Widgets

    Boolean firstTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        splashScreenViewModel = ViewModelProviders.of(this).get(SplashScreenViewModel.class);

        setWidgetReferences();

        splashScreenViewModel.setContext(this);

        splashScreenViewModel.getmUserName().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s.isEmpty()) {
                    firstTime = true;
                    welcomeLabel.setText(R.string.welcome);
                } else {
                    firstTime = false;
                    userName.setText(s);
                    welcomeLabel.setText(R.string.welcome_back);
                }

                new GetDailyQuote().execute();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Class destination = firstTime ? GetStarted.class : MainActivity.class;
                        startActivity(new Intent(SplashScreen.this, destination));
                        finish();
                    }
                }, SPLASH_TIME_OUT);

            }
        });

    }

    private void setWidgetReferences() {
        quoteField = findViewById(R.id.quote_field);
        attributionField = findViewById(R.id.attribution_field);
        welcomeLabel = findViewById(R.id.welcome);
        userName = findViewById(R.id.user);
    }

    class GetDailyQuote extends AsyncTask<Void, Void, Quote> {

        @Override
        protected Quote doInBackground(Void... voids) {
            return new Quote();
        }

        @Override
        protected void onPostExecute(Quote quote) {
            super.onPostExecute(quote);
            if (quote != null) {
                quoteField.setText(quote.getQuote());
                attributionField.setText(quote.getAttribution());
            }
        }
    }
}