package com.cr.o.cdc.sandboxAndroid.libraries.networking

import androidx.lifecycle.LiveData
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type
import java.util.concurrent.atomic.AtomicBoolean

//https://github.com/android/architecture-components-samples/blob/master/GithubBrowserSample/app/src/main/java/com/android/example/github/util/LiveDataCallAdapter.kt
class LiveDataCallAdapter<R>(private val responseType: Type) :
    CallAdapter<R, LiveData<NetworkResponse<R>>> {

    override fun responseType() = responseType

    override fun adapt(call: Call<R>): LiveData<NetworkResponse<R>> {
        return object : LiveData<NetworkResponse<R>>() {
            private var started = AtomicBoolean()
            override fun onActive() {
                super.onActive()
                if (started.compareAndSet(false, true)) {
                    call.enqueue(object : Callback<R> {
                        override fun onResponse(call: Call<R>, response: Response<R>) {
                            postValue(NetworkResponse.create(response))
                        }

                        override fun onFailure(call: Call<R>, throwable: Throwable) {
                            postValue(NetworkResponse.create(throwable))
                        }
                    })
                }
            }
        }
    }
}