package com.cr.o.cdc.sandboxAndroid.repos

import PokemonQuery
import androidx.lifecycle.LiveData
import com.apollographql.apollo.ApolloClient
import com.cr.o.cdc.sandboxAndroid.network.ApolloManager
import com.cr.o.cdc.sandboxAndroid.network.NetworkResponse

class PokemonDataSource(
    private val apolloManager: ApolloManager,
    private val client: ApolloClient
) : PokemonDataSourceProvider {

    override fun pokemon(id: String): LiveData<NetworkResponse<PokemonQuery.Data>> =
        apolloManager.request(client.query(PokemonQuery.builder().id(id).build()))
}
