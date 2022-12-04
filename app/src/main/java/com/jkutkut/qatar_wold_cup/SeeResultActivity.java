package com.jkutkut.qatar_wold_cup;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jkutkut.custom.CustomActivity;
import com.jkutkut.custom.CustomAnimations;
import com.jkutkut.custom.CustomButton;
import com.jkutkut.qatar_wold_cup.data.MatchResult;
import com.jkutkut.qatar_wold_cup.data.MatchResultList;

/**
 * Class with the logic to see the results of a team.
 *
 * @author jkutkut
 */
public class SeeResultActivity extends CustomActivity {

    // ********* UI Components *********
    private TextView txtvCountry;
    private CustomButton btnSeeResults;
    private LinearLayout fragmentContainer;
    private FragmentManager fm;

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
        fragmentContainer = findViewById(R.id.fragmentContainer);
        fm = getSupportFragmentManager();

        // ********* Set Listeners *********
        btnSeeResults.setOnClickListener(v -> handleSeeResults());

        // ********* Animations *********
        btnSeeResults.setClickFeedback(getColor(R.color.qatar_light));
        CustomAnimations.setBtnClickFeedback(btnSeeResults, txtvCountry, getColor(R.color.qatar_light));
    }

    /**
     * Handles all the logic related to the button.
     * Depending if a team has been selected, it will open the team selector or clear the form.
     */
    private void handleSeeResults() {
        if (team == null) {
            Intent i = new Intent(this, CountrySelectionActivity.class);
            teamSelectorLauncher.launch(i);
        }
        else
            clearForm();
    }

    /**
     * Clear the activity form.
     */
    private void clearForm() {
        team = null;
        txtvCountry.setText(getString(R.string.selectTeam));
        btnSeeResults.setText(getString(R.string.selectTeam));
        fragmentContainer.removeAllViews();
    }

    /**
     * Updates the UI with the selected team (if any).
     */
    private void updateUIWithTeam() {
        if (team == null) return;
        txtvCountry.setText(team);
        btnSeeResults.setText(getString(R.string.clearData));

        QatarApplication app = (QatarApplication) getApplication();
        MatchResultList data = app.getResultData();
        fragmentContainer.removeAllViews();
        FragmentTransaction ft = fm.beginTransaction();
        for (MatchResult result : data.getResultsByTeam(team)) {
            ft.add(R.id.fragmentContainer, ResultFragment.newInstance(result));
        }
        // Note: Just a single transaction without ft.addToBackStash(null) is international
        ft.commit();
    }

    // ********* Session *********
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
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