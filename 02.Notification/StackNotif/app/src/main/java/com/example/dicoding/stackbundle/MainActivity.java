package com.example.dicoding.stackbundle;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText edtSender, edtMessage;
    Button btnKirim;

    private final static String GROUP_KEY_EMAILS = "group_key_emails";

    private int idNotif = 0;
    private int maxNotif = 2;

    List<NotificationItem> stackNotif = new ArrayList<>();

    private final static int NOTIF_REQUEST_CODE = 200;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtSender = (EditText) findViewById(R.id.edtSender);
        edtMessage = (EditText) findViewById(R.id.edtMessage);
        btnKirim = (Button) findViewById(R.id.btnKirim);


        btnKirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String sender = edtSender.getText().toString();
                String message = edtMessage.getText().toString();

                if(sender.isEmpty()||message.isEmpty()) {

                    Toast.makeText(MainActivity.this,"Data harus diisi",Toast.LENGTH_SHORT).show();

                 }else{

                    NotificationItem notificationItem =new NotificationItem(idNotif,sender,message);

                    stackNotif.add(new NotificationItem(idNotif,sender,message));

                    sendNotif();

                    idNotif++;

                    edtSender.setText("");
                    edtMessage.setText("");
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
                }
            }
        });
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        stackNotif.clear();
        idNotif = 0;

    }

    public void sendNotif(){

        NotificationManagerCompat manager = NotificationManagerCompat.from(this);

        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(),R.drawable.ic_notifications_white_48px);

        Intent intent = new Intent(this, MainActivity.class);

        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this,NOTIF_REQUEST_CODE,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = null;
        if (idNotif < maxNotif) {

            notification = new NotificationCompat.Builder(this)
                    .setContentTitle("New Email from "+stackNotif.get(idNotif).getSender())
                    .setContentText(stackNotif.get(idNotif).getMessage())
                    .setSmallIcon(R.drawable.ic_mail_white_48px)
                    .setLargeIcon(largeIcon)
                    .setGroup(GROUP_KEY_EMAILS)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    .build();

        } else {
            NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle()
                    .addLine("New Email from "+stackNotif.get(idNotif).getSender())
                    .addLine("New Email from "+stackNotif.get(idNotif-1).getSender())
                    .setBigContentTitle(idNotif + " new emails")
                    .setSummaryText("mail@dicoding");
            notification = new NotificationCompat.Builder(this)
                    .setContentTitle(idNotif + " new emails")
                    .setContentText("mail@dicoding.com")
                    .setSmallIcon(R.drawable.ic_mail_white_48px)
                    .setGroup(GROUP_KEY_EMAILS)
                    .setGroupSummary(true)
                    .setContentIntent(pendingIntent)
                    .setStyle(inboxStyle)
                    .setAutoCancel(true)
                    .build();

        }
        manager.notify(idNotif,notification);
    }


}
