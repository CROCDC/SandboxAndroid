package com.cr.o.cdc.sandboxAndroid.repos

import androidx.lifecycle.LiveData
import com.cr.o.cdc.requests.Client
import com.cr.o.cdc.requests.Response
import com.cr.o.cdc.sandboxAndroid.model.Pokemon
import queries.QueryPokemon

class PokemonDataSource(private val client: Client) : PokemonDataSourceProvider {

    override fun pokemon(id: String): LiveData<Response<Pokemon?>> = client.request(QueryPokemon().build(id))
}
