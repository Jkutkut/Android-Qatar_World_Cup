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
import androidx.annotation.NonNull;

import com.jkutkut.custom.CustomActivity;
import com.jkutkut.custom.CustomButton;
import com.jkutkut.qatar_wold_cup.data.MatchResult;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddResultActivity extends CustomActivity {

    // TODO Fix UI to be more user friendly

    public static final String TEAM_SIDE = "teamSide";
    private static final int TEAM_1 = 1;
    private static final int TEAM_2 = 2;
    public static final String OPONENT = "oponent";

    // ********* UI Components *********
    private CustomButton btnDate;
    private CustomButton btnTime;
    private CustomButton btnTeam1;
    private CustomButton btnTeam2;
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

    // ********* Session *********
    private static final String CALENDAR_KEY = "calendar";
    private static final String TEAM_1_KEY = "team1";
    private static final String TEAM_2_KEY = "team2";
    private static final String PHASE_KEY = "phase";
    private static final String GOALS_1_KEY = "goals1";
    private static final String GOALS_2_KEY = "goals2";

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
                    if (i == null) return;
                    Bundle data = i.getExtras();
                    Button btnTeam = (data.getInt(TEAM_SIDE) == TEAM_1) ? btnTeam1 : btnTeam2;
                    if (data.getString(CountrySelectionActivity.COUNTRY_KEY) != null)
                        btnTeam.setText(data.getString(CountrySelectionActivity.COUNTRY_KEY));
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

        btnTeam1.setOnClickListener(view -> ask4team(TEAM_1));
        btnTeam2.setOnClickListener(view -> ask4team(TEAM_2));
        btnSave.setOnClickListener(view -> saveResult());
        btnClear.setOnClickListener(view -> clearForm());

        // ********* Init values *********
        updateDate();
        updateTime();

        // TODO set click feedback
//        btnDate.setClickFeedback(getColor(R.attr.btn_clear_clicked));
//        btnTime.setClickFeedback(getColor(R.attr.btn_clear_clicked));
//        btnTeam1.setClickFeedback(getColor(R.attr.btn_clear_clicked));
//        btnTeam2.setClickFeedback(getColor(R.attr.btn_clear_clicked));
        btnSave.setClickFeedback(getColor(R.color.qatar_light));
        btnClear.setClickFeedback(getColor(R.color.qatar_light));
    }

    private void saveResult() {
        if (calendar.getTimeInMillis() > System.currentTimeMillis()) {
            alert(getString(R.string.error_date_in_future));
            return;
        }
        String datetime = String.format("%s %s", btnDate.getText(), btnTime.getText());
        String[] teams = {btnTeam1.getText().toString(), btnTeam2.getText().toString()};
        int[] idErrors = {R.string.alert_team1_missing, R.string.alert_team2_missing};
        // Note: No need to check if they're the same team
        for (int i = 0; i < 2; i++) {
            if (teams[i].equals(getString(R.string.btnTeamDefault))) {
                alert(getString(idErrors[i]));
                return;
            }
        }
        idErrors = new int[]{R.string.alert_goals_team1_missing, R.string.alert_goals_team2_missing};
        String[] goalsStr = {etxtGoalsTeam1.getText().toString(), etxtGoalsTeam2.getText().toString()};
        for (int i = 0; i < 2; i++) {
            if (goalsStr[i].trim().isEmpty()) {
                alert(getString(idErrors[i]));
                return;
            }
        }
        int[] goals = new int[2];
        idErrors = new int[]{R.string.alert_goals_team1_invalid, R.string.alert_goals_team2_invalid};
        for (int i = 0; i < 2; i++) {
            try {
                goals[i] = Integer.parseInt(goalsStr[i]);
            } catch (NumberFormatException e) {
                alert(getString(idErrors[i]));
                return;
            }
        }
        QatarApplication app = (QatarApplication) getApplicationContext();
        app.getResultData().addResult(new MatchResult(
            spnPhase.getSelectedItem().toString(),
            teams[0],
            teams[1],
            goals[0],
            goals[1],
            datetime
        ));
        alert(getString(R.string.alert_result_saved));
        finish();
    }

    private void ask4team(int team) {
        Intent intent = new Intent(this, CountrySelectionActivity.class);
        intent.putExtra(TEAM_SIDE, team);

        String oponent = ((team == TEAM_1) ? btnTeam2 : btnTeam1).getText().toString();
        if (oponent.equals(getString(R.string.btnTeamDefault)))
            oponent = null;
        intent.putExtra(OPONENT, oponent);

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

    // ********* Session *********
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(CALENDAR_KEY, calendar);
        outState.putString(TEAM_1_KEY, btnTeam1.getText().toString());
        outState.putString(TEAM_2_KEY, btnTeam2.getText().toString());
        outState.putInt(PHASE_KEY, spnPhase.getSelectedItemPosition());
        outState.putString(GOALS_1_KEY, etxtGoalsTeam1.getText().toString());
        outState.putString(GOALS_2_KEY, etxtGoalsTeam2.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        calendar = (Calendar) savedInstanceState.getSerializable(CALENDAR_KEY);
        updateDate();
        updateTime();
        btnTeam1.setText(savedInstanceState.getString(TEAM_1_KEY));
        btnTeam2.setText(savedInstanceState.getString(TEAM_2_KEY));
        spnPhase.setSelection(savedInstanceState.getInt(PHASE_KEY));
        etxtGoalsTeam1.setText(savedInstanceState.getString(GOALS_1_KEY));
        etxtGoalsTeam2.setText(savedInstanceState.getString(GOALS_2_KEY));
    }
}