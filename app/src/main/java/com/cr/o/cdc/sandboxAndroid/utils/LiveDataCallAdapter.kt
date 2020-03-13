package com.cr.o.cdc.sandboxAndroid.utils

import androidx.lifecycle.LiveData
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import java.lang.reflect.Type
import java.util.concurrent.atomic.AtomicBoolean
import retrofit2.Response as Re

//https://github.com/android/architecture-components-samples/blob/master/GithubBrowserSample/app/src/main/java/com/android/example/github/util/LiveDataCallAdapter.kt
class LiveDataCallAdapter<R>(private val responseType: Type) :
    CallAdapter<R, LiveData<Response<R>>> {

    override fun responseType() = responseType

    override fun adapt(call: Call<R>): LiveData<Response<R>> {
        return object : LiveData<Response<R>>() {
            private var started = AtomicBoolean()
            override fun onActive() {
                super.onActive()
                if (started.compareAndSet(false, true)) {
                    call.enqueue(object : Callback<R> {
                        override fun onResponse(call: Call<R>, response: Re<R>) {
                            postValue(
                                Response(
                                    response.body(),
                                    response.code(),
                                    DebugInfo(call.request().url.toString())
                                )
                            )
                        }

                        override fun onFailure(call: Call<R>, throwable: Throwable) {
                            postValue(
                                Response(
                                    null,
                                    100,
                                    DebugInfo(call.request().url.toString())
                                )
                            )
                        }
                    })
                }
            }
        }
    }
}