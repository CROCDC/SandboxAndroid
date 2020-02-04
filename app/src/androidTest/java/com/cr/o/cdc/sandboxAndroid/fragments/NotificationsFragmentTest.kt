package com.cr.o.cdc.sandboxAndroid.fragments

import android.content.Intent
import android.widget.TextView
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers
import com.cr.o.cdc.sandboxAndroid.R
import com.cr.o.cdc.sandboxAndroid.vm.NotificationsViewModel
import com.cr.o.cdc.sharedtest.getMessage
import com.cr.o.cdc.sharedtest.myPostValue
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
        val vm = NotificationsViewModel(getPushToken())
        launchFragmentInContainer<NotificationsFragment>(
            listOf(vm)
        )

        vm.token.myPostValue("message")

        onView(ViewMatchers.withId(R.id.txt_push_token)).check { view, _ ->
            assertTrue(getMessage("txt_push_token"), (view as TextView).text == "message")
        }
    }
}