package com.cr.o.cdc.sandboxAndroid.notifications.vo

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.cr.o.cdc.sandboxAndroid.coronavirus.di.CoronavirusModule
import com.cr.o.cdc.sharedtest.sharedPreferences
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import junit.framework.TestCase.assertTrue
import org.junit.Test

@HiltAndroidTest
@UninstallModules(CoronavirusModule::class)
class PushTokenTest {

    private val context: Context = InstrumentationRegistry.getInstrumentation().context

    @Test
    fun getPushesQuantity() {
        val pushToken = PushToken(context.sharedPreferences())

        assertTrue(pushToken.pushQuantity == 0)

        pushToken.addPush()

        assertTrue(pushToken.pushQuantity == 1)
    }
}
