package com.cr.o.cdc.sandboxAndroid.coronavirus.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cr.o.cdc.networking.RetrofitResource
import com.cr.o.cdc.sandboxAndroid.coronavirus.model.CountryStat
import com.cr.o.cdc.sandboxAndroid.coronavirus.repos.CoronavirusRepository
import javax.inject.Inject

class CoronavirusViewModel @Inject constructor(private val repository: CoronavirusRepository) :
    ViewModel() {

    fun getCasesByCountry(): LiveData<RetrofitResource<List<CountryStat>>> =
        repository.getCasesByCountry()
}