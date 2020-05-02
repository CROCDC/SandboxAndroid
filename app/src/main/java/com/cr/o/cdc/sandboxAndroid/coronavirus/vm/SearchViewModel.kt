package com.cr.o.cdc.sandboxAndroid.coronavirus.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.cr.o.cdc.sandboxAndroid.coronavirus.db.model.CountryStat
import com.cr.o.cdc.sandboxAndroid.coronavirus.repos.CoronavirusRepository
import javax.inject.Inject

class SearchViewModel @Inject constructor(repository: CoronavirusRepository) : ViewModel() {

    private val search: MutableLiveData<String> = MutableLiveData<String>()

    val list: LiveData<List<CountryStat>> = search.switchMap {
        repository.search(it)
    }

    fun setSearch(search: String) {
        this.search.value = search
    }
}