package com.cr.o.cdc.sandboxAndroid.hiltbug

import androidx.appcompat.app.AppCompatActivity
import androidx.test.core.app.ActivityScenario
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Test

/**
 * Created by Cami on 5/8/21.
 */
@HiltAndroidTest
class HiltBugTest {

    lateinit var scenario: ActivityScenario<out AppCompatActivity>

    @Test
    fun test(){
        scenario = ActivityScenario.launch(Activity1::class.java)
    }
}