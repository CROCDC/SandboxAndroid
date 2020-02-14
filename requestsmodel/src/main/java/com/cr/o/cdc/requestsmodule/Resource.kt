package com.cr.o.cdc.requestsmodule

data class Resource<T>(val data: T?, val status: StatusResult) {

    companion object {
        fun <T> error() = Resource<T>(
            null,
            StatusResult.FAILURE
        )
        fun <T> loading(data: T? = null) = Resource(
            data,
            StatusResult.LOADING
        )
        fun <T> success(data: T?) = Resource(
            data,
            StatusResult.SUCCESS
        )
    }
}