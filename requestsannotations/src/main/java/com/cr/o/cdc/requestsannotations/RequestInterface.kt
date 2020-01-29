package com.cr.o.cdc.requestsannotations

import okhttp3.Request

/**
 * Created by Camilo on 01/01/20.
 */

interface RequestInterface {
    fun getRequestInfo(): Request

    fun getDebugInfo(): DebugInfo
}