package com.cr.o.cdc.sandboxAndroid.pokemons.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cr.o.cdc.requestsmodule.Response
import com.cr.o.cdc.sandboxAndroid.pokemons.model.Pokemon
import com.cr.o.cdc.sandboxAndroid.pokemons.repos.PokemonRepository
import javax.inject.Inject

class PokemonViewModel @Inject constructor(repository: PokemonRepository) : ViewModel() {

    val pikachu: LiveData<Response<Pokemon?>> = repository.pokemon("Pikachu")
}