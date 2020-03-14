package com.cr.o.cdc.sandboxAndroid.pagination.vo

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.cr.o.cdc.sandboxAndroid.pagination.model.PagedRecipe
import com.cr.o.cdc.networking.StatusResult

data class PagedRecipeResource(
    val status: LiveData<StatusResult>,
    val data: LiveData<PagedList<PagedRecipe>>,
    val refresh: () -> Unit
) {
    fun refresh() {
        refresh.invoke()
    }
}
