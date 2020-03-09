package com.cr.o.cdc.sandboxAndroid.db

import androidx.paging.toLiveData
import com.cr.o.cdc.sandboxAndroid.pagination.model.InfoSearchRecipe
import com.cr.o.cdc.sandboxAndroid.pagination.model.Recipe
import com.cr.o.cdc.sandboxAndroid.utils.DBTest
import com.cr.o.cdc.sharedtest.getValueLiveData
import com.cr.o.cdc.sharedtest.makeRandomInstance
import junit.framework.TestCase.assertTrue
import org.junit.Test

class RecipeDaoTest : DBTest() {

    private val dao = db.recipeDao()

    private val recipes = listOf<Recipe>(
        Recipe::class.makeRandomInstance(),
        Recipe::class.makeRandomInstance(),
        Recipe::class.makeRandomInstance(),
        Recipe::class.makeRandomInstance()
    )

    @Test
    fun saveAll() {
        dao.saveAll(recipes)
    }

    @Test
    fun loadRecipe() {
        dao.saveAll(recipes)

        assertTrue(getValueLiveData(dao.loadRecipe(recipes[0].uri), 2) != null)
    }

    @Test
    fun delete() {
        dao.saveOffSet(recipes.map { InfoSearchRecipe(it.uri, 0, "search") })
        dao.saveAll(recipes)

        assertTrue(getValueLiveData(dao.loadPaged("search").toLiveData(5), 2)?.size == 4)

        dao.delete("search")

        assertTrue(getValueLiveData(dao.loadPaged("search").toLiveData(5), 2)?.isEmpty() == true)
    }

    @Test
    fun saveOffSet() {
        dao.saveOffSet(recipes.map { InfoSearchRecipe(it.uri, 0, "search") })
    }

    @Test
    fun loadPaged() {
        dao.saveOffSet(
            recipes.map {
                InfoSearchRecipe(
                    it.uri,
                    0,
                    "search"
                )
            }
        )
        dao.saveAll(recipes)

        assertTrue(getValueLiveData(dao.loadPaged("search").toLiveData(5), 2)?.size == 4)
    }

}