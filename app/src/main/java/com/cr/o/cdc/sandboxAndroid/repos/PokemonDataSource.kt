package com.cr.o.cdc.sandboxAndroid.repos

import androidx.lifecycle.LiveData
import com.cr.o.cdc.requests.Manager
import com.cr.o.cdc.sandboxAndroid.model.Pokemon
import queries.QueryPokemon

class PokemonDataSource(private val manager: Manager) : PokemonDataSourceProvider {

    override fun pokemon(id: String): LiveData<Pokemon> = manager.request(QueryPokemon().build(id))
}
