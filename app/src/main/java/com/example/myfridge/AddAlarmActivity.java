package com.example.myfridge;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
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

    // Time Picker listener
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

        if (ContextCompat.checkSelfPermission(
                getApplicationContext(),Manifest.permission.SCHEDULE_EXACT_ALARM)
                != PackageManager.PERMISSION_GRANTED
        )
        {
            System.out.println("Permissions not set");
        }

        Intent intent = getIntent();

        String name = intent.getStringExtra("Name");
        int id = intent.getIntExtra("ID", 42);

        EditText note = findViewById(R.id.addAlarmAddNoteText);
        TextView nameView = findViewById(R.id.addAlarmNameTextView);

        nameView.setText(name);

        CalendarView calendarView = findViewById(R.id.addAlarmCalendar);

        // Process user inputs when user clicks Add Alarm
        Button add = findViewById(R.id.addAlarmFromActivityButton);
        add.setOnClickListener(view -> {
            Calendar cal = new GregorianCalendar();
            Calendar calFromView = new GregorianCalendar();
            calFromView.setTime(new Date(calendarView.getDate()));
            int year = calFromView.get(Calendar.YEAR);
            int month = calFromView.get(Calendar.MONTH) + 1;
            int day = calFromView.get(Calendar.DAY_OF_MONTH);

            cal.set(year, month, day, hour_m, minute_m);

            String Title = "Grocery Alarm: " + name;

            scheduleNotification(
                    getNotification(Title, note.getText().toString()),
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

    private void scheduleNotification(Notification notification, long time, int id) {

        Intent notificationIntent = new Intent(this, NotificationReceiver.class);
        notificationIntent.putExtra(NotificationReceiver.NOTIFICATION_ID, id);
        notificationIntent.putExtra(NotificationReceiver.NOTIFICATION, notification);
        @SuppressLint("UnspecifiedImmutableFlag") PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, time, pendingIntent);
    }

    private Notification getNotification(String title, String content) {
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle(title);
        builder.setContentText(content);
        builder.setSmallIcon(R.mipmap.ic_launcher_round);
        return builder.build();
    }
}
