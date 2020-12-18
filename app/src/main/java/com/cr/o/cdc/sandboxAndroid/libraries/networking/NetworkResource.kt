package com.cr.o.cdc.sandboxAndroid.libraries.networking

data class NetworkResource<T>(val data: T?, val status: StatusResult) {

    companion object {
        fun <T> error() = NetworkResource<T>(null, StatusResult.FAILURE)
        fun <T> loading(data: T? = null) = NetworkResource(data, StatusResult.LOADING)
        fun <T> success(data: T?) = NetworkResource(data, StatusResult.SUCCESS)
    }
}