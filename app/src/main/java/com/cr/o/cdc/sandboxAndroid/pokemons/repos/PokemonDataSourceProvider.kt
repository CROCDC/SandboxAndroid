package com.cr.o.cdc.sandboxAndroid.pokemons.repos

import androidx.lifecycle.LiveData
import com.cr.o.cdc.requestsmodule.Response
import com.cr.o.cdc.sandboxAndroid.pokemons.model.Pokemon
import com.cr.o.cdc.sandboxAndroid.pokemons.model.Pokemons

interface PokemonDataSourceProvider {
    fun pokemon(name: String): LiveData<Response<Pokemon?>>
    fun pokemons(first: Int): LiveData<Response<Pokemons?>>
}