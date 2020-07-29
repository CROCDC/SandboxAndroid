package com.cr.o.cdc.sandboxAndroid.pokedex.db.dao

import com.cr.o.cdc.sandboxAndroid.coronavirus.di.CoronavirusModule
import com.cr.o.cdc.sandboxAndroid.pokedex.fake.MockFactoryPokedex
import com.cr.o.cdc.sandboxAndroid.utils.DBTest
import com.cr.o.cdc.sharedtest.getValueLiveData
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import junit.framework.TestCase.assertTrue
import org.junit.Test

@HiltAndroidTest
@UninstallModules(CoronavirusModule::class)
class PokemonDaoTest : DBTest() {
    private val dao = db.pokemonDao()

    private val pokemons = MockFactoryPokedex.getListOfPokemons()

    @Test
    fun saveAll() {
        dao.saveAll(pokemons)
    }

    @Test
    fun loadAll() {
        dao.saveAll(pokemons)

        assertTrue(getValueLiveData(dao.loadAll(), 10)?.isNotEmpty() == true)
    }
}
