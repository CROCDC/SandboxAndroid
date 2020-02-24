package com.cr.o.cdc.sandboxAndroid.pagination.vo

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.cr.o.cdc.requestsmodule.StatusResult
import com.cr.o.cdc.sandboxAndroid.pagination.model.PagedRecipe

data class PagedRecipeResource(
    val status: LiveData<StatusResult>,
    val data: LiveData<PagedList<PagedRecipe>>,
    val refresh: () -> Unit
) {
    fun refresh() {
        refresh.invoke()
    }
}
