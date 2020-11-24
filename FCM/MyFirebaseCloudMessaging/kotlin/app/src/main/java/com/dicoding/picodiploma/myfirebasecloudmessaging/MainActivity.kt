package com.dicoding.picodiploma.myfirebasecloudmessaging

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val subscribeButton = findViewById<Button>(R.id.btn_subscribe)
        subscribeButton.setOnClickListener {
            FirebaseMessaging.getInstance().subscribeToTopic("news")
            val msg = getString(R.string.msg_subscribed)
            Log.d(TAG, msg)
            Toast.makeText(this@MainActivity, msg, Toast.LENGTH_SHORT).show()
        }

        val logTokenButton = findViewById<Button>(R.id.btn_token)
        logTokenButton.setOnClickListener {
            val deviceToken = MyFirebaseMessagingService
            val msg = getString(R.string.msg_token_fmt, deviceToken)
            Toast.makeText(this@MainActivity, msg, Toast.LENGTH_SHORT).show()
            Log.d(TAG, "Refreshed token: $deviceToken")
            FirebaseMessaging.getInstance().token.addOnSuccessListener { deviceToken ->
                val msg = getString(R.string.msg_token_fmt, deviceToken)
                Toast.makeText(this@MainActivity, msg, Toast.LENGTH_SHORT).show()
                Log.d(TAG, "Refreshed token: $deviceToken")
            }
        }
    }
}
