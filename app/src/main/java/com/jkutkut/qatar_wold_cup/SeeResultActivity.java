package com.jkutkut.qatar_wold_cup;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SeeResultActivity extends AppCompatActivity {

    // ********* UI Components *********
    private TextView txtvCountry;
    private Button btnSeeResults;
    private LinearLayout containerResult;

    // ********* Activity Result *********
    private String team;
    ActivityResultLauncher<Intent> teamSelectorLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_result);

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
}