package com.cr.o.cdc.sandboxAndroid.hiltbug

import androidx.appcompat.app.AppCompatActivity
import androidx.test.core.app.ActivityScenario
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


/**
 * Created by Cami on 5/8/21.
 */
@HiltAndroidTest
class HiltBugTest {

    @Rule
    var hiltRule = HiltAndroidRule(this)

    lateinit var scenario: ActivityScenario<out AppCompatActivity>

    @Before
    fun before(){
        hiltRule.inject()
    }

    @Test
    fun test(){
        scenario = ActivityScenario.launch(Activity1::class.java)
    }
}