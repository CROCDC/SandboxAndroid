package com.cr.o.cdc.sandboxAndroid.pokemons.repos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cr.o.cdc.sandboxAndroid.pokemons.model.Pokemon
import com.cr.o.cdc.sandboxAndroid.pokemons.model.Pokemons
import com.cr.o.cdc.networking.Response

class PokemonDataSource : PokemonDataSourceProvider {

    override fun pokemon(name: String): LiveData<Response<Pokemon?>> =
        MutableLiveData()

    override fun pokemons(first: Int): LiveData<Response<Pokemons?>> =
        MutableLiveData()

}
