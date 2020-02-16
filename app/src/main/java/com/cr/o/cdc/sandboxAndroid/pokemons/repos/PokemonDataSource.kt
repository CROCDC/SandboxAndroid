package com.cr.o.cdc.sandboxAndroid.pokemons.repos

import androidx.lifecycle.LiveData
import com.cr.o.cdc.requests.Client
import com.cr.o.cdc.requestsmodule.Response
import com.cr.o.cdc.sandboxAndroid.pokemons.model.Pokemon
import com.cr.o.cdc.sandboxAndroid.pokemons.model.Pokemons
import queries.QueryPokemon
import queries.QueryPokemons

class PokemonDataSource(private val client: Client) : PokemonDataSourceProvider {

    override fun pokemon(name: String): LiveData<Response<Pokemon?>> =
        client.request(QueryPokemon(name))

    override fun pokemons(first: Int): LiveData<Response<Pokemons?>> =
        client.request(QueryPokemons(first))


}
