package com.cr.o.cdc.sandboxAndroid.coronavirus.vm

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cr.o.cdc.sandboxAndroid.libraries.networking.NetworkResource
import com.cr.o.cdc.sandboxAndroid.coronavirus.db.model.CountryStat
import com.cr.o.cdc.sandboxAndroid.coronavirus.repos.CoronavirusRepository

class CoronavirusViewModel @ViewModelInject constructor(
    private val repository: CoronavirusRepository
) : ViewModel() {

    fun getCasesByCountry(): LiveData<NetworkResource<List<CountryStat>>> =
        repository.getCasesByCountry()
}
