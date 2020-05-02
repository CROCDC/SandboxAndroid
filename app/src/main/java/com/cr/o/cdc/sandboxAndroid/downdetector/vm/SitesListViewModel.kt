package com.cr.o.cdc.sandboxAndroid.downdetector.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cr.o.cdc.sandboxAndroid.downdetector.db.model.Site
import com.cr.o.cdc.sandboxAndroid.downdetector.repos.SitesRepository
import javax.inject.Inject

class SitesListViewModel @Inject constructor(private val sitesRepository: SitesRepository) :
    ViewModel() {

    val sites: LiveData<List<Site>> = sitesRepository.getAllSites()

    fun modifyEnable(address: String, enable: Boolean) {
        sitesRepository.modifyEnable(address, enable)
    }

}