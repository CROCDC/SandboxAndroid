package com.cr.o.cdc.sandboxAndroid.downdetector.repos

import com.cr.o.cdc.sandboxAndroid.downdetector.model.PingResponse
import java.net.HttpURLConnection
import java.net.URL

class SitesDataSource : SitesDataSourceProvider {

    override fun ping(address: String): PingResponse = try {
        val connection: HttpURLConnection = URL(address).openConnection() as HttpURLConnection
        connection.setRequestProperty("Connection", "close")
        connection.connectTimeout = 3000
        connection.connect()

        val response = connection.responseCode

        if (response == 200) {
            PingResponse.PingSuccess
        } else {
            PingResponse.PingError(response)
        }
    } catch (e: Exception) {
        PingResponse.PingException(e.message)
    }
}
