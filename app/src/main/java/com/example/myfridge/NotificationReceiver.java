package com.example.myfridge;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class NotificationReceiver extends BroadcastReceiver {

    private NotificationChannel Channel;

    private final String ChannelID = "FridgeAlarmID";

    public NotificationReceiver() {

        int importance = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            importance = NotificationManager.IMPORTANCE_DEFAULT;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelName = "Fridge Alarms";
            Channel = new NotificationChannel(
                    ChannelID,
                    channelName,
                    importance
            );

        }
    }

    public void ScheduleNotification(Context context, String title, String text, Long time, int id) {
        AlarmManager alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, NotificationReceiver.class);
        intent.putExtra("title", title);
        intent.putExtra("text", text);
        intent.putExtra("ID", id);

        @SuppressLint("UnspecifiedImmutableFlag") PendingIntent alarmIntent = PendingIntent.getBroadcast(
                context,
                0,
                intent,
                0
        );

        alarmMgr.set(
                AlarmManager.ELAPSED_REALTIME_WAKEUP,
                time,
                alarmIntent
        );
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            NotificationManager manager_N = context.getSystemService(NotificationManager.class);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                manager_N.createNotificationChannel(Channel);
            }
            else {
                System.out.println("Error: Build.VERSION.SDK_INT >= Build.VERSION_CODES.O Failed");
                return;
            }
        }
        else {
            System.out.println("Error: Build.VERSION.SDK_INT >= Build.VERSION_CODES.M Failed");
            return;
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(
                context,
                ChannelID
        )
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(intent.getStringExtra("title"))
                .setContentText(intent.getStringExtra("text"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);

        NotificationManagerCompat notifier = NotificationManagerCompat.from(context);
        notifier.notify(
                intent.getIntExtra("ID", 42),
                builder.build()
        );
    }
}
