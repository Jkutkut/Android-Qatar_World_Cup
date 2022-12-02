package com.jkutkut.qatar_wold_cup;

import androidx.constraintlayout.helper.widget.Flow;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.jkutkut.custom.CustomActivity;
import com.jkutkut.custom.CustomButton;

public class CountrySelectionActivity extends CustomActivity implements View.OnClickListener {

    // ********* UI Components *********
    ConstraintLayout constraintLayoutActivity;
    private Flow flowCountries;
    private EditText etxtCountrySelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_selection);

        // ********* UI Components *********
        constraintLayoutActivity = findViewById(R.id.constraintLayoutActivity);
        flowCountries = findViewById(R.id.flowCountries);
        etxtCountrySelected = findViewById(R.id.etxtCountrySelected);

        // ********* Init Components *********
        initFlowCountries();
    }

    private void initFlowCountries() {
        CustomButton btnCountry;
        String[] countries = getResources().getStringArray(R.array.teams);
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

    @Override
    public void onClick(View v) {
        String country = ((Button) v).getText().toString();
        etxtCountrySelected.setText(country);
    }
}