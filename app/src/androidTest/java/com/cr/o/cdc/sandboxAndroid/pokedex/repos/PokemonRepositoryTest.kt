package com.cr.o.cdc.sandboxAndroid.pokedex.repos

import PokemonsQuery
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cr.o.cdc.networking.NetworkResponse
import com.cr.o.cdc.sandboxAndroid.coronavirus.di.CoronavirusModule
import com.cr.o.cdc.sandboxAndroid.di.AppModule
import com.cr.o.cdc.sandboxAndroid.utils.RepositoryTest
import com.cr.o.cdc.sharedtest.getValueOfList
import com.cr.o.cdc.sharedtest.makeRandomInstance
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import junit.framework.TestCase.assertTrue
import org.junit.Test
import retrofit2.Response

@HiltAndroidTest
@UninstallModules(CoronavirusModule::class)
class PokemonRepositoryTest : RepositoryTest() {

    private val pokemonsData: List<PokemonsQuery.Pokemon> = listOf(
        PokemonsQuery.Pokemon::class.makeRandomInstance(),
        PokemonsQuery.Pokemon::class.makeRandomInstance(),
        PokemonsQuery.Pokemon::class.makeRandomInstance(),
        PokemonsQuery.Pokemon::class.makeRandomInstance(),
        PokemonsQuery.Pokemon("typename", "id", null, null, null)

    )

    private val repository = PokemonRepository(
        object : PokemonDataSourceProvider {
            override fun pokemons(first: Int): LiveData<NetworkResponse<PokemonsQuery.Data>> =
                MutableLiveData(
                    NetworkResponse.create(
                        Response.success(PokemonsQuery.Data(pokemonsData))
                    )
                )
        },
        db,
        AppModule().provideAppExecutors()
    )

    @Test
    fun pokemons() {
        getValueOfList(repository.pokemons(100), 10)

        val pokemons = getValueOfList(db.pokemonDao().loadAll(), 10)

        assertTrue(pokemons?.find { it.id == pokemonsData[0].id } != null)
    }
}
