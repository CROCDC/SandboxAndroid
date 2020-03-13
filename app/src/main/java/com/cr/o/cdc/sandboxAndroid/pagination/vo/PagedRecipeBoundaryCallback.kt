package com.cr.o.cdc.sandboxAndroid.pagination.vo

import androidx.annotation.MainThread
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.cr.o.cdc.sandboxAndroid.db.dao.RecipeDao
import com.cr.o.cdc.sandboxAndroid.pagination.model.InfoSearchRecipe
import com.cr.o.cdc.sandboxAndroid.pagination.model.PagedRecipe
import com.cr.o.cdc.sandboxAndroid.pagination.repos.RecipeService
import com.cr.o.cdc.sandboxAndroid.utils.StatusResult
import retrofit2.Retrofit
import java.util.concurrent.Executor

class PagedRecipeBoundaryCallback(
    private val search: String,
    retrofit: Retrofit,
    private val networkIO: Executor,
    private val dao: RecipeDao

) : PagedList.BoundaryCallback<PagedRecipe>() {

    private val mlApi = retrofit.create(RecipeService::class.java)
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
            //todo
            val response =
                mlApi.search(search, to, from, "41ce6696", "416e06f4fa1b46dacd17ca14a1d563c9")
                    .execute().body()

            if (response != null) {
                val recipes = response.hits.map { it.recipe }
                dao.saveAll(recipes)
                dao.saveOffSet(recipes.map { InfoSearchRecipe(it.uri, from, search) })

                networkStatus.postValue(StatusResult.SUCCESS)
            } else {
                networkStatus.postValue(StatusResult.FAILURE)
            }
        }
    }

}