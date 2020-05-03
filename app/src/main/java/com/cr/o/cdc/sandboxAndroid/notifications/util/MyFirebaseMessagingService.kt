package com.cr.o.cdc.sandboxAndroid.notifications.util

import android.content.Intent
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.cr.o.cdc.sandboxAndroid.notifications.fragments.NotificationsFragment
import com.cr.o.cdc.sandboxAndroid.notifications.vo.PushToken
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.pubnub.api.PNConfiguration
import com.pubnub.api.PubNub
import com.pubnub.api.enums.PNPushType
import dagger.android.AndroidInjection
import javax.inject.Inject

class MyFirebaseMessagingService : FirebaseMessagingService() {

    @Inject
    lateinit var pushToken: PushToken

    override fun onCreate() {
        super.onCreate()
        AndroidInjection.inject(this)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        LocalBroadcastManager.getInstance(this).sendBroadcast(
            Intent(NotificationsFragment.BROADCAST_RECEIVER).apply {
                putExtra(NotificationsFragment.EXTRA_MSG_NOTIFICATION, "message")
            })
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        pushToken.savePushToken(token)

        PubNub(PNConfiguration().apply {
            publishKey = "pub-c-86f541ab-abef-4db4-a577-81694d93feeb"
            subscribeKey = "sub-c-8e816636-3e51-11ea-afe9-722fee0ed680"
            isSecure = true
        }).addPushNotificationsOnChannels()
            .pushType(PNPushType.FCM)
            .channels(listOf("Notifications"))
            .deviceId(token)
    }
}
