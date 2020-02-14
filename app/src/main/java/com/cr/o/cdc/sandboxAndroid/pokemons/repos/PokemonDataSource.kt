package com.cr.o.cdc.sandboxAndroid.pokemons.repos

import androidx.lifecycle.LiveData
import com.cr.o.cdc.requests.Client
import com.cr.o.cdc.requestsmodule.Response
import com.cr.o.cdc.sandboxAndroid.pokemons.model.Pokemon
import queries.QueryPokemon

class PokemonDataSource(private val client: Client) :
    PokemonDataSourceProvider {

    override fun pokemon(name: String): LiveData<Response<Pokemon?>> =
        client.request(QueryPokemon().build(name))
}
