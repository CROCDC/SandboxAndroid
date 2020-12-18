package com.cr.o.cdc.sandboxAndroid.libraries.networking

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

//https://github.com/android/architecture-components-samples/edit/master/GithubBrowserSample/app/src/main/java/com/android/example/github/repository/NetworkBoundResource.kt
abstract class NetworkBoundResource<ResultType, RequestType>
@MainThread constructor(private val appExecutors: AppExecutors) {

    private val result = MediatorLiveData<NetworkResource<ResultType>>()

    init {
        result.value = NetworkResource.loading()
        @Suppress("LeakingThis")
        val dbSource = loadFromDb()
        result.addSource(dbSource) { data ->
            result.removeSource(dbSource)
            if (shouldFetch(data)) {
                fetchFromNetwork(dbSource, data)
            } else {
                result.addSource(dbSource) { newData ->
                    setValue(NetworkResource.success(newData))
                }
            }
        }
    }

    @MainThread
    private fun setValue(newValue: NetworkResource<ResultType>) {
        if (result.value != newValue) {
            result.value = newValue
        }
    }

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>, dbData: ResultType) {
        val apiResponse = createCall()
        setValue(NetworkResource.loading(dbData))
        result.addSource(dbSource) { newData ->
            setValue(NetworkResource.loading(newData))
        }
        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            result.removeSource(dbSource)
            when (response) {
                is SuccessResponse -> {
                    appExecutors.diskIO().execute {
                        saveCallResult(processResponse(NetworkResource.success(response.data)))
                        appExecutors.mainThread().execute {
                            result.addSource(loadFromDb()) { newData ->
                                setValue(NetworkResource.success(newData))
                            }
                        }
                    }
                }
                is ErrorResponse -> {
                    onFetchFailed()
                    result.addSource(dbSource) {
                        setValue(NetworkResource.error())
                    }
                }
            }
        }
    }

    protected open fun onFetchFailed() {}

    fun asLiveData() = result as LiveData<NetworkResource<ResultType>>

    @WorkerThread
    protected open fun processResponse(resource: NetworkResource<RequestType>) = resource.data

    @WorkerThread
    protected abstract fun saveCallResult(item: RequestType?)

    @MainThread
    protected abstract fun shouldFetch(data: ResultType?): Boolean

    @MainThread
    protected abstract fun loadFromDb(): LiveData<ResultType>

    @MainThread
    protected abstract fun createCall(): LiveData<NetworkResponse<RequestType>>
}