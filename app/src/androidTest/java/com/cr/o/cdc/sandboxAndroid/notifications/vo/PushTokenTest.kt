package com.cr.o.cdc.sandboxAndroid.notifications.vo

import androidx.test.platform.app.InstrumentationRegistry
import com.cr.o.cdc.sandboxAndroid.notifications.vo.PushToken
import com.cr.o.cdc.sharedtest.sharedPreferences
import junit.framework.TestCase.assertTrue
import org.junit.Test

class PushTokenTest {

    val context = InstrumentationRegistry.getInstrumentation().context

    @Test
    fun getPushesQuantity() {
        val pushToken = PushToken(context.sharedPreferences())

        assertTrue(pushToken.pushQuantity == 0)

        pushToken.addPush()

        assertTrue(pushToken.pushQuantity == 1)

    }
}