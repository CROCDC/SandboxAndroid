package com.cr.o.cdc.sandboxAndroid.pokemon.repos

import com.apollographql.apollo.ApolloClient
import com.cr.o.cdc.networking.SuccessResponse
import com.cr.o.cdc.sandboxAndroid.pokedex.repos.PokemonDataSource
import com.cr.o.cdc.sandboxAndroid.utils.EndpointTest
import com.cr.o.cdc.sharedtest.getValue
import junit.framework.TestCase.assertTrue
import okhttp3.OkHttpClient
import org.junit.Test

class PokemonDataSourceTest : EndpointTest() {
    private val pokemonDataSource = PokemonDataSource(
        ApolloClient.builder().serverUrl("https://graphql-pokemon.now.sh/")
            .okHttpClient(OkHttpClient.Builder().build()).build()
    )

    @Test
    fun pokemons() {
        val response = getValue(pokemonDataSource.pokemons(20))

        assertTrue((response as SuccessResponse).data != null)
        print(response)
    }
}
