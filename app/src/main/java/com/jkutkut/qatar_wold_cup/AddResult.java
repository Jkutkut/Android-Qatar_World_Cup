package com.jkutkut.qatar_wold_cup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.Button;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddResult extends AppCompatActivity {

    // ********* UI Components *********
    private Button btnDate;
    private Button btnTime;

    // ********* DateTime Utils *********
    private SimpleDateFormat sdfDate;
    private SimpleDateFormat sdfTime;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_result);

        // ********* DateTime Utils *********
        calendar = Calendar.getInstance();
        sdfDate = new SimpleDateFormat(getString(R.string.date_format));
        sdfTime = new SimpleDateFormat(getString(R.string.time_format));

        // ********* UI Components *********
        btnDate = findViewById(R.id.btnDate);
        btnTime = findViewById(R.id.btnTime);

        // ********* Set Listeners *********
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
            AddResult.this,
            date,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show());
        btnTime.setOnClickListener(view -> new TimePickerDialog(
            AddResult.this,
            time,
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            true
        ).show());

        // ********* Init values *********
        updateDate();
        updateTime();
    }

    private void updateDate() {
        btnDate.setText(sdfDate.format(calendar.getTime()));
    }

    private void updateTime() {
        btnTime.setText(sdfTime.format(calendar.getTime()));
    }
}