package com.cr.o.cdc.sandboxAndroid.push

import android.content.Intent
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.cr.o.cdc.sandboxAndroid.fragments.NotificationsFragment
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.pubnub.api.PNConfiguration
import com.pubnub.api.PubNub
import com.pubnub.api.callbacks.PNCallback
import com.pubnub.api.enums.PNPushType
import com.pubnub.api.models.consumer.PNStatus
import com.pubnub.api.models.consumer.push.PNPushAddChannelResult
import dagger.android.AndroidInjection
import javax.inject.Inject

/**
 * Created by Camilo on 22/01/20.
 */

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
            .pushType(PNPushType.GCM)
            .channels(listOf("Notifications"))
            .deviceId(token)
            .async(object : PNCallback<PNPushAddChannelResult>() {
                override fun onResponse(result: PNPushAddChannelResult, status: PNStatus) {
                    Log.d("PUBNUB", "-->PNStatus.getStatusCode = " + status.statusCode)
                }
            })
    }
}