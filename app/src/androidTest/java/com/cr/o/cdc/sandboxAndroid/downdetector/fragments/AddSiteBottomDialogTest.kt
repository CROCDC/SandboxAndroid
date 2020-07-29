package com.cr.o.cdc.sandboxAndroid.downdetector.fragments

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.cr.o.cdc.sandboxAndroid.R
import com.cr.o.cdc.sandboxAndroid.coronavirus.di.CoronavirusModule
import com.cr.o.cdc.sandboxAndroid.utils.FragmentTest
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(CoronavirusModule::class)
class AddSiteBottomDialogTest : FragmentTest() {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun assertChangeNumberWhenMinimum15OfInputIntervalMinutes() {
        launchFragmentInHiltContainer<AddSiteBottomDialog>()

        val inputInterval = onView(withId(R.id.input_interval_check))
        inputInterval.perform(typeText("14"))
        onView(withId(R.id.input_address)).perform(typeText("www.palabras.com.ar"))

        inputInterval.check(matches(withText("15")))
    }
}
