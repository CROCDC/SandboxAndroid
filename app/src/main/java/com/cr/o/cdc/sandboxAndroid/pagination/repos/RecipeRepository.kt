package com.cr.o.cdc.sandboxAndroid.pagination.repos

import androidx.paging.toLiveData
import com.cr.o.cdc.networking.AppExecutors
import com.cr.o.cdc.sandboxAndroid.R
import com.cr.o.cdc.sandboxAndroid.SandBoxApp
import com.cr.o.cdc.sandboxAndroid.db.SandBoxDB
import com.cr.o.cdc.sandboxAndroid.pagination.vo.PagedRecipeBoundaryCallback
import com.cr.o.cdc.sandboxAndroid.pagination.vo.PagedRecipeResource
import javax.inject.Inject

class RecipeRepository @Inject constructor(
    val db: SandBoxDB,
    private val service: RecipeService,
    private val appExecutors: AppExecutors,
    app: SandBoxApp
) {

    private val apiKey = app.resources.getString(R.string.recipes_api_key)
    private val appId = app.resources.getString(R.string.recipes_app_id)

    fun search(search: String): PagedRecipeResource {
        val boundaryCallback = PagedRecipeBoundaryCallback(
            search,
            service,
            appExecutors.networkIO(),
            db.recipeDao(),
            appId,
            apiKey
        )
        return PagedRecipeResource(
            boundaryCallback.networkStatus,
            db.recipeDao().loadPaged(search).toLiveData(
                10, boundaryCallback = boundaryCallback
            )
        ) {
            appExecutors.diskIO().execute {
                db.recipeDao().delete(search)
            }
        }

    }
}