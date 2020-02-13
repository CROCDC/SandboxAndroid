package com.cr.o.cdc.sandboxAndroid.pokemons.repos

import javax.inject.Inject

/**
 * Created by Camilo on 31/12/19.
 */

class PokemonRepository @Inject constructor(private val dataSource: PokemonDataSourceProvider) {

    fun pokemon(name: String) = dataSource.pokemon(name)
}