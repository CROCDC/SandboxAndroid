package com.cr.o.cdc.sandboxAndroid.customviews.fragments

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.cr.o.cdc.sandboxAndroid.R
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class CustomViewFragmentTest {

    @Test
    fun assertNavigateToCurveFragment() {
        val navController = mockk<NavController>(relaxed = true)
        launchFragmentInContainer<CustomViewsFragment>().onFragment {
            Navigation.setViewNavController(it.view!!, navController)
        }

        Espresso.onView(ViewMatchers.withId(R.id.btn_curve))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed())).perform(click())

        verify { navController.navigate(R.id.action_customViewsFragment_to_curveFragment) }
    }

}