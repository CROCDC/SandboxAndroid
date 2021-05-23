package com.cr.o.cdc.sandboxAndroid.notifications.fragments

import android.content.Intent
import android.widget.TextView
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers
import com.cr.o.cdc.sandboxAndroid.R
import com.cr.o.cdc.sandboxAndroid.coronavirus.di.CoronavirusModule
import com.cr.o.cdc.sandboxAndroid.editAndApply
import com.cr.o.cdc.sandboxAndroid.utils.FragmentTest
import com.cr.o.cdc.sharedtest.getMessage
import com.cr.o.cdc.sharedtest.sharedPreferences
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(CoronavirusModule::class)
class NotificationsFragmentTest : FragmentTest() {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun assertBroadcastReceiverSetTextTxtMsgNotifications() {
        launchFragmentInHiltContainer<NotificationsFragment>() {
            LocalBroadcastManager.getInstance(requireContext()).sendBroadcast(
                Intent(NotificationsFragment.BROADCAST_RECEIVER).apply {
                    putExtra(NotificationsFragment.EXTRA_MSG_NOTIFICATION, "message")
                })
        }

        onView(ViewMatchers.withId(R.id.txt_msg_notification)).check { view, _ ->
            assertTrue((view as TextView).text == "message")
        }
    }

    @Test
    fun assertPushTokenSetText() {
        launchFragmentInHiltContainer<NotificationsFragment>() {
            context?.sharedPreferences()?.editAndApply {
                putString("push_token", "message")
            }
        }

        onView(ViewMatchers.withId(R.id.txt_push_token)).check { view, _ ->
            assertTrue(getMessage("txt_push_token"), (view as TextView).text == "message")
        }
    }
}
