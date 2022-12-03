package com.jkutkut.qatar_wold_cup;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jkutkut.custom.CustomActivity;
import com.jkutkut.custom.CustomButton;

public class SeeResultActivity extends CustomActivity {

    // ********* UI Components *********
    private TextView txtvCountry;
    private CustomButton btnSeeResults;
    private LinearLayout containerResult;

    // ********* Activity Result *********
    private String team;
    ActivityResultLauncher<Intent> teamSelectorLauncher;

    // ********* Session *********
    private static final String TEAM_KEY = "team";

    @Override
    @SuppressLint("MissingSuperCall") // We call super.onCreate on the parent class
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_see_result);

        team = null;
        teamSelectorLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    Intent i = result.getData();
                    if (i == null) return;
                    Bundle data = i.getExtras();
                    team = data.getString(CountrySelectionActivity.COUNTRY_KEY);
                    updateUIWithTeam();
                }
            }
        );

        // ********* UI Components *********
        txtvCountry = findViewById(R.id.txtvCountry);
        btnSeeResults = findViewById(R.id.btnSeeResults);
        containerResult = findViewById(R.id.containerResult);

        // ********* Set Listeners *********
        btnSeeResults.setOnClickListener(v -> handleSeeResults());

        // ********* Animations *********
        btnSeeResults.setClickFeedback(getColor(R.color.qatar_light));
    }

    private void handleSeeResults() {
        if (team == null) {
            Intent i = new Intent(this, CountrySelectionActivity.class);
            teamSelectorLauncher.launch(i);
        }
        else
            clearForm();
    }

    private void clearForm() {
        team = null;
        txtvCountry.setText(getString(R.string.selectTeam));
        btnSeeResults.setText(getString(R.string.selectTeam));
        containerResult.removeAllViews();
    }

    private void updateUIWithTeam() {
        if (team == null) return;
        txtvCountry.setText(team);
        btnSeeResults.setText(getString(R.string.clearData));
        // TODO add results
    }

    // ********* Session *********
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(TEAM_KEY, team);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        team = savedInstanceState.getString(TEAM_KEY);
        updateUIWithTeam();
    }
}