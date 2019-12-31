package com.cr.o.cdc.sandboxAndroid.network

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import ar.com.cdeamerica.tupi.network.NetworkResource

abstract class NetworkBoundResource<ResultType, RequestType>
@MainThread constructor(private val appExecutors: AppExecutors) {

    private val result = MediatorLiveData<NetworkResource<ResultType>>()

    init {
        result.value = NetworkResource.Loading()
        val dbSource = this.loadFromDb()
        result.addSource(dbSource) { data ->
            result.removeSource(dbSource)
            if (shouldFetch(data)) {
                fetchFromNetwork(dbSource)
            } else {
                result.addSource(dbSource) { newData ->
                    setValue(NetworkResource.Success(newData))
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

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
        val apiResponse = createCall()

        result.addSource(dbSource) { newData ->
            setValue(NetworkResource.Loading(newData))
        }
        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            result.removeSource(dbSource)

            if (response.isSuccess) {
                appExecutors.diskIO().execute {
                    response.data?.let { saveCallResult(it) }
                    appExecutors.mainThread().execute {
                        result.addSource(loadFromDb()) { newData ->
                            setValue(NetworkResource.Success(newData))
                        }
                    }
                }
            } else {
                onFetchFailed()

                result.addSource(dbSource) { newData ->
                    result.setValue(
                        if (response.result === StatusResult.OFFLINE) {
                            NetworkResource.Offline()
                        } else {
                            NetworkResource.Error(response.data.toString(), newData)
                        }
                    )
                }
            }
        }
    }

    protected open fun onFetchFailed() {}

    fun asLiveData() = result as LiveData<NetworkResource<ResultType>>

    @WorkerThread
    protected abstract fun saveCallResult(item: RequestType)

    @MainThread
    protected abstract fun shouldFetch(data: ResultType?): Boolean

    @MainThread
    protected abstract fun loadFromDb(): LiveData<ResultType>

    @MainThread
    protected abstract fun createCall(): LiveData<NetworkResponse<RequestType>>
}