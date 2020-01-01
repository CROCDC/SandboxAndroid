package com.cr.o.cdc.sandboxAndroid.repos

import PokemonQuery
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.apollographql.apollo.ApolloClient

class PokemonDataSource(
    private val apolloManager: com.cr.o.cdc.requests.Manager,
    private val client: ApolloClient
) : PokemonDataSourceProvider {

    override fun pokemon(id: String): LiveData<com.cr.o.cdc.requests.NetworkResponse<PokemonQuery.Data>> =
        MutableLiveData()
}
