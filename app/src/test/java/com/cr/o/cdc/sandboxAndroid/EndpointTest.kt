package com.cr.o.cdc.sandboxAndroid

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import com.cr.o.cdc.requests.AppExecutors
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

    fun getManager() = Manager()
    private fun getAppExecutors() = AppExecutors()

    @Throws(InterruptedException::class)
    fun <T> getValue(liveData: LiveData<T>): T {
        var data: T? = null
        val latch = CountDownLatch(1)
        liveData.observeForever { o ->
            if (o != null) {
                data = o
                latch.countDown()
            }
        }
        latch.await(40, TimeUnit.SECONDS)

        return data!!
    }
}