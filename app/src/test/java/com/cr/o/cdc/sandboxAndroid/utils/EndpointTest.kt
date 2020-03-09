package com.cr.o.cdc.sandboxAndroid.utils

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
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
        liveData.observeForever { o ->
            when (o.status) {
                StatusResult.SUCCESS -> {
                    data = o
                    latch.countDown()
                }
                StatusResult.LOADING -> {
                }
                StatusResult.FAILURE -> {
                    println((o.debugInfo as GraphQlDebugInfo))
                    latch.countDown()
                    throw Exception()
                }
                StatusResult.OFFLINE -> TODO()
            }
        }
        latch.await(40, TimeUnit.SECONDS)

        return data!!
    }
}