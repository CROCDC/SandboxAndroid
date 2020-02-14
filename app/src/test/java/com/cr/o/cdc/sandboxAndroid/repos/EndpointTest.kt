package com.cr.o.cdc.sandboxAndroid.repos

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import com.cr.o.cdc.requests.AppExecutors
import com.cr.o.cdc.requests.Client
import com.cr.o.cdc.requests.Response
import com.cr.o.cdc.requests.StatusResult
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
                StatusResult.FAILURE -> TODO()
                StatusResult.OFFLINE -> TODO()
            }
        }
        latch.await(40, TimeUnit.SECONDS)

        return data!!
    }
}