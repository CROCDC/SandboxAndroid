package com.cr.o.cdc.sandboxAndroid.pokemons.repos

import androidx.lifecycle.LiveData
import com.cr.o.cdc.sandboxAndroid.pokemons.model.Pokemon
import com.cr.o.cdc.sandboxAndroid.pokemons.model.Pokemons
import com.cr.o.cdc.networking.Response

interface PokemonDataSourceProvider {
    fun pokemon(name: String): LiveData<com.cr.o.cdc.networking.Response<Pokemon?>>
    fun pokemons(first: Int): LiveData<com.cr.o.cdc.networking.Response<Pokemons?>>
}