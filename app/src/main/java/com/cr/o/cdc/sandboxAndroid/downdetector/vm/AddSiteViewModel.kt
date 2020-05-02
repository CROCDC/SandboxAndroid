package com.cr.o.cdc.sandboxAndroid.downdetector.vm

import androidx.lifecycle.ViewModel
import com.cr.o.cdc.sandboxAndroid.downdetector.db.model.Site
import com.cr.o.cdc.sandboxAndroid.downdetector.repos.SitesRepository
import javax.inject.Inject

data class AddSiteViewModel @Inject constructor(private val repository: SitesRepository) :
    ViewModel() {

    fun saveSite(site: Site): Unit = repository.saveSite(site)

}