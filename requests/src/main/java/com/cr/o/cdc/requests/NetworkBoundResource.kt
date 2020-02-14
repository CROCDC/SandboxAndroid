package com.cr.o.cdc.requests

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

//https://github.com/android/architecture-components-samples/edit/master/GithubBrowserSample/app/src/main/java/com/android/example/github/repository/NetworkBoundResource.kt
abstract class NetworkBoundResource<ResultType, RequestType>
@MainThread constructor(private val appExecutors: AppExecutors) {

    private val result = MediatorLiveData<Resource<ResultType>>()

    init {
        result.value = Resource.loading()
        @Suppress("LeakingThis")
        val dbSource = loadFromDb()
        result.addSource(dbSource) { data ->
            result.removeSource(dbSource)
            if (shouldFetch(data)) {
                fetchFromNetwork(dbSource, data)
            } else {
                result.addSource(dbSource) { newData ->
                    setValue(Resource.success(newData))
                }
            }
        }
    }

    @MainThread
    private fun setValue(newValue: Resource<ResultType>) {
        if (result.value != newValue) {
            result.value = newValue
        }
    }

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>, dbData: ResultType) {
        val apiResponse = createCall()
        setValue(Resource.loading(dbData))
        result.addSource(dbSource) { newData ->
            setValue(Resource.loading(newData))
        }
        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            result.removeSource(dbSource)
            when (response.status) {

                StatusResult.SUCCESS -> {
                    appExecutors.diskIO().execute {
                        saveCallResult(processResponse(Resource.success(response.data)))
                        appExecutors.mainThread().execute {
                            result.addSource(loadFromDb()) { newData ->
                                setValue(Resource.success(newData))
                            }
                        }
                    }
                }
                StatusResult.FAILURE, StatusResult.OFFLINE -> {
                    onFetchFailed()
                    result.addSource(dbSource) {
                        setValue(Resource.error())
                    }
                }
            }
        }

    }

    protected open fun onFetchFailed() {}

    fun asLiveData() = result as LiveData<Resource<ResultType>>

    @WorkerThread
    protected open fun processResponse(resource: Resource<RequestType>) = resource.data

    @WorkerThread
    protected abstract fun saveCallResult(item: RequestType?)

    @MainThread
    protected abstract fun shouldFetch(data: ResultType?): Boolean

    @MainThread
    protected abstract fun loadFromDb(): LiveData<ResultType>

    @MainThread
    protected abstract fun createCall(): LiveData<Resource<RequestType>>
}