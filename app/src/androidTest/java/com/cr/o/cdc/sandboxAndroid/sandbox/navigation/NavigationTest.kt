package com.cr.o.cdc.sandboxAndroid.sandbox.navigation

import android.content.Context
import android.content.Intent
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.cr.o.cdc.sandboxAndroid.MainActivity
import com.cr.o.cdc.sandboxAndroid.utils.DummyActivityA
import org.junit.Rule
import org.junit.Test


class NavigationTest {

    private val context: Context = InstrumentationRegistry.getInstrumentation().context

    @Rule
    @JvmField
    val activityRule: ActivityTestRule<DummyActivityA> = ActivityTestRule(
        DummyActivityA::class.java, false, false
    )

    @Test
    fun `navigateFromDummyActivityAtoDummyActivityBWithflagsIntent-FLAG_ACTIVITY_NEW_TASKorIntent-FLAG_ACTIVITY_CLEAR_TASK`() {
        activityRule.launchActivity(Intent(context, DummyActivityA::class.java))
        Thread.sleep(10000)
    }
}