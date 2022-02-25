package com.example.servicesdemo

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import android.R
import android.app.Notification


class ForegroundService : Service() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Thread {
            Runnable {
                kotlin.run {
                    Log.e("Service", "Foreground service is running...")
                    try {
                        Thread.sleep(5000)
                    } catch (exception: InterruptedException) {
                        exception.printStackTrace()
                    }
                }
            }
        }.start()

        val CHANNEL_ID = "Foreground Service Channel"
        val channel = NotificationChannel("Foreground Service Channel",
            CHANNEL_ID,
            NotificationManager.IMPORTANCE_HIGH)

        getSystemService(NotificationManager::class.java).createNotificationChannel(channel)

        val notification = Notification.Builder(this, CHANNEL_ID)
            .setContentText("Service is running")
            .setContentTitle("Service enabled")
            .setSmallIcon(R.drawable.ic_lock_idle_low_battery)

        startForeground(1001, notification.build())

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}