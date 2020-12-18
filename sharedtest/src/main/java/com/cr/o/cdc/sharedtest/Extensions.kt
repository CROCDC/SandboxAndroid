@file:Suppress("UNCHECKED_CAST")

package com.cr.o.cdc.sharedtest

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

fun Context.sharedPreferences(): SharedPreferences =
    getSharedPreferences(packageName, Context.MODE_PRIVATE).also {
        it.edit().clear().apply()
    }

@Throws(InterruptedException::class)
fun <T> getValueLiveData(
    liveData: LiveData<T>,
    seconds: Long,
    executeFunWhenObserve: () -> Unit? = {}
): T? {
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(o: T?) {
            if (o != null) {
                data = o
                latch.countDown()
                liveData.removeObserver(this)
            }
        }
    }
    liveData.observeForever(observer)
    executeFunWhenObserve.invoke()
    latch.await(seconds, TimeUnit.SECONDS)

    return data
}


@Throws(InterruptedException::class)
fun <T> getValueLiveDataValueCanBeNull(
    liveData: LiveData<T>,
    seconds: Long,
    executeFunWhenObserve: () -> Unit? = {}
): T? {
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(o: T) {
            data = o
            latch.countDown()
            liveData.removeObserver(this)

        }
    }
    liveData.observeForever(observer)
    executeFunWhenObserve.invoke()
    latch.await(seconds, TimeUnit.SECONDS)
    return data
}

fun getMessage(id: String) = "la view con el id $id no tenia el texto esperado"

@Throws(InterruptedException::class)
fun <T> getCountOfChangesLiveData(
    liveData: LiveData<T>,
    seconds: Long,
    executeFunWhenObserve: (() -> Unit)? = null

): Int {
    var count = 0
    val latch = CountDownLatch(1)
    liveData.observeForever {
        count += 1
    }

    Thread.sleep(400)
    executeFunWhenObserve?.invoke()
    latch.await(seconds, TimeUnit.SECONDS)
    return count

}
