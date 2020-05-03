package com.cr.o.cdc.sandboxAndroid.pokedex.repos

import PokemonsQuery
import androidx.lifecycle.LiveData
import com.cr.o.cdc.networking.NetworkResponse

interface PokemonDataSourceProvider {
    fun pokemons(first: Int): LiveData<NetworkResponse<PokemonsQuery.Data>>
}
