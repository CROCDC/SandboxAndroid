package com.cr.o.cdc.sandboxAndroid

import com.cr.o.cdc.sandboxAndroid.repos.PokemonDataSource
import org.junit.Test

class PokemonDataSourceTest : EndpointTest() {


    private val pokemonDataSource = PokemonDataSource(getManager())

    @Test
    fun pokemon(){
        print(getValue(pokemonDataSource.pokemon("Pikachu")))
    }
}