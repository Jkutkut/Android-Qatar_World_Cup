package com.jkutkut.qatar_wold_cup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.helper.widget.Flow;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.jkutkut.custom.CustomActivity;
import com.jkutkut.custom.CustomButton;

import java.util.Arrays;

public class CountrySelectionActivity extends CustomActivity implements View.OnClickListener {
    // TODO add scroll to view in small screens
    public static final String COUNTRY_KEY = "country";

    // ********* UI Components *********
    ConstraintLayout constraintLayoutActivity;
    private Flow flowCountries;
    private AutoCompleteTextView autocTxtViewCountry;
    private CustomButton btnSave;
    private CustomButton btnCancel;

    // ********* Activity Result *********
    private String[] countries;
    private int teamSide;
    private String oponent;

    // ********* Session *********
    private static final String COUNTRIES_KEY = "countries";
    private static final String TEAM_SIDE_KEY = "teamSide";
    private static final String OPONENT_KEY = "oponent";
//    private static final String COUNTRY_KEY = "country";

    @Override
    @SuppressLint("MissingSuperCall") // We call super.onCreate on the parent class
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_country_selection);

        countries = getResources().getStringArray(R.array.teams);
        teamSide = getIntent().getIntExtra(AddResultActivity.TEAM_SIDE, -1);
        oponent = getIntent().getStringExtra(AddResultActivity.OPONENT);

        if (oponent != null) {
            // Override countries without opponent
            String[] countriesWithoutOponent = new String[countries.length - 1];
            int index = 0;
            for (String country : countries)
                if (!country.equals(oponent))
                    countriesWithoutOponent[index++] = country;
            countries = countriesWithoutOponent;
        }

        // Sort countries
        Arrays.sort(countries);

        // ********* UI Components *********
        constraintLayoutActivity = findViewById(R.id.constraintLayoutActivity);
        flowCountries = findViewById(R.id.flowCountries);
        autocTxtViewCountry = findViewById(R.id.autocTxtViewCountry);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);

        // ********* Init Components *********
        initFlowCountries();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
            this,
            android.R.layout.simple_list_item_1,
            getResources().getStringArray(R.array.teams)
        );
        autocTxtViewCountry.setAdapter(adapter);
        btnSave.setOnClickListener(v -> handleSave());
        btnCancel.setOnClickListener(v -> {
            setResult(RESULT_CANCELED);
            finish();
        });
    }

    private void initFlowCountries() {
        CustomButton btnCountry;
        int[] btnIds = new int[countries.length];
        for (int i = 0; i < countries.length; i++) {
            btnCountry = new CustomButton(this);
            btnCountry.setBackgroundResource(android.R.color.transparent);
            btnCountry.setId(View.generateViewId());
            btnCountry.setAllCaps(false);
            btnCountry.setText(countries[i]);
            btnCountry.setOnClickListener(this);
            constraintLayoutActivity.addView(btnCountry);
            btnIds[i] = btnCountry.getId();
        }
        flowCountries.setReferencedIds(btnIds);
    }

    private void handleSave() {
        if (!validCountrySelected()) {
            alert(getString(R.string.country_selection_error));
            return;
        }

        String country = getSelectedCountry();

        if (oponent != null && oponent.equals(country)) {
            alert(getString(R.string.country_selection_error_same_country));
            return;
        }

        Intent intent = new Intent();
        intent.putExtra(COUNTRY_KEY, country);
        intent.putExtra(AddResultActivity.TEAM_SIDE, teamSide);
        setResult(RESULT_OK, intent);
        finish();
    }

    // ********* IMPLEMENTS *********
    @Override
    public void onClick(View v) {
        String country = ((Button) v).getText().toString();
        autocTxtViewCountry.setText(country);
    }

    // ********* UTILS *********
    private boolean validCountrySelected() {
        String country = getSelectedCountry();
        for (String team : countries) {
            if (team.equals(country)) {
                return true;
            }
        }
        return false;
    }

    // ********* GETTERS *********
    private String getSelectedCountry() {
        return autocTxtViewCountry.getText().toString().trim();
    }

    // ********* SESSION *********
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArray(COUNTRIES_KEY, countries);
        outState.putInt(TEAM_SIDE_KEY, teamSide);
        outState.putString(OPONENT_KEY, oponent);
        outState.putString(COUNTRY_KEY, getSelectedCountry());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        countries = savedInstanceState.getStringArray(COUNTRIES_KEY);
        teamSide = savedInstanceState.getInt(TEAM_SIDE_KEY);
        oponent = savedInstanceState.getString(OPONENT_KEY);
        autocTxtViewCountry.setText(savedInstanceState.getString(COUNTRY_KEY));
    }
}