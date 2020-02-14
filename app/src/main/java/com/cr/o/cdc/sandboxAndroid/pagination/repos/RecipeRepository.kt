package com.cr.o.cdc.sandboxAndroid.pagination.repos

import androidx.paging.toLiveData
import com.cr.o.cdc.requests.AppExecutors
import com.cr.o.cdc.sandboxAndroid.db.SandBoxDB
import com.cr.o.cdc.sandboxAndroid.pagination.vo.PagedRecipeBoundaryCallback
import com.cr.o.cdc.sandboxAndroid.pagination.vo.PagedRecipeResource
import retrofit2.Retrofit
import javax.inject.Inject

class RecipeRepository @Inject constructor(
    val db: SandBoxDB,
    val retrofit: Retrofit,
    val appExecutors: AppExecutors
) {

    fun search(search: String): PagedRecipeResource {
        val boundaryCallback = PagedRecipeBoundaryCallback(
            search,
            retrofit,
            appExecutors.networkIO(),
            db.recipeDao()
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