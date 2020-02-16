package com.cr.o.cdc.sandboxAndroid.repos

import com.cr.o.cdc.requestsmodule.GraphQlDebugInfo
import com.cr.o.cdc.sandboxAndroid.pokemons.repos.PokemonDataSource
import junit.framework.TestCase.assertTrue
import org.junit.Test

class PokemonDataSourceTest : EndpointTest() {


    private val pokemonDataSource =
        PokemonDataSource(getManager())

    @Test
    fun assertPokemonNotNull() {
        assertTrue(getValue(pokemonDataSource.pokemon("Pikachu")).also {
            println((it.debugInfo as GraphQlDebugInfo).debuUrl())
            println(it)
        }.data != null)
    }

    @Test
    fun assertPokemonNull() {
        assertTrue(getValue(pokemonDataSource.pokemon("uri")).also {
            println((it.debugInfo as GraphQlDebugInfo).debuUrl())
            println(it)
        }.data == null)
    }
}