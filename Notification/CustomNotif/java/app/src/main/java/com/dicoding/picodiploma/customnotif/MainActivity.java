package com.dicoding.picodiploma.customnotif;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonNotif = findViewById(R.id.button_show_notification);
        buttonNotif.setOnClickListener(view ->
                startService(new Intent(MainActivity.this, NotificationService.class))
        );
    }
}

