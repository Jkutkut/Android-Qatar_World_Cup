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
    Button btnDate;
    Button btnTime;
    final Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_result);

        btnDate = findViewById(R.id.btnDate);
        btnTime = findViewById(R.id.btnTime);

        DatePickerDialog.OnDateSetListener date = (view, year, month, day) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH,month);
            calendar.set(Calendar.DAY_OF_MONTH,day);
            updateDate();
        };
        btnDate.setOnClickListener(view -> new DatePickerDialog(
            AddResult.this,
            date,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show());

        TimePickerDialog.OnTimeSetListener time = (view, hour, minute) -> {
            calendar.set(Calendar.HOUR_OF_DAY, hour);
            calendar.set(Calendar.MINUTE, minute);
            updateTime();
        };
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
        SimpleDateFormat dateFormat=new SimpleDateFormat(getString(R.string.date_format));
        btnDate.setText(dateFormat.format(calendar.getTime()));
    }

    private void updateTime() {
        SimpleDateFormat dateFormat=new SimpleDateFormat(getString(R.string.time_format));
        btnTime.setText(dateFormat.format(calendar.getTime()));
    }
}