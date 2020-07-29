package com.cr.o.cdc.sandboxAndroid.notifications.vo

import com.cr.o.cdc.sandboxAndroid.coronavirus.di.CoronavirusModule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
@UninstallModules(CoronavirusModule::class)
class PushTokenTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var pushToken: PushToken

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun getPushesQuantity() {
        assertTrue(pushToken.pushQuantity == 0)

        pushToken.addPush()

        assertTrue(pushToken.pushQuantity == 1)
    }
}
