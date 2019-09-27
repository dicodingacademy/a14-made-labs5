package com.dicoding.picodiploma.myfirebasecloudmessaging

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_subscribe.setOnClickListener {
            FirebaseMessaging.getInstance().subscribeToTopic("news")
            val msg = getString(R.string.msg_subscribed)
            Log.d(TAG, msg)
            Toast.makeText(this@MainActivity, msg, Toast.LENGTH_SHORT).show()
        }

        btn_token.setOnClickListener {
            FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener { instanceIdResult ->
                val deviceToken = instanceIdResult.token
                val msg = getString(R.string.msg_token_fmt, deviceToken)
                Toast.makeText(this@MainActivity, msg, Toast.LENGTH_SHORT).show()
                Log.d(TAG, "Refreshed token: $deviceToken")
            }
        }
    }
}
