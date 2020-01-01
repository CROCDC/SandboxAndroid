package com.cr.o.cdc.sandboxAndroid

import com.cr.o.cdc.sandboxAndroid.repos.PokemonDataSource
import org.junit.Test

/**
 * Created by Camilo on 31/12/19.
 */
class PokemonDataSourceTest : EndpointTest() {

    private val dataSource = PokemonDataSource(
        getApolloManager()
    )

    @Test
    fun pokemon() {
        getValue(dataSource.pokemon("UG9rZW1vbjowMDE")).let {
            println(it.data)
            println(it.data?.pokemon()?.id())
        }
    }
}