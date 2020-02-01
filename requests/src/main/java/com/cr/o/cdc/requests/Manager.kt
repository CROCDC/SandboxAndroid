package com.cr.o.cdc.requests

import androidx.lifecycle.LiveData
import com.cr.o.cdc.requestsannotations.RequestInterface
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

    fun <T> request(request: RequestInterface<T>): LiveData<T> =
        object : LiveData<T>() {

            val started = AtomicBoolean(false)

            override fun onActive() {

                super.onActive()
                if (started.compareAndSet(false, true)) {
                    appExecutors.networkIO().execute {

                        var httpCode: Int?
                        var body: String?

                        try {
                            val response = httpclient.newCall(request.getRequest()).execute()

                            httpCode = response.code

                            body = response.body?.string()

                        } catch (e: IOException) {
                            httpCode = 408
                            body = "CONNECTION_TIMEOUT"
                        }

                        postValue(request.parse(body!!))
                    }
                }
            }
        }
}