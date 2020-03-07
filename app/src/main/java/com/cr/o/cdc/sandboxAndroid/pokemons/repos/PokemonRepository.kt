package com.cr.o.cdc.sandboxAndroid.pokemons.repos

import javax.inject.Inject

class PokemonRepository @Inject constructor(private val dataSource: PokemonDataSourceProvider) {

    fun pokemon(name: String) = dataSource.pokemon(name)
}