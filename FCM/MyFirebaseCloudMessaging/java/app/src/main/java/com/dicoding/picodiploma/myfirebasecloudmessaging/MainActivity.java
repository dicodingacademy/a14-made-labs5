package com.dicoding.picodiploma.myfirebasecloudmessaging;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button subscribeButton = findViewById(R.id.btn_subscribe);
        subscribeButton.setOnClickListener(v -> {
            FirebaseMessaging.getInstance().subscribeToTopic("news");
            String msg = getString(R.string.msg_subscribed);
            Log.d(TAG, msg);
            Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
        });

        Button logTokenButton = findViewById(R.id.btn_token);
        logTokenButton.setOnClickListener(v -> FirebaseMessaging.getInstance().getToken().addOnSuccessListener(deviceToken -> {
            String msg = getString(R.string.msg_token_fmt, deviceToken);
            Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Refreshed token: " + deviceToken);
        }));
    }
}
