package com.cr.o.cdc.sandboxAndroid.recipe

import androidx.paging.toLiveData
import com.cr.o.cdc.sandboxAndroid.pagination.db.model.InfoSearchRecipe
import com.cr.o.cdc.sandboxAndroid.pagination.db.model.Recipe
import com.cr.o.cdc.sandboxAndroid.utils.DBTest
import com.cr.o.cdc.sharedtest.getValueLiveData
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Test

class RecipeDaoTest : DBTest() {

    private val dao = db.recipeDao()

    private val recipes = listOf(
        Recipe("1", "label"),
        Recipe("2", "label")
    )

    @Test
    fun saveAll() {
        dao.saveAll(recipes)
    }

    @Test
    fun loadRecipe() {
        dao.saveAll(recipes)

        val recipe1 = getValueLiveData(dao.loadRecipe("1"), 2)
        val recipe2 = getValueLiveData(dao.loadRecipe("2"), 2)
        assertEquals(recipe1?.uri, "1")
        assertEquals(recipe2?.uri, "2")
    }

    @Test
    fun delete() {
        dao.saveOffSet(recipes.map {
            InfoSearchRecipe(
                it.uri,
                0,
                "search"
            )
        })
        dao.saveAll(recipes)

        assertTrue(getValueLiveData(dao.loadPaged("search").toLiveData(5), 2)?.size == 2)

        dao.delete("search")

        assertTrue(getValueLiveData(dao.loadPaged("search").toLiveData(5), 2)?.isEmpty() == true)
    }

    @Test
    fun saveOffSet() {
        dao.saveOffSet(recipes.map {
            InfoSearchRecipe(
                it.uri,
                0,
                "search"
            )
        })
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

        assertTrue(getValueLiveData(dao.loadPaged("search").toLiveData(5), 2)?.size == 2)
    }
}
