package com.cr.o.cdc.sandboxAndroid.repos

import PokemonQuery
import com.apollographql.apollo.ApolloClient
import com.cr.o.cdc.sandboxAndroid.network.ApolloManager
import okhttp3.OkHttpClient

class PokemonDataSource(private val apolloManager: ApolloManager) {

    private val client = ApolloClient.builder().serverUrl("https://graphql-pokemon.now.sh/graphql")
        .okHttpClient(OkHttpClient.Builder().build())
        .build()

    fun pokemon(id: String) =
        apolloManager.request(client.query(PokemonQuery.builder().id(id).build()))
}
