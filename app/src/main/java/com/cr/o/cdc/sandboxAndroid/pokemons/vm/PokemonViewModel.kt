package com.cr.o.cdc.sandboxAndroid.pokemons.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cr.o.cdc.requestsmodule.Response
import com.cr.o.cdc.sandboxAndroid.pokemons.model.Pokemon
import com.cr.o.cdc.sandboxAndroid.pokemons.repos.PokemonRepository
import javax.inject.Inject

/**
 * Created by Camilo on 31/12/19.
 */

class PokemonViewModel @Inject constructor(repository: PokemonRepository) : ViewModel() {

    val pikachu: LiveData<Response<Pokemon?>> = repository.pokemon("Pikachu")
}