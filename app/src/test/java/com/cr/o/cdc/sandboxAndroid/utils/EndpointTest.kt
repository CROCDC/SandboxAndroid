package com.cr.o.cdc.sandboxAndroid.utils

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import org.junit.Rule
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

abstract class EndpointTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Throws(InterruptedException::class)
    fun <T> getValue(liveData: LiveData<com.cr.o.cdc.networking.Response<T>>): com.cr.o.cdc.networking.Response<T> {
        var data: com.cr.o.cdc.networking.Response<T>? = null
        val latch = CountDownLatch(1)
        val ob = Observer<com.cr.o.cdc.networking.Response<T>> {
            when (it.status) {
                com.cr.o.cdc.networking.StatusResult.SUCCESS -> {
                    data = it
                    latch.countDown()
                }
                com.cr.o.cdc.networking.StatusResult.LOADING -> {
                }
                com.cr.o.cdc.networking.StatusResult.FAILURE -> {
                    latch.countDown()
                    throw Exception()
                }
                com.cr.o.cdc.networking.StatusResult.OFFLINE -> TODO()
            }
        }

        liveData.observeForever(ob)
        latch.await(40, TimeUnit.SECONDS)
        liveData.removeObserver(ob)

        return data!!
    }
}