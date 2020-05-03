package com.cr.o.cdc.sandboxAndroid.coronavirus.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cr.o.cdc.networking.NetworkResource
import com.cr.o.cdc.sandboxAndroid.coronavirus.db.model.CountryStat
import com.cr.o.cdc.sandboxAndroid.coronavirus.repos.CoronavirusRepository
import javax.inject.Inject

class CoronavirusViewModel @Inject constructor(private val repository: CoronavirusRepository) :
    ViewModel() {

    fun getCasesByCountry(): LiveData<NetworkResource<List<CountryStat>>> =
        repository.getCasesByCountry()
}
