package com.cr.o.cdc.sandboxAndroid.pokedex.vm

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cr.o.cdc.networking.NetworkResource
import com.cr.o.cdc.sandboxAndroid.pokedex.db.model.Pokemon
import com.cr.o.cdc.sandboxAndroid.pokedex.repos.PokemonRepository

class PokemonViewModel @ViewModelInject constructor(repository: PokemonRepository) : ViewModel() {

    val pokemons: LiveData<NetworkResource<List<Pokemon>>> = repository.pokemons(100)
}
