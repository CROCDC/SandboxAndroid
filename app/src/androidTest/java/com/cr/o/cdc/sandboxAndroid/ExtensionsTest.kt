package com.cr.o.cdc.sandboxAndroid

import android.content.Context
import android.text.SpannableString
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.test.platform.app.InstrumentationRegistry
import com.cr.o.cdc.sharedtest.getCountOfChangesLiveData
import junit.framework.TestCase.assertEquals
import org.junit.Rule
import org.junit.Test

class ExtensionsTest {

    private val seconds: Long = 1

    private val mutableLiveDataNotNull = MutableLiveData("hola")
    private val mutableLiveDataNull = MutableLiveData<String>(null)

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun mapGetValueIfNotEquals_with_value_not_null() {
        assertEquals(1, getCountOfChangesLiveData(mutableLiveDataNotNull.mapGetValueIfNotEquals {
            "$it camilo"
        }, seconds) {
            mutableLiveDataNotNull.value = "hola"
            mutableLiveDataNotNull.value = "hola"
        })
    }

    @Test
    fun mapGetValueIfNotEquals_with_value_null() {
        assertEquals(1, getCountOfChangesLiveData(mutableLiveDataNull.mapGetValueIfNotEquals {
            "$it camilo"
        }, seconds) {
            mutableLiveDataNull.value = null
            mutableLiveDataNull.value = null
        })
    }

    @Test
    fun map_with_value_not_null() {
        assertEquals(3, getCountOfChangesLiveData(mutableLiveDataNotNull.map {
            "$it camilo"
        }, seconds) {
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
            }, seconds) {
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
            }, seconds) {
                mutableLiveDataNotNull.value = "hola"
                mutableLiveDataNotNull.value = "hola"
            })
    }

    @Test
    fun mapGetValueIfNotEqualsAndNotNull_with_value_not_null_and_null() {
        assertEquals(
            1,
            getCountOfChangesLiveData(mutableLiveDataNotNull.mapGetValueIfNotEqualsAndNotNull {
                "$it camilo"
            }, seconds) {
                mutableLiveDataNotNull.value = "hola"
                mutableLiveDataNotNull.value = null
            })
    }

    @Test
    fun mapGetValueIfNotEqualsAndNotNull_with_value_not_null_and_null_and_not_null_same_values() {
        assertEquals(
            1,
            getCountOfChangesLiveData(mutableLiveDataNotNull.mapGetValueIfNotEqualsAndNotNull {
                "$it camilo"
            }, seconds) {
                mutableLiveDataNotNull.value = "hola"
                mutableLiveDataNotNull.value = null
                mutableLiveDataNotNull.value = "hola"
            })
    }

    @Test
    fun mapGetValueIfNotEqualsAndNotNull_with_value_not_null_and_null_and_not_null_n_same_values() {
        assertEquals(
            2,
            getCountOfChangesLiveData(mutableLiveDataNotNull.mapGetValueIfNotEqualsAndNotNull {
                "$it camilo"
            }, seconds) {
                mutableLiveDataNotNull.value = "hola"
                mutableLiveDataNotNull.value = null
                mutableLiveDataNotNull.value = "hola 2"
            })
    }
}
