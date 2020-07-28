package com.cr.o.cdc.sandboxAndroid.coronavirus.fragments

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.cr.o.cdc.sandboxAndroid.R
import com.cr.o.cdc.sandboxAndroid.coronavirus.di.CoronavirusModule
import com.cr.o.cdc.sandboxAndroid.utils.FragmentTest
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(CoronavirusModule::class)
class CoronavirusFragmentTest : FragmentTest() {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun testRecyclerView() {
        launchFragmentInHiltContainer<CoronavirusFragment>()

        Thread.sleep(300)

        onView(withId(R.id.recycler)).check { view, _ ->
            assertTrue((view as RecyclerView).adapter?.itemCount != 0)
        }
    }
}
