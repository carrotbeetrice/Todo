package com.example.todo.ui.splash;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.todo.ui.main.MainActivity;
import com.example.todo.R;
import java.util.ArrayList;
import java.util.List;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3000; // Display splash screen for 3 seconds
    TextView quoteField, attributionField, welcomeLabel, userName, quoteOfTheDay; // Widgets
    SplashScreenViewModel splashScreenViewModel;
    boolean isFirstTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        setWidgetReferences();

        // Access the viewmodel
        splashScreenViewModel = ViewModelProviders.of(this).get(SplashScreenViewModel.class);
        splashScreenViewModel.setContext(this);

        splashScreenViewModel.getUserName().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s.isEmpty()) {
                    isFirstTime = true;
                    welcomeLabel.setText(R.string.welcome);
                } else {
                    userName.setText(s);
                    welcomeLabel.setText(R.string.welcome_back);
                }
            }
        });

        quoteOfTheDay.setText(R.string.quote_of_the_day);

        new GetDailyQuote().execute();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreen.this, MainActivity.class));
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

    private void setWidgetReferences() {
        quoteField = findViewById(R.id.quote_field);
        attributionField = findViewById(R.id.attribution_field);
        welcomeLabel = findViewById(R.id.welcome);
        userName = findViewById(R.id.user);
        quoteOfTheDay = findViewById(R.id.quote_of_the_day);

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