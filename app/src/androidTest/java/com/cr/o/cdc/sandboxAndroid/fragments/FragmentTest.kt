package com.cr.o.cdc.sandboxAndroid.fragments

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.test.platform.app.InstrumentationRegistry
import com.cr.o.cdc.requests.Response
import com.cr.o.cdc.sandboxAndroid.R
import com.cr.o.cdc.sandboxAndroid.model.Pokemon
import com.cr.o.cdc.sandboxAndroid.push.PushToken
import com.cr.o.cdc.sharedtest.makeRandomInstance
import com.cr.o.cdc.sharedtest.modifyValue
import com.cr.o.cdc.sharedtest.sharedPreferences

@Suppress("UNCHECKED_CAST")
abstract class FragmentTest {

    private val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

    inline fun <reified F : Fragment> launchFragmentInContainer(vms: List<ViewModel> = listOf()) =
        launchFragmentInContainer<F>(
            themeResId = R.style.AppTheme,
            factory = object :
                FragmentFactory() {
                override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
                    return super.instantiate(classLoader, className).also {
                        it.modifyValue("viewModelFactory", object : ViewModelProvider.Factory {
                            override fun <T : ViewModel> create(modelClass: Class<T>) =
                                vms.find { it.javaClass.name == modelClass.name } as T
                        })
                    }
                }
            })


    fun getPushToken() = PushToken(context.sharedPreferences())

    fun getPokemon() = Pokemon::class.makeRandomInstance<Pokemon>()
}
