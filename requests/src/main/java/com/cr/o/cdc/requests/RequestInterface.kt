package com.cr.o.cdc.requests

import okhttp3.Request

/**
 * Created by Camilo on 01/01/20.
 */

interface RequestInterface {
    fun getRequest(): Request

    fun getDebugInfo(): DebugInfo
}