package com.cr.o.cdc.sandboxAndroid.downdetector.model

sealed class PingResponse {

    object PingSuccess : PingResponse()

    data class PingError(val response: Int) : PingResponse()

    data class PingException(val message: String?) : PingResponse()
}
