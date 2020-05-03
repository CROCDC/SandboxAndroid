package com.cr.o.cdc.sandboxAndroid.pokedex.db.dao

import com.cr.o.cdc.sandboxAndroid.pokedex.db.model.Pokemon
import com.cr.o.cdc.sandboxAndroid.pokedex.db.model.PokemonMini
import com.cr.o.cdc.sandboxAndroid.utils.DBTest
import com.cr.o.cdc.sharedtest.getValueLiveData
import junit.framework.TestCase.assertTrue
import org.junit.Test

class PokemonDaoTest : DBTest() {
    private val dao = db.pokemonDao()

    private val pokemons = listOf(
        Pokemon("id1", null, null, null),
        Pokemon("id2", "name", "image", listOf(PokemonMini("id", "name")))
    )

    @Test
    fun saveAll() {
        dao.saveAll(pokemons)
    }

    @Test
    fun loadAll() {
        dao.saveAll(pokemons)

        assertTrue(getValueLiveData(dao.loadAll(), 10)?.find { it.id == "id1" } != null)
    }
}
