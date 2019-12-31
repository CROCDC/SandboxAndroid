package com.cr.o.cdc.sandboxAndroid

import androidx.lifecycle.LiveData
import com.cr.o.cdc.sandboxAndroid.network.NetworkResponse
import com.cr.o.cdc.sandboxAndroid.network.StatusResult
import com.cr.o.cdc.sandboxAndroid.repos.PokemonDataSource
import org.junit.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

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