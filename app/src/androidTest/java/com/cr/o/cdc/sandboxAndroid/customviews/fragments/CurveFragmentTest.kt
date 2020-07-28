package com.cr.o.cdc.sandboxAndroid.customviews.fragments

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.cr.o.cdc.sandboxAndroid.R
import com.cr.o.cdc.sandboxAndroid.coronavirus.di.CoronavirusModule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Test

@HiltAndroidTest
@UninstallModules(CoronavirusModule::class)
class CurveFragmentTest {

    @Test
    fun displayCurve() {
        launchFragmentInContainer<CurveFragment>()

        onView(withId(R.id.curve)).check(matches(isDisplayed()))
    }
}
