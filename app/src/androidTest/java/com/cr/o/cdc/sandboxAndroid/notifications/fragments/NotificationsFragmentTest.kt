package com.cr.o.cdc.sandboxAndroid.notifications.fragments

import android.content.Intent
import android.widget.TextView
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers
import com.cr.o.cdc.sandboxAndroid.R
import com.cr.o.cdc.sandboxAndroid.editAndApply
import com.cr.o.cdc.sandboxAndroid.utils.FragmentTest
import com.cr.o.cdc.sharedtest.getMessage
import com.cr.o.cdc.sharedtest.sharedPreferences
import junit.framework.TestCase.assertTrue
import org.junit.Test

class NotificationsFragmentTest : FragmentTest() {

    @Test
    fun assertBroadcastReceiverSetTextTxtMsgNotifications() {
        launchFragmentInContainer<NotificationsFragment>().onFragment {
            LocalBroadcastManager.getInstance(it.requireContext()).sendBroadcast(
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
        launchFragmentInContainer<NotificationsFragment>().onFragment {
            it.context?.sharedPreferences()?.editAndApply {
                putString("push_token", "message")
            }
        }

        onView(ViewMatchers.withId(R.id.txt_push_token)).check { view, _ ->
            assertTrue(getMessage("txt_push_token"), (view as TextView).text == "message")
        }
    }
}