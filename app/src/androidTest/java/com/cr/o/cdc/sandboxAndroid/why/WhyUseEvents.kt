package com.cr.o.cdc.sandboxAndroid.why

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.testing.launchFragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.cr.o.cdc.sandboxAndroid.coronavirus.di.CoronavirusModule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import junit.framework.TestCase.assertEquals
import org.junit.Test

@HiltAndroidTest
@UninstallModules(CoronavirusModule::class)
class WhyUseEvents {


    @Test
    fun whyInFragments() {
        val scenario = launchFragment<MainFragment>()

        scenario.onFragment {
            it.view?.callOnClick()
        }
        scenario.onFragment {
            it.view?.callOnClick()
        }
        scenario.onFragment {
            assertEquals(2, it.viewModel.changes)
        }

    }

    class MainFragment : Fragment() {

        lateinit var viewModel: DummyViewModel

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            viewModel = ViewModelProviders.of(this, null).get(DummyViewModel::class.java)

        }

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            val v = LinearLayout(requireContext()).apply {
                id = 1
                setOnClickListener {
                    replaceFragment(DummyFragment2())

                    setOnClickListener {
                        replaceFragment(DummyFragment1())
                    }
                }
            }

            replaceFragment(DummyFragment1())

            return v
        }

        private fun replaceFragment(fragment: Fragment) {
            childFragmentManager.beginTransaction().replace(1, fragment).commit()
        }
    }

    class DummyFragment2 : Fragment()

    class DummyFragment1 : Fragment() {

        private lateinit var viewModel: DummyViewModel

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            viewModel =
                ViewModelProviders.of(parentFragment!!, null).get(DummyViewModel::class.java)

        }

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? = View(requireContext())

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            viewModel.liveData.observe(viewLifecycleOwner, Observer {
                viewModel.incChange()
            })
        }
    }

    class DummyViewModel : ViewModel() {
        var changes = 0

        val liveData: LiveData<String> = MutableLiveData("value")

        fun incChange() {
            changes = changes.inc()
        }
    }
}