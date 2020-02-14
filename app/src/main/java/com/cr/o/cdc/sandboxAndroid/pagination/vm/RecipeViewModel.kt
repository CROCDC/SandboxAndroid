package com.cr.o.cdc.sandboxAndroid.pagination.vm

import androidx.lifecycle.*
import androidx.paging.PagedList
import com.cr.o.cdc.sandboxAndroid.pagination.model.PagedRecipe
import com.cr.o.cdc.sandboxAndroid.pagination.repos.RecipeRepository
import javax.inject.Inject

class RecipeViewModel @Inject constructor(private val repository: RecipeRepository) : ViewModel() {

    private val search = MutableLiveData<String>("fish")

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