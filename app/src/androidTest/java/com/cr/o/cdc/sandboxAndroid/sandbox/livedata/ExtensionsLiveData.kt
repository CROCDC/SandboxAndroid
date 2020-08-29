package com.cr.o.cdc.sandboxAndroid.sandbox.livedata

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.cr.o.cdc.sharedtest.getValueLiveDataValueCanBeNull
import junit.framework.TestCase.assertTrue
import org.junit.Rule
import org.junit.Test

class ExtensionsLiveData {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun switchMap() {
        val liveData = MutableLiveData("Nada")

        val value = liveData.switchMap {
            MutableLiveData(it)
        }

        assertTrue(getValueLiveDataValueCanBeNull(value, 1) == liveData.value)
    }
}
