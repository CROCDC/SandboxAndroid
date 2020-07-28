package com.cr.o.cdc.sandboxAndroid.notifications.vm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.platform.app.InstrumentationRegistry
import com.cr.o.cdc.sandboxAndroid.coronavirus.di.CoronavirusModule
import com.cr.o.cdc.sandboxAndroid.notifications.vo.PushToken
import com.cr.o.cdc.sharedtest.getValueLiveData
import com.cr.o.cdc.sharedtest.sharedPreferences
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import junit.framework.TestCase.assertTrue
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(CoronavirusModule::class)
class NotificationsViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun observeReferenceOfPushTokenAndTestLiveDataOfSharedPreferences() {
        val pushToken = PushToken(context.sharedPreferences())
        val viewModel = NotificationsViewModel(pushToken)

        val token = getValueLiveData(viewModel.token, 10) {
            pushToken.savePushToken("token")
        }

        Thread.sleep(100)

        assertTrue(token == "token")
    }
}
