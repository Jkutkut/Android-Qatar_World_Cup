package com.jkutkut.qatar_wold_cup;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import com.jkutkut.custom.CustomActivity;
import com.jkutkut.custom.CustomButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddResultActivity extends CustomActivity {

    // TODO Fix UI to be more user friendly
    // TODO Add click feedback to btnTeam1 and btnTeam2

    public static final String TEAM_SIDE = "teamSide";
    private static final int TEAM_1 = 1;
    private static final int TEAM_2 = 2;

    // ********* UI Components *********
    private Button btnDate;
    private Button btnTime;
    private Button btnTeam1;
    private Button btnTeam2;
    private Spinner spnPhase;
    private EditText etxtGoalsTeam1;
    private EditText etxtGoalsTeam2;
    private CustomButton btnSave;
    private CustomButton btnClear;

    // ********* DateTime Utils *********
    private SimpleDateFormat sdfDate;
    private SimpleDateFormat sdfTime;
    private Calendar calendar;

    // ********* Activity Result *********
    ActivityResultLauncher<Intent> teamSelectorLauncher;

    @Override
    @SuppressLint("MissingSuperCall") // We call super.onCreate on the parent class
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_add_result);

        // ********* DateTime Utils *********
        calendar = Calendar.getInstance();
        sdfDate = new SimpleDateFormat(getString(R.string.date_format));
        sdfTime = new SimpleDateFormat(getString(R.string.time_format));

        // ********* Team Selector *********
        teamSelectorLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    Intent i = result.getData();
                    Bundle data = i.getExtras();
                    Button btnTeam = (data.getInt(TEAM_SIDE) == TEAM_1) ? btnTeam1 : btnTeam2;
                    // TODO bug: always updating btnTeam2
                    if (data.getString(CountrySelectionActivity.COUNTRY_KEY) != null)
                        btnTeam.setText(data.getString(CountrySelectionActivity.COUNTRY_KEY));
                    alert("Team Selected correctly");
                }
            }
        );

        // ********* UI Components *********
        btnDate = findViewById(R.id.btnDate);
        btnTime = findViewById(R.id.btnTime);
        btnTeam1 = findViewById(R.id.btnTeam1);
        btnTeam2 = findViewById(R.id.btnTeam2);
        spnPhase = findViewById(R.id.spnPhase);
        etxtGoalsTeam1 = findViewById(R.id.etxtGoalsTeam1);
        etxtGoalsTeam2 = findViewById(R.id.etxtGoalsTeam2);
        btnSave = findViewById(R.id.btnSave);
        btnClear = findViewById(R.id.btnClear);

        // ********* Listeners *********
        DatePickerDialog.OnDateSetListener date = (view, year, month, day) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH,month);
            calendar.set(Calendar.DAY_OF_MONTH,day);
            updateDate();
        };
        TimePickerDialog.OnTimeSetListener time = (view, hour, minute) -> {
            calendar.set(Calendar.HOUR_OF_DAY, hour);
            calendar.set(Calendar.MINUTE, minute);
            updateTime();
        };

        btnDate.setOnClickListener(view -> new DatePickerDialog(
            AddResultActivity.this,
            R.style.date_picker_style, // TODO
            date,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show());
        btnTime.setOnClickListener(view -> new TimePickerDialog(
            AddResultActivity.this,
            // TODO
            time,
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            true
        ).show());

        btnTeam1.setOnClickListener(view -> {
            ask4team(TEAM_1);
        });
        btnTeam2.setOnClickListener(view -> {
            ask4team(TEAM_2);
        });
        btnSave.setOnClickListener(view -> {
            // TODO
            alert("TODO");
        });
        btnClear.setOnClickListener(view -> clearForm());

        // ********* Init values *********
        updateDate();
        updateTime();
        btnSave.setClickFeedback(getColor(R.color.qatar_light));
        btnClear.setClickFeedback(getColor(R.color.qatar_light));
    }

    private void ask4team(int team) {
        Intent intent = new Intent(this, CountrySelectionActivity.class);
        intent.putExtra(TEAM_SIDE, team);
        // TODO Prevent from selecting the same team twice
        teamSelectorLauncher.launch(intent);
    }

    // ********* Utils *********
    private void updateDate() {
        btnDate.setText(sdfDate.format(calendar.getTime()));
    }

    private void updateTime() {
        btnTime.setText(sdfTime.format(calendar.getTime()));
    }

    private void clearForm() {
        calendar = Calendar.getInstance();
        updateDate();
        updateTime();

        btnTeam1.setText(getString(R.string.btnTeamDefault));
        btnTeam2.setText(getString(R.string.btnTeamDefault));
        spnPhase.setSelection(0);
        etxtGoalsTeam1.setText("");
        etxtGoalsTeam2.setText("");
    }
}