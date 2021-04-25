package com.cr.o.cdc.sandboxAndroid.pokedex.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cr.o.cdc.sandboxAndroid.libraries.networking.NetworkResource
import com.cr.o.cdc.sandboxAndroid.pokedex.db.model.Pokemon
import com.cr.o.cdc.sandboxAndroid.pokedex.repos.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(repository: PokemonRepository) : ViewModel() {

    val pokemons: LiveData<NetworkResource<List<Pokemon>>> = repository.pokemons(100)
}
