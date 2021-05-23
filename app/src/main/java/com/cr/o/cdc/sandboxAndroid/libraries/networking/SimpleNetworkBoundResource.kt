package com.cr.o.cdc.sandboxAndroid.libraries.networking

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

//https://github.com/android/architecture-components-samples/edit/master/GithubBrowserSample/app/src/main/java/com/android/example/github/repository/NetworkBoundResource.kt
abstract class SimpleNetworkBoundResource<ResultType>
@MainThread constructor(private val appExecutors: AppExecutors) {

    private val result = MediatorLiveData<NetworkResource<ResultType>>()

    init {
        result.value = NetworkResource.loading()
        fetchFromNetwork()
    }

    @MainThread
    private fun setValue(newValue: NetworkResource<ResultType>) {
        if (result.value != newValue) {
            result.postValue(newValue)
        }
    }

    private fun fetchFromNetwork() {
        val apiResponse = createCall()
        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            when (response) {
                is SuccessResponse -> {
                    appExecutors.diskIO().execute {
                        NetworkResource.success(response.data).let {
                            saveCallResult(processResponse(it))
                            setValue(it)
                        }
                    }
                }
                is ErrorResponse -> {
                    onFetchFailed()
                    setValue(NetworkResource.error())
                }
            }
        }
    }

    protected open fun onFetchFailed() {}

    fun asLiveData() = result as LiveData<NetworkResource<ResultType>>

    @WorkerThread
    protected open fun processResponse(resource: NetworkResource<ResultType>) = resource.data

    @WorkerThread
    protected abstract fun saveCallResult(item: ResultType?)

    @MainThread
    protected abstract fun createCall(): LiveData<NetworkResponse<ResultType>>
}
