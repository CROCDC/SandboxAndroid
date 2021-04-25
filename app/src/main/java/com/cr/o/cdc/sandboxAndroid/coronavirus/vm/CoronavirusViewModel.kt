package com.cr.o.cdc.sandboxAndroid.coronavirus.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cr.o.cdc.sandboxAndroid.coronavirus.db.model.CountryStat
import com.cr.o.cdc.sandboxAndroid.coronavirus.repos.CoronavirusRepository
import com.cr.o.cdc.sandboxAndroid.libraries.networking.NetworkResource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CoronavirusViewModel @Inject constructor(
    private val repository: CoronavirusRepository
) : ViewModel() {

    fun getCasesByCountry(): LiveData<NetworkResource<List<CountryStat>>> =
        repository.getCasesByCountry()
}
