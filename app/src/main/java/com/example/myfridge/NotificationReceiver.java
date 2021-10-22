package com.example.myfridge;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class NotificationReceiver extends BroadcastReceiver {

    private NotificationManager Manager_N;
    private NotificationChannel Channel;
    private Context context;
    private Boolean isSet;
    private String ChannelID = "FridgeAlarmID";
    private String ChannelName = "Fridge Alarms";

    public NotificationReceiver() {
        isSet = Boolean.FALSE;
    }

    public void Set(Context context) {
        this.context = context;
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Channel = new NotificationChannel(
                    ChannelID,
                    ChannelName,
                    importance
            );
        }

        Manager_N = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Manager_N.createNotificationChannel(Channel);
            isSet = Boolean.TRUE;
        }
    }

    public void CreateNotification(String title, String text, Long time, int id) {
        if (isSet == Boolean.FALSE)
        {
            System.out.println("Channel Not Set");
            return;
        }

        Intent intent = new Intent(this.context, MainActivity.class);

        intent.putExtra("title", title);
        intent.putExtra("text", text);
        intent.putExtra("ID", id);
        PendingIntent pending = PendingIntent.getBroadcast(
                context,
                id,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
        // Schdedule notification
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            manager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, time, pending);
        }
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        // Build notification based on Intent
        Notification notification = new NotificationCompat.Builder(context, ChannelID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(intent.getStringExtra("title"))
                .setContentText(intent.getStringExtra("text"))
                .build();
        // Show notification
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(intent.getIntExtra("ID", 42), notification);
    }
}
