package com.cr.o.cdc.requestsmodule

data class GraphQlDebugInfo(override val debugUrl: String, val queryBuilder: QueryBuilder) :
    DebugInfo(debugUrl)