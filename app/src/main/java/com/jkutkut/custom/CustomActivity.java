package com.jkutkut.custom;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jkutkut.qatar_wold_cup.R;

public class CustomActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState, final int layout) {
        super.onCreate(savedInstanceState);
        setContentView(layout);
        FloatingActionButton fabMode = findViewById(R.id.fabMode);
        fabMode.setOnClickListener(this::toggleDarkLightMode);
    }

    protected void alert(String msg) {
        Toast.makeText(
            this,
            msg,
            Toast.LENGTH_LONG
        ).show();
    }

    protected void toggleDarkLightMode(View ignoredView) {
        if (darkMode())
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        else
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
    }

    protected boolean darkMode() {
        int nightModeFlags = this.getResources().getConfiguration().uiMode &
                Configuration.UI_MODE_NIGHT_MASK;
        return nightModeFlags == Configuration.UI_MODE_NIGHT_YES;
    }
}
