package com.cr.o.cdc.sandboxAndroid

import android.content.Intent
import android.widget.TextView
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers
import com.cr.o.cdc.sandboxAndroid.fragments.NotificationsFragment
import com.cr.o.cdc.sandboxAndroid.vm.NotificationsViewModel
import com.cr.o.cdc.sharedtest.getPrivateField
import com.cr.o.cdc.sharedtest.postValue
import junit.framework.TestCase.assertTrue
import org.junit.Test

class NotificationsFragmentTest {

    @Test
    fun assertBroadcastReceiverSetTextTxtMsgNotifications() {
        launchFragmentInContainer<NotificationsFragment>(themeResId = R.style.AppTheme).onFragment {
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
        launchFragmentInContainer<NotificationsFragment>(themeResId = R.style.AppTheme).onFragment {
            (it.getPrivateField("viewModel") as NotificationsViewModel).token.postValue("message")
        }

        onView(ViewMatchers.withId(R.id.txt_push_token)).check { view, _ ->
            assertTrue((view as TextView).text == "message")
        }
    }
}