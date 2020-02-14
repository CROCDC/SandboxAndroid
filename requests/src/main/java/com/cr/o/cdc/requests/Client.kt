package com.cr.o.cdc.requests

import androidx.lifecycle.LiveData
import com.cr.o.cdc.requestsmodule.RequestInterface
import com.cr.o.cdc.requestsmodule.Response
import okhttp3.OkHttpClient
import java.io.IOException
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicBoolean

class Client {

    private val appExecutors = AppExecutors()

    private val httpclient = OkHttpClient.Builder()
        .connectTimeout(15, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .followRedirects(false).build()

    //todo handle !!
    fun <T> request(request: RequestInterface) =
        object : LiveData<Response<T>>() {

            val started = AtomicBoolean()

            override fun onActive() {

                super.onActive()
                if (started.compareAndSet(false, true)) {
                    appExecutors.networkIO().execute {
                        var body: String?
                        var httpCode: Int
                        try {
                            with(httpclient.newCall(request.getRequest()).execute()) {
                                body = this.body?.string()
                                httpCode = this.code
                            }

                        } catch (e: IOException) {
                            body = "CONNECTION_TIMEOUT"
                            httpCode = 408
                        }

                        postValue(
                            Response(
                                null, httpCode
                            )
                        )
                    }
                }
            }
        }
}