package com.dicoding.picodiploma.simplenotif;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class MainActivity extends AppCompatActivity {
    public static final int NOTIFICATION_ID = 1;
    public static String CHANNEL_ID = "channel_01";
    public static CharSequence CHANNEL_NAME = "dicoding channel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notifications_white_48px)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_notifications_white_48px))
                .setContentTitle(getResources().getString(R.string.content_title))
                .setContentText(getResources().getString(R.string.content_text))
                .setSubText(getResources().getString(R.string.subtext))
                .setAutoCancel(true);

        /*
        Untuk android Oreo ke atas perlu menambahkan notification channel
        */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            mBuilder.setChannelId(CHANNEL_ID);
            if (mNotificationManager != null) {
                mNotificationManager.createNotificationChannel(channel);
            }
        }

        Notification notification = mBuilder.build();

        if (mNotificationManager != null) {
            mNotificationManager.notify(NOTIFICATION_ID, notification);
        }
    }
}
