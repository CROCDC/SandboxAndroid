package com.cr.o.cdc.sandboxAndroid.pokedex.vm

import androidx.lifecycle.ViewModel
import com.cr.o.cdc.sandboxAndroid.pokedex.repos.PokemonRepository
import javax.inject.Inject

class PokemonViewModel @Inject constructor(repository: PokemonRepository) : ViewModel() {

    val pokemons = repository.pokemons(100)
}