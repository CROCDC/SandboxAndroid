package com.cr.o.cdc.requests

import androidx.lifecycle.LiveData
import okhttp3.OkHttpClient
import java.io.IOException
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicBoolean
class Manager {

    private val appExecutors = AppExecutors()

    private val httpclient = OkHttpClient.Builder()
        .connectTimeout(15, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .followRedirects(false).build()

    fun <T> request(request: RequestInterface): LiveData<NetworkResponse<T>> =
        object : LiveData<NetworkResponse<T>>() {

            val started = AtomicBoolean(false)

            override fun onActive() {

                super.onActive()
                if (started.compareAndSet(false, true)) {
                    appExecutors.networkIO().execute {

                        var httpCode: Int? = null
                        var body: String? = null

                        try {
                            val response = httpclient.newCall(request.getRequest()).execute()

                            //httpCode = response.code()

                            //val responseBody = response.body()

                        } catch (e: IOException) {
                            httpCode = 408
                            body = "CONNECTION_TIMEOUT"
                        }


                        //postValue(NetworkResponse(httpCode,))
                    }
                }
            }
        }
}