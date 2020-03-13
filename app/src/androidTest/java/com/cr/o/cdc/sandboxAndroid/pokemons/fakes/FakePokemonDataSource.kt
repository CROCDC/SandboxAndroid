package com.cr.o.cdc.sandboxAndroid.pokemons.fakes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cr.o.cdc.sandboxAndroid.pokemons.model.Pokemon
import com.cr.o.cdc.sandboxAndroid.pokemons.model.Pokemons
import com.cr.o.cdc.sandboxAndroid.pokemons.repos.PokemonDataSourceProvider
import com.cr.o.cdc.networking.Response

@Suppress("UNCHECKED_CAST")
class FakePokemonDataSource(private val list: List<MutableLiveData<*>>) :
    PokemonDataSourceProvider {
    override fun pokemon(name: String): LiveData<com.cr.o.cdc.networking.Response<Pokemon?>> =
        list[0] as LiveData<com.cr.o.cdc.networking.Response<Pokemon?>>

    override fun pokemons(first: Int): LiveData<com.cr.o.cdc.networking.Response<Pokemons?>> =
        list[1] as LiveData<com.cr.o.cdc.networking.Response<Pokemons?>>

}