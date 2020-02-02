package com.cr.o.cdc.sandboxAndroid

import com.cr.o.cdc.sandboxAndroid.repos.PokemonDataSource
import junit.framework.TestCase.assertTrue
import org.junit.Test

class PokemonDataSourceTest : EndpointTest() {


    private val pokemonDataSource = PokemonDataSource(getManager())

    @Test
    fun assertPokemonNotNull() {
        assertTrue(getValue(pokemonDataSource.pokemon("Pikachu")).also {
            println(it)
        }.data != null)|
    }

    @Test
    fun assertPokemonNull() {
        assertTrue(getValue(pokemonDataSource.pokemon("id")).also {
            println(it)
        }.data == null)
    }
}