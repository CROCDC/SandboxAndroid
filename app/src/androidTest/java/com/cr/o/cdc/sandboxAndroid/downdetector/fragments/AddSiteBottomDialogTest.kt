package com.cr.o.cdc.sandboxAndroid.downdetector.fragments

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.cr.o.cdc.sandboxAndroid.R
import org.junit.Test

class AddSiteBottomDialogTest {

    @Test
    fun assertChangeNumberWhenMinimum15OfInputIntervalMinutes() {
        launchFragmentInContainer<AddSiteBottomDialog>()

        val inputInterval = onView(withId(R.id.input_interval_check))
        inputInterval.perform(typeText("14"))
        onView(withId(R.id.input_address)).perform(typeText("www.palabras.com.ar"))

        inputInterval.check(matches(withText("15")))
    }
}
