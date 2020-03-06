package com.cr.o.cdc.sandboxAndroid.utils

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.cr.o.cdc.sandboxAndroid.pokemons.model.Pokemon
import com.cr.o.cdc.sharedtest.makeRandomInstance

@Suppress("UNCHECKED_CAST")
abstract class FragmentTest {

    private val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

    fun getPokemon() = Pokemon::class.makeRandomInstance<Pokemon>()
}
