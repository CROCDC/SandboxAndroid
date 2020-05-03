package com.cr.o.cdc.sandboxAndroid.pagination.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.switchMap
import androidx.paging.PagedList
import com.cr.o.cdc.sandboxAndroid.pagination.db.model.PagedRecipe
import com.cr.o.cdc.sandboxAndroid.pagination.repos.RecipeRepository
import javax.inject.Inject

class RecipeViewModel @Inject constructor(private val repository: RecipeRepository) : ViewModel() {

    private val search = MutableLiveData<String>()

    private val recipeResource = search.map {
        repository.search(it)
    }

    val recipes: LiveData<PagedList<PagedRecipe>> = recipeResource.switchMap {
        it.data
    }

    val loading = recipeResource.switchMap {
        it.status
    }

    fun setSearch(search: String?) {
        this.search.value = search
    }

    fun refresh() {
        recipeResource.value?.refresh()
    }
}
