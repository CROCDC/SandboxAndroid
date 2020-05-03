package com.cr.o.cdc.sandboxAndroid.pagination.vo

import androidx.annotation.MainThread
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.cr.o.cdc.networking.StatusResult
import com.cr.o.cdc.sandboxAndroid.pagination.db.dao.RecipeDao
import com.cr.o.cdc.sandboxAndroid.pagination.db.model.InfoSearchRecipe
import com.cr.o.cdc.sandboxAndroid.pagination.db.model.PagedRecipe
import com.cr.o.cdc.sandboxAndroid.pagination.repos.RecipeService
import java.util.concurrent.Executor

class PagedRecipeBoundaryCallback(
    private val search: String,
    private val recipeService: RecipeService,
    private val networkIO: Executor,
    private val dao: RecipeDao,
    private val appId: String,
    private val apiKey: String

) : PagedList.BoundaryCallback<PagedRecipe>() {

    val networkStatus = MutableLiveData<StatusResult>()

    @MainThread
    override fun onZeroItemsLoaded() {
        fetch(0, 10)
    }

    @MainThread
    override fun onItemAtEndLoaded(itemAtEnd: PagedRecipe) {
        fetch(itemAtEnd.from, itemAtEnd.from + 10)
    }

    private fun fetch(to: Int, from: Int) {
        if (networkStatus.value == StatusResult.LOADING) {
            return
        }

        networkStatus.value = StatusResult.LOADING

        networkIO.execute {
            val response = recipeService.search(
                search,
                to,
                from,
                appId,
                apiKey
            ).execute().body()

            if (response != null) {
                val recipes = response.hits.map { it.recipe }
                dao.saveAll(recipes)
                dao.saveOffSet(recipes.map {
                    InfoSearchRecipe(
                        it.uri,
                        from,
                        search
                    )
                })

                networkStatus.postValue(StatusResult.SUCCESS)
            } else {
                networkStatus.postValue(StatusResult.FAILURE)
            }
        }
    }
}
