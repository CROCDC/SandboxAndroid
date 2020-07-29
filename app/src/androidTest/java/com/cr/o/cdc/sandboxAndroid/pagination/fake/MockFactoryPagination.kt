package com.cr.o.cdc.sandboxAndroid.pagination.fake

import com.cr.o.cdc.sandboxAndroid.pagination.db.model.InfoSearchRecipe
import com.cr.o.cdc.sandboxAndroid.pagination.db.model.Recipe

object MockFactoryPagination {

    private fun getRecipe(): Recipe = Recipe("1", "label")

    fun getListOfRecipes(): List<Recipe> = listOf(getRecipe())

    fun getInfoSearchRecipe(uri: String): InfoSearchRecipe = InfoSearchRecipe(
        uri,
        0,
        "search"
    )
}
