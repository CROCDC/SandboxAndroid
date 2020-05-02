package com.cr.o.cdc.sandboxAndroid

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class HomeFragmentTest {


    @Test
    fun assertNavigateToCoronavirusFragment() {
        val navController = mockk<NavController>(relaxed = true)
        launchFragmentInContainer<HomeFragment>().onFragment {
            Navigation.setViewNavController(it.view!!, navController)
        }

        Espresso.onView(ViewMatchers.withId(R.id.btn_coronavirus))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed())).perform(ViewActions.click())

        verify { navController.navigate(R.id.action_homeFragment_to_coronavirusFragment) }
    }

    @Test
    fun assertNavigateToRNCFragment() {
        val navController = mockk<NavController>(relaxed = true)
        launchFragmentInContainer<HomeFragment>().onFragment {
            Navigation.setViewNavController(it.view!!, navController)
        }

        Espresso.onView(ViewMatchers.withId(R.id.btn_rnc))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed())).perform(ViewActions.click())

        verify { navController.navigate(R.id.action_homeFragment_to_RNCFragment) }
    }

    @Test
    fun assertNavigateToNotificationsFragment() {
        val navController = mockk<NavController>(relaxed = true)
        launchFragmentInContainer<HomeFragment>().onFragment {
            Navigation.setViewNavController(it.view!!, navController)
        }

        Espresso.onView(ViewMatchers.withId(R.id.btn_notifications))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed())).perform(ViewActions.click())

        verify { navController.navigate(R.id.action_homeFragment_to_notificationsFragment) }
    }

    @Test
    fun assertNavigationToSitesListFragment() {
        val navController = mockk<NavController>(relaxed = true)
        launchFragmentInContainer<HomeFragment>().onFragment {
            Navigation.setViewNavController(it.view!!, navController)
        }

        Espresso.onView(ViewMatchers.withId(R.id.btn_down_detector))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed())).perform(ViewActions.click())

        verify { navController.navigate(R.id.action_homeFragment_to_sitesListFragment) }
    }
}