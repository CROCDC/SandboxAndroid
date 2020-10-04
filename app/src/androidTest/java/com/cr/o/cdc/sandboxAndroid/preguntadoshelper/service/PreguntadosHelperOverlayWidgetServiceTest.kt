package com.cr.o.cdc.sandboxAndroid.preguntadoshelper.service

import android.content.Context
import android.content.Intent
import android.os.IBinder
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread
import androidx.test.rule.ServiceTestRule
import com.cr.o.cdc.sandboxAndroid.MainActivity
import com.cr.o.cdc.sandboxAndroid.coronavirus.di.CoronavirusModule
import com.cr.o.cdc.sandboxAndroid.preguntadoshelper.fake.MockFactoryPreguntadosHelper
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(CoronavirusModule::class)
class PreguntadosHelperOverlayWidgetServiceTest {

    @get:Rule
    val serviceRule = ServiceTestRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun assertAnswer() {
        val service = startService()
        ActivityScenario.launch(MainActivity::class.java).onActivity {
            runOnUiThread {
                service.setAnswer(MockFactoryPreguntadosHelper.getAnswer())
            }
        }
    }

    private fun startService(): PreguntadosHelperOverlayWidgetService {
        val binder: IBinder = serviceRule.bindService(
            Intent(
                ApplicationProvider.getApplicationContext<Context>(),
                PreguntadosHelperOverlayWidgetService::class.java
            )
        )
        return (binder as PreguntadosHelperOverlayWidgetService.PreguntadosHelperOverlayWidgetBinder).getService()
    }
}
