package com.cr.o.cdc.sandboxAndroid.downdetector.utils

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.cr.o.cdc.sandboxAndroid.R

fun Int.formatToString(): String = String.format("%d", this)

fun Context.createNotification(
    message: String,
    notification: (NotificationCompat.Builder.() -> NotificationCompat.Builder)? = null
): Notification {
    val channelName = getString(R.string.app_name)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)
            .createNotificationChannel(
                NotificationChannel(
                    channelName,
                    channelName,
                    NotificationManager.IMPORTANCE_DEFAULT
                ).apply {
                    importance = NotificationManager.IMPORTANCE_DEFAULT
                    lightColor = ContextCompat.getColor(this@createNotification, R.color.white)
                    enableVibration(true)
                    lockscreenVisibility = NotificationCompat.VISIBILITY_PRIVATE
                }
            )
    }

    return NotificationCompat.Builder(this, channelName).apply {
        notification?.let { it() }
        setSmallIcon(R.drawable.ic_fiber_new_black_24dp).setStyle(
            NotificationCompat.BigTextStyle()
                .bigText(message)
        ).setContentText(message)
    }.build()
}
