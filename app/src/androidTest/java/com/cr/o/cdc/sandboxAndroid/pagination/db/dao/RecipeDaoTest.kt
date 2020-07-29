package com.cr.o.cdc.sandboxAndroid.pagination.db.dao

import androidx.paging.toLiveData
import com.cr.o.cdc.sandboxAndroid.coronavirus.di.CoronavirusModule
import com.cr.o.cdc.sandboxAndroid.pagination.fake.MockFactoryPagination
import com.cr.o.cdc.sandboxAndroid.utils.DBTest
import com.cr.o.cdc.sharedtest.getValueLiveData
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Test

@HiltAndroidTest
@UninstallModules(CoronavirusModule::class)
class RecipeDaoTest : DBTest() {

    private val dao = db.recipeDao()

    private val recipes = MockFactoryPagination.getListOfRecipes()

    @Test
    fun saveAll() {
        dao.saveAll(recipes)
    }

    @Test
    fun loadRecipe() {
        dao.saveAll(recipes)

        val recipe1 = getValueLiveData(dao.loadRecipe(recipes[0].uri), 2)
        assertEquals(recipe1?.uri, "1")
    }

    @Test
    fun delete() {
        dao.saveOffSet(recipes.map {
            MockFactoryPagination.getInfoSearchRecipe(it.uri)
        })
        dao.saveAll(recipes)

        assertTrue(getValueLiveData(dao.loadPaged("search").toLiveData(5), 2)?.size == recipes.size)

        dao.delete("search")

        assertTrue(getValueLiveData(dao.loadPaged("search").toLiveData(5), 2)?.isEmpty() == true)
    }

    @Test
    fun saveOffSet() {
        dao.saveOffSet(recipes.map {
            MockFactoryPagination.getInfoSearchRecipe(it.uri)
        })
    }

    @Test
    fun loadPaged() {
        dao.saveOffSet(
            recipes.map {
                MockFactoryPagination.getInfoSearchRecipe(it.uri)
            }
        )
        dao.saveAll(recipes)

        assertTrue(getValueLiveData(dao.loadPaged("search").toLiveData(5), 2)?.size == recipes.size)
    }
}
