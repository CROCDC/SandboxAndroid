package com.cr.o.cdc.sandboxAndroid.customviews.fragments

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.cr.o.cdc.sandboxAndroid.R
import com.cr.o.cdc.sandboxAndroid.coronavirus.di.CoronavirusModule
import com.cr.o.cdc.sandboxAndroid.utils.FragmentTest
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import junit.framework.TestCase.assertEquals
import org.junit.Test

@HiltAndroidTest
@UninstallModules(CoronavirusModule::class)
class CustomViewFragmentTest : FragmentTest() {

    @Test
    fun assertNavigateToCurveFragment() {
        launchFragmentInContainer<CustomViewsFragment>().onFragment {
            Navigation.setViewNavController(it.view!!, fakeNavController)
        }

        Espresso.onView(ViewMatchers.withId(R.id.btn_curve))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed())).perform(click())

        assertEquals(
            R.id.action_customViewsFragment_to_curveFragment,
            fakeNavController.destiny
        )
    }
}
