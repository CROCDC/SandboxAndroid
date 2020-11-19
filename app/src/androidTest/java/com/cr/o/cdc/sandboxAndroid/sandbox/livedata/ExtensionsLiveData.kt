package com.cr.o.cdc.sandboxAndroid.sandbox.livedata

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.switchMap
import com.cr.o.cdc.sandboxAndroid.setValueIfNotEquals
import com.cr.o.cdc.sharedtest.getCountOfChangesLiveData
import com.cr.o.cdc.sharedtest.getValueLiveDataValueCanBeNull
import junit.framework.TestCase.assertEquals
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

    @Test
    fun setValueIfNotEquals() {
        val liveData = MutableLiveData("Nada")

        assertEquals(getCountOfChangesLiveData(liveData, 1) {
            liveData.setValueIfNotEquals("Nada")
        }, 1)
    }

    @Test
    fun distinctUntilChanged() {
        val liveData = MutableLiveData("Nada")

        assertEquals(getCountOfChangesLiveData(liveData.distinctUntilChanged(), 1) {
            liveData.value = "Nada"
        }, 1)

    }

    @Test
    fun withoutDistinctUntilChanged() {
        val liveData = MutableLiveData("Nada")

        assertEquals(getCountOfChangesLiveData(liveData, 1) {
            liveData.value = "Nada"
        }, 2)

    }
}
