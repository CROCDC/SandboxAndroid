package com.cr.o.cdc.requestsannotations

/**
 * Created by Camilo on 01/01/20.
 */

interface RequestInterface {
    fun getRequestInfo(): RequestInfo

    fun getDebugInfo(): DebugInfo
}