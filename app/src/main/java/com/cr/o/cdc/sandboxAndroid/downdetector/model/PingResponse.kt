package com.cr.o.cdc.sandboxAndroid.downdetector.model

sealed class PingResponse {

    object PingSuccess : PingResponse()

    data class PingError(val response: Int) : PingResponse()

    data class PingMalformedURLException(val message: String?) : PingResponse()

    data class PingIOException(val message: String?) : PingResponse()

    data class PingSecurityException(val message: String?) : PingResponse()

    data class PingUnknownError(val message: String?) : PingResponse()
}