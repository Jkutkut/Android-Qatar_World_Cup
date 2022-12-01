package com.jkutkut.custom;

import android.content.res.Configuration;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class CustomActivity extends AppCompatActivity {

    protected void alert(String msg) {
        Toast.makeText(
            this,
            msg,
            Toast.LENGTH_LONG
        ).show();
    }

    protected void toggleDarkLightMode(View view) {
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
