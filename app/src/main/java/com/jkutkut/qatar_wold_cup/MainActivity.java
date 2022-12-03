package com.jkutkut.qatar_wold_cup;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.jkutkut.custom.CustomActivity;
import com.jkutkut.custom.CustomButton;

public class MainActivity extends CustomActivity {

    @Override
    @SuppressLint("MissingSuperCall") // We call super.onCreate on the parent class
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_main);

        // ********* UI Components *********
        CustomButton btnAddResult = findViewById(R.id.btnAddResult);
        CustomButton btnSeeResults = findViewById(R.id.btnSeeResults);

        // ********* Set Listeners *********
        btnAddResult.setOnClickListener(v -> {
            Intent i = new Intent(getBaseContext(), AddResultActivity.class);
            startActivity(i);
        });

        btnSeeResults.setOnClickListener(v -> {
            Intent i = new Intent(getBaseContext(), SeeResultActivity.class);
            startActivity(i);
        });

        // ********* Animations *********
        btnAddResult.setClickFeedback(getColor(R.color.qatar_light));
        btnSeeResults.setClickFeedback(getColor(R.color.qatar_light));
    }

}