package com.cr.o.cdc.sandboxAndroid

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.test.platform.app.InstrumentationRegistry
import com.cr.o.cdc.sandboxAndroid.fragments.NotificationsFragment
import com.cr.o.cdc.sandboxAndroid.push.MyFirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import junit.framework.TestCase.assertTrue
import org.junit.Test

class MyFirebaseMessagingServiceTest {

    private val myFirebaseMessaging = MyFirebaseMessagingService()


    val context = InstrumentationRegistry.getInstrumentation().context

    @Test
    fun assertSendBroadcastToNotificationsFragment() {

        myFirebaseMessaging.onMessageReceived(RemoteMessage(Bundle()))

        object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                assertTrue(intent.extras?.getString(NotificationsFragment.EXTRA_MSG_NOTIFICATION) == "message")
            }
        }.also {
            LocalBroadcastManager.getInstance(context)
                .registerReceiver(it, IntentFilter(NotificationsFragment.BROADCAST_RECEIVER))
        }

    }
}