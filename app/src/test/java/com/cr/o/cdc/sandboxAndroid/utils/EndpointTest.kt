package com.cr.o.cdc.sandboxAndroid.utils

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import com.cr.o.cdc.sandboxAndroid.libraries.networking.NetworkResponse
import org.junit.Rule
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

abstract class EndpointTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    fun <T> getValue(liveData: LiveData<NetworkResponse<T>>): NetworkResponse<T>? {
        var response: NetworkResponse<T>? = null
        val latch = CountDownLatch(1)
        liveData.observeForever {
            when (it) {
                is com.cr.o.cdc.sandboxAndroid.libraries.networking.SuccessResponse -> {
                    response = it
                    latch.countDown()
                }
                is com.cr.o.cdc.sandboxAndroid.libraries.networking.ErrorResponse -> {
                    latch.countDown()
                    throw Exception("Error in request")
                }
            }
        }
        latch.await(20, TimeUnit.SECONDS)
        return response
    }
}
