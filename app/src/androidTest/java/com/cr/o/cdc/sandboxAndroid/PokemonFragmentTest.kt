package com.cr.o.cdc.sandboxAndroid

import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers
import com.cr.o.cdc.sandboxAndroid.fragments.PokemonFragment
import com.cr.o.cdc.sharedtest.getMessage
import junit.framework.TestCase.assertTrue
import org.junit.Test

class PokemonFragmentTest {


    @Test
    fun assertTxtNameSetValue() {
        launchFragmentInContainer<PokemonFragment>(themeResId = R.style.AppTheme,factory = object :
            FragmentFactory() {

            override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
                val fragment = super.instantiate(classLoader, className)

                return fragment
            }
        }).onFragment {
            it
        }

        onView(ViewMatchers.withId(R.id.txt_name)).check { view, _ ->
            assertTrue(getMessage("R.id.txt_name"), (view as TextView).text == "pokemon")
        }
    }
}