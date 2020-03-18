package com.cr.o.cdc.sandboxAndroid.pokedex.repos

import PokemonsQuery
import androidx.lifecycle.LiveData
import com.apollographql.apollo.ApolloClient
import com.cr.o.cdc.networking.RetrofitResponse
import com.cr.o.cdc.sandboxAndroid.execute

class PokemonDataSource(private val apolloClient: ApolloClient) : PokemonDataSourceProvider {

    override fun pokemons(first: Int): LiveData<RetrofitResponse<PokemonsQuery.Data>> =
        apolloClient.query(PokemonsQuery(first)).execute()

}
