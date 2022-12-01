package com.jkutkut.qatar_wold_cup;

import androidx.appcompat.widget.AppCompatButton;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jkutkut.custom.CustomActivity;

public class MainActivity extends CustomActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ********* UI Components *********
        AppCompatButton btnAddResult = findViewById(R.id.btnAddResult);
        AppCompatButton btnSeeResults = findViewById(R.id.btnSeeResults);
        FloatingActionButton fabMode = findViewById(R.id.fabMode);

        // ********* Set Listeners *********
        btnAddResult.setOnClickListener(v -> {
            alert("TODO");
            // TODO
        });

        btnSeeResults.setOnClickListener(v -> {
            alert("TODO");
            // TODO
        });

        fabMode.setOnClickListener(this::toggleDarkLightMode);
    }

}