package com.example.myfridge;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

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

    protected void onCreate(Bundle savedInstanceData) {
        super.onCreate(savedInstanceData);
        setContentView(R.layout.activity_add_alarm);

        Intent intent = getIntent();

        String name = intent.getStringExtra("Name");
        int id = intent.getIntExtra("ID", 42);
        Long expires = intent.getLongExtra("Expiration", 0);

        EditText note = findViewById(R.id.addAlarmAddNoteText);
        TextView nameView = findViewById(R.id.addAlarmNameTextView);

        nameView.setText(name);

        CalendarView calendarView = findViewById(R.id.addAlarmCalendar);

        Button add = findViewById(R.id.addAlarmFromActivityButton);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationReceiver scheduler = new NotificationReceiver();
                scheduler.Set(getApplicationContext());
                Calendar cal = new GregorianCalendar();
                Calendar calFromView = new GregorianCalendar();
                calFromView.setTime(new Date(calendarView.getDate()));
                int year = calFromView.get(Calendar.YEAR);
                int month = calFromView.get(Calendar.MONTH) + 1;
                int day = calFromView.get(Calendar.DAY_OF_MONTH);

                cal.set(year, month, day, hour_m, minute_m);

                System.out.println(year + " " + month + " " + day);

                String Title = "Grocery Alarm: " + name;

                scheduler.CreateNotification(
                        Title,
                        note.getText().toString(),
                        cal.getTimeInMillis(),
                        id
                );

                finish();
            }
        });
    }
    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment(mTimeSetListener);
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }
}
