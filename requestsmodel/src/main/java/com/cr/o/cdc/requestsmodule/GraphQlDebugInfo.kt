package com.cr.o.cdc.requestsmodule

import okhttp3.HttpUrl

data class GraphQlDebugInfo(override val debugUrl: String, val queryBuilder: QueryBuilder) :
    DebugInfo(debugUrl) {

    //improve
    fun debuUrl() =
        HttpUrl.Builder()
            .scheme("https")
            .host(debugUrl.replace("https://", "").replace("/graphql", ""))
            .addPathSegment("/graphql")
            .addQueryParameter("query", queryBuilder.query)
            .addQueryParameter("variables", queryBuilder.variables)
            .build().toUrl().toString()
}