package com.cr.o.cdc.sandboxAndroid.utils

import com.cr.o.cdc.sandboxAndroid.pokemons.model.Pokemon
import com.cr.o.cdc.sharedtest.makeRandomInstance

@Suppress("UNCHECKED_CAST")
abstract class FragmentTest {

    fun getPokemon() = Pokemon::class.makeRandomInstance<Pokemon>()
}
