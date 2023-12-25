package com.example.messaging

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

@SuppressLint("MissingFirebaseInstanceTokenRefresh")
class MyFirebaseCloudMessagingServices () :FirebaseMessagingService(){



    @RequiresApi(Build.VERSION_CODES.O)
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        if (message.notification != null){
            val notificationChannel= NotificationChannel("fcm_kotlin_app","fcm_kotlin",NotificationManager.IMPORTANCE_HIGH)
            val notificationBuilder=NotificationCompat.Builder(this)
                .setContentTitle(message.notification!!.title)
                .setContentText(message.notification!!.body)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setAutoCancel(true)
                .build()

            val notificationMessage= getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationMessage.createNotificationChannel(notificationChannel)
            notificationMessage.notify(0,notificationBuilder)
        }
    }

}


