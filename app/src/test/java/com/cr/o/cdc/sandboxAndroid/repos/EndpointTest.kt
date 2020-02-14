package com.cr.o.cdc.sandboxAndroid.repos

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import com.cr.o.cdc.requests.AppExecutors
import com.cr.o.cdc.requests.Client
import com.cr.o.cdc.requestsmodule.Response
import com.cr.o.cdc.requestsmodule.StatusResult
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

    fun getManager() = Client()
    private fun getAppExecutors() = AppExecutors()

    @Throws(InterruptedException::class)
    fun <T> getValue(liveData: LiveData<com.cr.o.cdc.requestsmodule.Response<T>>): com.cr.o.cdc.requestsmodule.Response<T> {
        var data: com.cr.o.cdc.requestsmodule.Response<T>? = null
        val latch = CountDownLatch(1)
        liveData.observeForever { o ->
            when (o.status) {
                com.cr.o.cdc.requestsmodule.StatusResult.SUCCESS -> {
                    data = o
                    latch.countDown()
                }
                com.cr.o.cdc.requestsmodule.StatusResult.LOADING -> {
                }
                com.cr.o.cdc.requestsmodule.StatusResult.FAILURE -> TODO()
                com.cr.o.cdc.requestsmodule.StatusResult.OFFLINE -> TODO()
            }
        }
        latch.await(40, TimeUnit.SECONDS)

        return data!!
    }
}