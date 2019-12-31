package com.cr.o.cdc.sandboxAndroid.repos

import PokemonQuery
import androidx.lifecycle.LiveData
import com.apollographql.apollo.ApolloClient
import com.cr.o.cdc.sandboxAndroid.network.ApolloManager
import com.cr.o.cdc.sandboxAndroid.network.NetworkResponse
import okhttp3.OkHttpClient

class PokemonDataSource(private val apolloManager: ApolloManager) : PokemonDataSourceProvider {

    private val client = ApolloClient.builder().serverUrl("https://graphql-pokemon.now.sh/graphql")
        .okHttpClient(OkHttpClient.Builder().build())
        .build()

    override fun pokemon(id: String): LiveData<NetworkResponse<PokemonQuery.Data>> =
        apolloManager.request(client.query(PokemonQuery.builder().id(id).build()))
}
