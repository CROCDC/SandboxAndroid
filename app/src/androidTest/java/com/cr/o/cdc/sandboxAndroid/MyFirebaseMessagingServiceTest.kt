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

    val sandBoxApp = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as SandBoxApp

    @Test
    fun assertSendBroadcastToNotificationsFragment() {

        var assert = false

        object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                assert = true
                assertTrue(intent.extras?.getString(NotificationsFragment.EXTRA_MSG_NOTIFICATION) == "message")
            }
        }.also {
            LocalBroadcastManager.getInstance(sandBoxApp)
                .registerReceiver(it, IntentFilter(NotificationsFragment.BROADCAST_RECEIVER))
        }

        mockk<MyFirebaseMessagingService>(relaxed = true).apply {
            every { application } returns sandBoxApp
        }.onMessageReceived(RemoteMessage(Bundle()))

        assertTrue(assert)

    }
}