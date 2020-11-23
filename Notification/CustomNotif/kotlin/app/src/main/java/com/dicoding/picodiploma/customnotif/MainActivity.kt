package com.dicoding.picodiploma.customnotif

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val buttonNotif = findViewById<Button>(R.id.button_show_notification)
        buttonNotif.setOnClickListener {
            startService(Intent(this, NotificationService::class.java))
        }
    }
}

