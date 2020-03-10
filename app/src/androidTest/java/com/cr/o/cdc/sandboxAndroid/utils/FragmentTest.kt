package com.cr.o.cdc.sandboxAndroid.utils

import com.cr.o.cdc.sandboxAndroid.pokemons.model.Pokemon
import com.cr.o.cdc.sharedtest.makeRandomInstance
import org.junit.Rule

@Suppress("UNCHECKED_CAST")
abstract class FragmentTest {

    @Rule
    @JvmField
    var disableAnimationsRule = DisableAnimationsRule()

    fun getPokemon() = Pokemon::class.makeRandomInstance<Pokemon>()
}
