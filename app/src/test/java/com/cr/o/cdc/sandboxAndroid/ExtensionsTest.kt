package com.cr.o.cdc.sandboxAndroid

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.cr.o.cdc.sharedtest.getCountOfChangesLiveData
import junit.framework.TestCase.assertEquals
import org.junit.Rule
import org.junit.Test

class ExtensionsTest {

    private val mutableLiveDataNotNull = MutableLiveData("hola")
    private val mutableLiveDataNull = MutableLiveData<String>(null)

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun mapGetValueIfNotEquals_with_value_not_null() {
        assertEquals(1, getCountOfChangesLiveData(mutableLiveDataNotNull.mapGetValueIfNotEquals {
            "$it camilo"
        }, 5) {
            mutableLiveDataNotNull.value = "hola"
            mutableLiveDataNotNull.value = "hola"
        })
    }

    @Test
    fun mapGetValueIfNotEquals__with_value_null() {
        assertEquals(1, getCountOfChangesLiveData(mutableLiveDataNull.mapGetValueIfNotEquals {
            "$it camilo"
        }, 5) {
            mutableLiveDataNull.value = null
            mutableLiveDataNull.value = null
        })
    }

    @Test
    fun map_with_value_not_null() {
        assertEquals(3, getCountOfChangesLiveData(mutableLiveDataNotNull.map {
            "$it camilo"
        }, 5) {
            mutableLiveDataNotNull.value = "hola"
            mutableLiveDataNotNull.value = "hola"
        })
    }

    @Test
    fun mapGetValueIfNotEqualsAndNotNull__with_value_null() {
        assertEquals(
            0,
            getCountOfChangesLiveData(mutableLiveDataNull.mapGetValueIfNotEqualsAndNotNull {
                "$it camilo"
            }, 5) {
                mutableLiveDataNull.value = null
                mutableLiveDataNull.value = null
            })
    }

    @Test
    fun mapGetValueIfNotEqualsAndNotNull_with_value_not_null() {
        assertEquals(
            1,
            getCountOfChangesLiveData(mutableLiveDataNotNull.mapGetValueIfNotEqualsAndNotNull {
                "$it camilo"
            }, 5) {
                mutableLiveDataNotNull.value = "hola"
                mutableLiveDataNotNull.value = "hola"
            })
    }

}