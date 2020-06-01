package com.cr.o.cdc.sandboxAndroid

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.ApolloQueryCall
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.cr.o.cdc.networking.NetworkResponse
import com.squareup.picasso.Picasso

inline fun SharedPreferences.editAndApply(
    values: SharedPreferences.Editor.() -> SharedPreferences.Editor
) {
    edit().values().apply()
}

fun <T> ApolloQueryCall<T>.execute(): LiveData<NetworkResponse<T>> {
    val result = MutableLiveData<NetworkResponse<T>>()
    enqueue(object : ApolloCall.Callback<T>() {
        override fun onFailure(e: ApolloException) {
            result.postValue(NetworkResponse.create(e))
        }

        override fun onResponse(response: Response<T>) {
            result.postValue(NetworkResponse.create(response))
        }
    })

    return result
}

fun ImageView.loadFromUrl(
    imageUrl: String?
) {
    if (imageUrl != null) {
        visibility = View.VISIBLE
        Picasso.get().load(imageUrl).into(this)
    } else {
        visibility = View.GONE
    }
}

fun EditText.getInput(): String = this.text.toString()

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

fun EditText.getString(): String = this.text.toString()
