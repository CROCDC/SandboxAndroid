package com.cr.o.cdc.sandboxAndroid

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import com.cr.o.cdc.sandboxAndroid.network.ApolloManager
import com.cr.o.cdc.sandboxAndroid.network.AppExecutors
import com.cr.o.cdc.sandboxAndroid.network.NetworkResponse
import com.cr.o.cdc.sandboxAndroid.network.StatusResult
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

    fun getApolloManager() = ApolloManager(getAppExecutors())
    private fun getAppExecutors() = AppExecutors()

    @Throws(InterruptedException::class)
    fun <T> getValue(liveData: LiveData<NetworkResponse<T>>): NetworkResponse<T> {
        var data: NetworkResponse<T>? = null
        val latch = CountDownLatch(1)
        liveData.observeForever { o ->
            when (o.result) {
                StatusResult.SUCCESS -> {
                    data = o
                    latch.countDown()
                }
                StatusResult.FAILURE, StatusResult.OFFLINE -> {
                    throw Exception()
                }
            }

        }
        latch.await(40, TimeUnit.SECONDS)

        return data!!
    }
}