package com.cr.o.cdc.sandboxAndroid.pokedex.repos

import PokemonsQuery
import androidx.lifecycle.LiveData
import com.cr.o.cdc.networking.RetrofitResponse

interface PokemonDataSourceProvider {
    fun pokemons(first: Int): LiveData<RetrofitResponse<PokemonsQuery.Data>>
}