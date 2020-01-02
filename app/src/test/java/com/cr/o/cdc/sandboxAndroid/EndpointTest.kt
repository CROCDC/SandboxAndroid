package com.cr.o.cdc.sandboxAndroid

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import com.cr.o.cdc.requests.Manager
import org.junit.Rule
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

/**
 * Created by Camilo on 31/12/19.
 */

abstract class EndpointTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    fun getApolloManager() = Manager()
    private fun getAppExecutors() = com.cr.o.cdc.requests.AppExecutors()

    @Throws(InterruptedException::class)
    fun <T> getValue(liveData: LiveData<com.cr.o.cdc.requests.NetworkResponse<T>>): com.cr.o.cdc.requests.NetworkResponse<T> {
        var data: com.cr.o.cdc.requests.NetworkResponse<T>? = null
        val latch = CountDownLatch(1)
        liveData.observeForever { o ->
            when (o.result) {
                com.cr.o.cdc.requests.StatusResult.SUCCESS -> {
                    data = o
                    latch.countDown()
                }
                com.cr.o.cdc.requests.StatusResult.FAILURE, com.cr.o.cdc.requests.StatusResult.OFFLINE -> {
                    throw Exception()
                }
            }

        }
        latch.await(40, TimeUnit.SECONDS)

        return data!!
    }
}