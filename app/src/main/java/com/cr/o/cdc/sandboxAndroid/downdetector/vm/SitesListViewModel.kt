package com.cr.o.cdc.sandboxAndroid.downdetector.vm

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.cr.o.cdc.sandboxAndroid.downdetector.db.model.Site
import com.cr.o.cdc.sandboxAndroid.downdetector.model.PingResponse
import com.cr.o.cdc.sandboxAndroid.downdetector.repos.SitesRepository
import com.cr.o.cdc.sandboxAndroid.downdetector.vo.DownDetectorWorker
import java.util.UUID
import java.util.concurrent.TimeUnit

class SitesListViewModel @ViewModelInject constructor(
    private val sitesRepository: SitesRepository,
    private val workManager: WorkManager
) : ViewModel() {

    val sites: LiveData<List<Site>> = sitesRepository.getAllSites()

    fun modifyEnable(site: Site) {

        val workRequestId: String? = if (!site.enable) {
            val request = PeriodicWorkRequestBuilder<DownDetectorWorker>(
                site.intervalCheck.toLong(),
                TimeUnit.MINUTES
            ).setInputData(workDataOf(Pair(DownDetectorWorker.ARG_ADDRESS_SITE, site.address)))
                .build()

            workManager.enqueue(request)

            request.id.toString()
        } else {
            workManager.cancelWorkById(UUID.fromString(site.workRequestId))
            null
        }

        sitesRepository.modifyEnable(site.address, !site.enable, workRequestId)
    }

    fun deleteSite(address: String) {
        sitesRepository.deleteSite(address)
    }

    fun testSite(address: String): MutableLiveData<PingResponse> =
        sitesRepository.asyncPing(address)
}
