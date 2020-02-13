package com.cr.o.cdc.sandboxAndroid.random.fragments

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers
import com.cr.o.cdc.sandboxAndroid.R
import com.google.android.material.tabs.TabLayout
import junit.framework.TestCase.assertTrue
import org.junit.Test

class TabLayoutFragmentTest : FragmentTest() {

    @Test
    fun assertIfTabLayoutHaveTabsIsUnSelected() {
        launchFragmentInContainer<TabLayoutFragment>()

        onView(ViewMatchers.withId(R.id.tabLayout)).check { view, _ ->
            val tabLayout = view as TabLayout
            for (i in 0..tabLayout.tabCount) {
                assertTrue(!(tabLayout.getTabAt(i)?.isSelected ?: false))
            }
        }
    }
}