package com.jkutkut.qatar_wold_cup;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jkutkut.custom.CustomActivity;
import com.jkutkut.custom.CustomButton;

public class MainActivity extends CustomActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ********* UI Components *********
        CustomButton btnAddResult = findViewById(R.id.btnAddResult);
        CustomButton btnSeeResults = findViewById(R.id.btnSeeResults);
        FloatingActionButton fabMode = findViewById(R.id.fabMode);

        // ********* Set Listeners *********
        btnAddResult.setOnClickListener(v -> {
            Intent i = new Intent(getBaseContext(), AddResult.class);
            startActivity(i);
        });

        btnSeeResults.setOnClickListener(v -> {
            alert("TODO");
            // TODO
        });

        fabMode.setOnClickListener(this::toggleDarkLightMode);

        // ********* Animations *********
        btnAddResult.setClickFeedback(getColor(R.color.qatar_light));
        btnSeeResults.setClickFeedback(getColor(R.color.qatar_light));
    }

}