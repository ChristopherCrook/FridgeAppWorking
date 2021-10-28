package com.example.myfridge;

import android.Manifest;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class AddAlarmActivity extends AppCompatActivity {

    private int hour_m;
    private int minute_m;

    TimePickerDialog.OnTimeSetListener mTimeSetListener =
            new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(android.widget.TimePicker view,
                                      int hourOfDay, int minute) {
                    hour_m = hourOfDay;
                    minute_m = minute;
                }
            };

    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            @NonNull String[] permissions,
            @NonNull int[] grantResults
    )
    {
        super.onRequestPermissionsResult(
                requestCode,
                permissions,
                grantResults
        );
        if (requestCode == 1)
        {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                System.out.println("Permissions Set");
            else
                System.out.println("Failed to set permissions");
        }
        else
            System.out.println("Request Code invalid");
    }

    protected void onCreate(Bundle savedInstanceData) {
        super.onCreate(savedInstanceData);
        setContentView(R.layout.activity_add_alarm);

        // Check Permissions
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SCHEDULE_EXACT_ALARM)
        != PackageManager.PERMISSION_GRANTED)
        {
            System.out.println("Permissions not set");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                ActivityCompat.requestPermissions(
                        this,
                        new String[]{Manifest.permission.SCHEDULE_EXACT_ALARM},
                1
                );
            }
        }

        Intent intent = getIntent();

        String name = intent.getStringExtra("Name");
        int id = intent.getIntExtra("ID", 42);

        EditText note = findViewById(R.id.addAlarmAddNoteText);
        TextView nameView = findViewById(R.id.addAlarmNameTextView);

        nameView.setText(name);

        CalendarView calendarView = findViewById(R.id.addAlarmCalendar);

        Button add = findViewById(R.id.addAlarmFromActivityButton);
        add.setOnClickListener(view -> {
            NotificationReceiver scheduler = new NotificationReceiver();

            Calendar cal = new GregorianCalendar();
            Calendar calFromView = new GregorianCalendar();
            calFromView.setTime(new Date(calendarView.getDate()));
            int year = calFromView.get(Calendar.YEAR);
            int month = calFromView.get(Calendar.MONTH) + 1;
            int day = calFromView.get(Calendar.DAY_OF_MONTH);

            cal.set(year, month, day, hour_m, minute_m);

            String Title = "Grocery Alarm: " + name;

            scheduler.ScheduleNotification(
                    getApplicationContext(),
                    Title,
                    note.getText().toString(),
                    cal.getTimeInMillis(),
                    id
            );

            finish();
        });
    }
    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment(mTimeSetListener);
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }
}
