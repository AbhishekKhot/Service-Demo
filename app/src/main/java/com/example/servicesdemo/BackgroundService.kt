package com.example.servicesdemo

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class BackgroundService : Service() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Thread(
            Runnable {
                kotlin.run {
                    while (true) {
                        Log.d("Service", "Service is running in background...")
                        try {
                            Thread.sleep(5000)
                        } catch (exception: InterruptedException) {
                            exception.printStackTrace()
                        }
                    }
                }
            }
        )
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}