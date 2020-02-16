package com.cr.o.cdc.requestsmodule

data class Response<T>(val data: T?, private val httpCode: Int, val debugInfo: DebugInfo) {

    val status: StatusResult
        get() = when (httpCode) {
            400 -> StatusResult.FAILURE
            408 -> StatusResult.OFFLINE
            200 -> StatusResult.SUCCESS
            else -> StatusResult.LOADING
        }
}