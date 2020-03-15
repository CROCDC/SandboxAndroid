package com.cr.o.cdc.networking

data class RetrofitResource<T>(val data: T?, val status: StatusResult) {

    companion object {
        fun <T> error() = RetrofitResource<T>(null, StatusResult.FAILURE)
        fun <T> loading(data: T? = null) = RetrofitResource(data, StatusResult.LOADING)
        fun <T> success(data: T?) = RetrofitResource(data, StatusResult.SUCCESS)
    }
}