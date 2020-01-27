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
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertTrue
import org.junit.Test

class MyFirebaseMessagingServiceTest {

    val app =
        InstrumentationRegistry.getInstrumentation().targetContext.applicationContext

    @Test
    fun assertSendBroadcastToNotificationsFragment() {
        var assert = false

        val r: BroadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                assert = true
                assertTrue(intent.extras?.getString(NotificationsFragment.EXTRA_MSG_NOTIFICATION) == "message")
            }
        }

        LocalBroadcastManager.getInstance(app).registerReceiver(r, IntentFilter(NotificationsFragment.BROADCAST_RECEIVER))

        mockk<MyFirebaseMessagingService>(relaxed = true).apply {
            every { baseContext } returns app
        }.onMessageReceived(RemoteMessage(Bundle()))

        Thread.sleep(4000)
        assertTrue(assert)

    }
}