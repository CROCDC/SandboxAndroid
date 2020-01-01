package com.cr.o.cdc.requests

/**
 * Created by Camilo on 04/05/19.
 */
class NetworkResponse<T>(val httpCode: Int, val body: String?) {

    val isSuccess: Boolean
        get() = httpCode in 200..299


    val result: StatusResult
        get() = when (httpCode) {
            408 -> StatusResult.OFFLINE
            in 200..299 -> StatusResult.SUCCESS
            else -> StatusResult.FAILURE
        }
}