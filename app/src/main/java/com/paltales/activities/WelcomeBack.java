package com.paltales.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.paltales.R;
import com.paltales.config.Preferences;

/*
    This is another starting activity of the mobile app,
    Checking if it's not the first time of the user then welcome them back
 */
public class WelcomeBack extends AppCompatActivity {
    /*
        Attributes
     */
    private Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize();
    }

    private void initialize() {
        if(getSupportActionBar()!=null)
            getSupportActionBar().hide();
        setContentView(R.layout.welcome_back);
        setStart(findViewById(R.id.start));
        handle_start(getStart());
    }

    private void handle_start(Button start) {
        start.setOnClickListener(view -> {
            Intent intent;
            if(Preferences.isRememberMe(this)) {
                intent = new Intent(this, Home.class);
            }else{
                intent = new Intent(this, LoginActivity.class);
            }
            startActivity(intent);
        });
    }

    /*
        Getters & Setters
     */
    public Button getStart() {
        return start;
    }
    public void setStart(Button start) {
        this.start = start;
    }
}