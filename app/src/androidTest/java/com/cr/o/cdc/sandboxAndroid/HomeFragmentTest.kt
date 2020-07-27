package com.cr.o.cdc.sandboxAndroid

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.cr.o.cdc.sandboxAndroid.utils.FakeNavController
import com.cr.o.cdc.sandboxAndroid.utils.FragmentTest
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import org.junit.Test

class HomeFragmentTest : FragmentTest() {
    @Test
    fun assertNavigateToCoronavirusFragment() {
        launchFragmentInContainer<HomeFragment>().onFragment {
            Navigation.setViewNavController(it.view!!, fakeNavController)
        }

        Espresso.onView(ViewMatchers.withId(R.id.btn_coronavirus))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed())).perform(ViewActions.click())

        assertEquals(
            R.id.action_homeFragment_to_coronavirusFragment,
            fakeNavController.destiny
        )
    }

    @Test
    fun assertNavigateToRNCFragment() {
        launchFragmentInContainer<HomeFragment>().onFragment {
            Navigation.setViewNavController(it.view!!, fakeNavController)
        }

        Espresso.onView(ViewMatchers.withId(R.id.btn_rnc))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed())).perform(ViewActions.click())

        assertEquals(
            R.id.action_homeFragment_to_RNCFragment,
            fakeNavController.destiny
        )
    }

    @Test
    fun assertNavigateToNotificationsFragment() {
        launchFragmentInContainer<HomeFragment>().onFragment {
            Navigation.setViewNavController(it.view!!, fakeNavController)
        }

        Espresso.onView(ViewMatchers.withId(R.id.btn_notifications))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed())).perform(ViewActions.click())

        assertEquals(
            R.id.action_homeFragment_to_notificationsFragment,
            fakeNavController.destiny
        )
    }

    @Test
    fun assertNavigationToSitesListFragment() {
        launchFragmentInContainer<HomeFragment>().onFragment {
            Navigation.setViewNavController(it.view!!, fakeNavController)
        }

        Espresso.onView(ViewMatchers.withId(R.id.btn_down_detector))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed())).perform(ViewActions.click())

        assertEquals(
            R.id.action_homeFragment_to_sitesListFragment,
            fakeNavController.destiny
        )
    }
}
