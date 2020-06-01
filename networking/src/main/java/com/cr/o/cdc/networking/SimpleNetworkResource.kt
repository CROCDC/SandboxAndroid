package com.cr.o.cdc.networking

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

abstract class SimpleNetworkResource<RequestType>
@MainThread constructor(private val appExecutors: AppExecutors) {

    private val result = MediatorLiveData<NetworkResource<RequestType>>()

    init {
        result.value = NetworkResource.loading()
        fetchFromNetwork()
    }

    private fun fetchFromNetwork() {
        val apiResponse: LiveData<NetworkResponse<RequestType>> = createCall()

        result.addSource(apiResponse) { r ->
            result.removeSource(apiResponse)

            if (r is SuccessResponse) {
                appExecutors.diskIO().execute {
                    r.data?.let { saveData(it) }
                }
                result.postValue(NetworkResource.success(r.data))
            } else {
                result.postValue(NetworkResource.error())
            }
        }
    }

    @MainThread
    protected abstract fun createCall(): LiveData<NetworkResponse<RequestType>>

    @WorkerThread
    protected abstract fun saveData(data: RequestType)

    fun asLiveData(): LiveData<NetworkResource<RequestType>> {
        return result
    }

}
