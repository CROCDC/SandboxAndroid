package com.cr.o.cdc.sandboxAndroid

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.platform.app.InstrumentationRegistry
import com.cr.o.cdc.sandboxAndroid.push.PushToken
import com.cr.o.cdc.sandboxAndroid.vm.NotificationsViewModel
import com.cr.o.cdc.sharedtest.getValueLivedata
import com.cr.o.cdc.sharedtest.sharedPreferences
import junit.framework.TestCase.assertTrue
import org.junit.Rule
import org.junit.Test

class NotificationsViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun observeReferenceOfPushTokenAndTestLiveDataOfSharedPreferences() {
        val pushToken = PushToken(context.sharedPreferences())
        val viewModel = NotificationsViewModel(pushToken)

        val token = getValueLivedata(viewModel.token, 10) {
            pushToken.savePushToken("token")
        }

        Thread.sleep(100)

        assertTrue(token == "token")
    }
}