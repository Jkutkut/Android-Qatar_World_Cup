package com.jkutkut.qatar_wold_cup;

import androidx.constraintlayout.helper.widget.Flow;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.jkutkut.custom.CustomActivity;
import com.jkutkut.custom.CustomButton;

public class CountrySelectionActivity extends CustomActivity implements View.OnClickListener {

    // TODO Change mode dark to light not working
    public static final String COUNTRY_KEY = "country";

    // ********* UI Components *********
    ConstraintLayout constraintLayoutActivity;
    private Flow flowCountries;
    private AutoCompleteTextView autocTxtViewCountry;
    private CustomButton btnSave;
    private CustomButton btnCancel;

    // ********* Activity Result *********
    private String teamSide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_selection);

        teamSide = getIntent().getStringExtra(AddResultActivity.TEAM_SIDE);

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
        String[] countries = getCountries();
        int[] btnIds = new int[countries.length];
        for (int i = 0; i < countries.length; i++) {
            // TODO fix style of btns
//            btnCountry = new CustomButton(this, null, R.style.btn_table_element);
            btnCountry = new CustomButton(this);
//            btnCountry.setLayoutParams(new ViewGroup.LayoutParams(
//                    ViewGroup.LayoutParams.WRAP_CONTENT,
//                    ViewGroup.LayoutParams.WRAP_CONTENT
//            ));
            btnCountry.setId(View.generateViewId());
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

        Intent intent = new Intent();
        intent.putExtra(COUNTRY_KEY, autocTxtViewCountry.getText().toString().trim());
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
        for (String team : getCountries()) {
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

    private String[] getCountries() {
        return getResources().getStringArray(R.array.teams);
    }
}