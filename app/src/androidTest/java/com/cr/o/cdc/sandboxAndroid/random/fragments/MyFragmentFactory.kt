package com.cr.o.cdc.sandboxAndroid.random.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cr.o.cdc.sharedtest.modifyValue
import org.mockito.Mockito.mock

class MyFragmentFactory<T> : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        val f = super.instantiate(classLoader, className)

        f.modifyValue("viewModelFactory", object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>) = mock(modelClass)
        })

        return f
    }
}