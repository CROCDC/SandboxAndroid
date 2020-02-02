package com.cr.o.cdc.sharedtest

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

fun Any.getPrivateField(fieldName: String): Any? = javaClass.getDeclaredField(fieldName).let {
    it.isAccessible = true
    return@let it.get(this)
}

fun Context.sharedPreferences() =
    getSharedPreferences("${this.packageName}_test", Context.MODE_PRIVATE).also {
        it.edit().clear().apply()
    }

@Throws(InterruptedException::class)
fun <T> getValueLivedata(
    liveData: LiveData<T>,
    seconds: Long,
    executeFunWhenObserve: () -> Unit
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
    Thread.sleep(400)
    executeFunWhenObserve.invoke()
    latch.await(seconds, TimeUnit.SECONDS)

    return data
}

fun getMessage(id: String) = "la view con el id $id no tenia el texto esperado"