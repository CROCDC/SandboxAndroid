package com.cr.o.cdc.sandboxAndroid.downdetector.repos

import com.cr.o.cdc.sandboxAndroid.downdetector.model.PingResponse

interface SitesDataSourceProvider {
    fun ping(address: String): PingResponse
}