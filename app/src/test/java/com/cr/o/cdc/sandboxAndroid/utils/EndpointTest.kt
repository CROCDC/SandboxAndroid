package com.cr.o.cdc.sandboxAndroid.utils

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.cr.o.cdc.requestsmodule.GraphQlDebugInfo
import com.cr.o.cdc.requestsmodule.Response
import com.cr.o.cdc.requestsmodule.StatusResult
import org.junit.Rule
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

abstract class EndpointTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Throws(InterruptedException::class)
    fun <T> getValue(liveData: LiveData<Response<T>>): Response<T> {
        var data: Response<T>? = null
        val latch = CountDownLatch(1)
        val ob = Observer<Response<T>> {
            when (it.status) {
                StatusResult.SUCCESS -> {
                    data = it
                    latch.countDown()
                }
                StatusResult.LOADING -> {
                }
                StatusResult.FAILURE -> {
                    println((it.debugInfo as GraphQlDebugInfo))
                    latch.countDown()
                    throw Exception()
                }
                StatusResult.OFFLINE -> TODO()
            }
        }

        liveData.observeForever(ob)
        latch.await(40, TimeUnit.SECONDS)
        liveData.removeObserver(ob)

        return data!!
    }
}