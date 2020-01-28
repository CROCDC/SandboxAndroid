package com.cr.o.cdc.sandboxAndroid

import android.view.View
import androidx.test.espresso.intent.rule.IntentsTestRule
import junit.framework.TestCase.assertTrue
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @Rule
    @JvmField
    val activityTestRule = IntentsTestRule(MainActivity::class.java, true, true)

    @Test
    fun btnTabLayoutAssertAddFragmentTabLayoutFragment(){
        activityTestRule.activity.findViewById<View>(R.id.btn_tab_layout).callOnClick()

        Thread.sleep(500)
        assertTrue(activityTestRule.activity.supportFragmentManager.fragments.find { it::class == TabLayoutFragment::class } != null)
    }
}