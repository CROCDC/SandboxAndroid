package com.cr.o.cdc.sandboxAndroid.network

import androidx.lifecycle.LiveData
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.IOException
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class ApolloManager @Inject constructor(
    private val appExecutors: AppExecutors
) {

    fun <T> request(request: ApolloCall<T>): LiveData<NetworkResponse<T>> =
        object : LiveData<NetworkResponse<T>>() {

            val started = AtomicBoolean(false)

            override fun onActive() {

                super.onActive()
                if (started.compareAndSet(false, true)) {
                    appExecutors.networkIO().execute {
                        var response: NetworkResponse<T>? = null
                        try {
                            runBlocking {
                                launch {
                                    response = NetworkResponse(200, request.execute().data())
                                }
                            }
                        } catch (e: IOException) {
                            response = NetworkResponse(408, null)
                        }

                        postValue(response)
                    }
                }
            }
        }
}

suspend fun <T> ApolloCall<T>.execute() = suspendCoroutine<Response<T>> { cont ->
    enqueue(object : ApolloCall.Callback<T>() {
        override fun onResponse(response: Response<T>) {
            cont.resume(response)
        }

        override fun onFailure(e: ApolloException) {
            cont.resumeWithException(e)
        }
    })
}