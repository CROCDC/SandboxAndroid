package com.cr.o.cdc.sandboxAndroid

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers
import com.cr.o.cdc.sandboxAndroid.fragments.TabLayoutFragment
import com.google.android.material.tabs.TabLayout
import junit.framework.TestCase.assertTrue
import org.junit.Test

class TabLayoutFragmentTest {

    @Test
    fun assertIfTabLayoutHaveTabsIsUnSelected() {
        launchFragmentInContainer<TabLayoutFragment>(themeResId = R.style.AppTheme)

        onView(ViewMatchers.withId(R.id.tabLayout)).check { view, _ ->
            val tabLayout = view as TabLayout
            for (i in 0..tabLayout.tabCount) {
                assertTrue(!(tabLayout.getTabAt(i)?.isSelected ?: false))
            }
        }
    }
}