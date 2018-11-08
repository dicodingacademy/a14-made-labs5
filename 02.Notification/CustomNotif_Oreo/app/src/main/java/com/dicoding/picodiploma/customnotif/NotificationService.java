package com.dicoding.picodiploma.customnotif;

import android.annotation.TargetApi;
import android.app.IntentService;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.RemoteInput;

/**
 * Created by dicoding on 1/20/2017.
 */

public class NotificationService extends IntentService {
    public static String REPLY_ACTION = "com.dicoding.notification.directreply.REPLY_ACTION";
    private static String KEY_REPLY = "key_reply_message";

    private int mNotificationId;
    private int mMessageId;

    public NotificationService() {
        super("NotificationService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            showNotification();
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void showNotification() {
        mNotificationId = 1;
        mMessageId = 123;


        // Tambahkan channel id, channel name , dan tingkat importance
        String channelId = "channel_01";
        CharSequence channelName = "dicoding channel";
        int importance = NotificationManager.IMPORTANCE_HIGH;

        String replyLabel = getString(R.string.notif_action_reply);
        RemoteInput remoteInput = new RemoteInput.Builder(KEY_REPLY)
                .setLabel(replyLabel)
                .build();

        NotificationCompat.Action replyAction = new NotificationCompat.Action.Builder(
                R.drawable.ic_reply_black_24px, replyLabel, getReplyPendingIntent())
                .addRemoteInput(remoteInput)
                .setAllowGeneratedReplies(true)
                .build();

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this,channelId)
                .setSmallIcon(R.drawable.ic_notifications_white_48px)
                .setContentTitle(getString(R.string.notif_title))
                .setContentText(getString(R.string.notif_content))
                .setShowWhen(true)
                .addAction(replyAction);

        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationChannel mChannel = new NotificationChannel(channelId, channelName, importance);

        mNotificationManager.createNotificationChannel(mChannel);

        mNotificationManager.notify(mNotificationId, mBuilder.build());
    }

    private PendingIntent getReplyPendingIntent() {
        Intent intent;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent = NotificationBroadcastReceiver.getReplyMessageIntent(this, mNotificationId, mMessageId);
            return PendingIntent.getBroadcast(getApplicationContext(), 100, intent,
                    PendingIntent.FLAG_UPDATE_CURRENT);
        } else {
            intent = ReplyActivity.getReplyMessageIntent(this, mNotificationId, mMessageId);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            return PendingIntent.getActivity(this, 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        }
    }

    public static CharSequence getReplyMessage(Intent intent) {
        Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);
        if (remoteInput != null) {
            return remoteInput.getCharSequence(KEY_REPLY);
        }
        return null;
    }
}

