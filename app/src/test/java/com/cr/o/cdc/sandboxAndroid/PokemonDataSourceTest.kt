package com.cr.o.cdc.sandboxAndroid

import com.cr.o.cdc.sandboxAndroid.model.Pokemon
import com.cr.o.cdc.sandboxAndroid.model.PokemonDimension
import org.junit.Test

/**
 * Created by Camilo on 31/12/19.
 */
class PokemonDataSourceTest : EndpointTest() {

    @Test
    fun pokemon() {
        Pokemon(
            "",
            "",
            PokemonDimension("", "")
        )

        Pokemon::class.constructors
    }
}