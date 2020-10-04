package com.cr.o.cdc.sandboxAndroid.downdetector.vm

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.cr.o.cdc.sandboxAndroid.downdetector.db.model.Site
import com.cr.o.cdc.sandboxAndroid.downdetector.repos.SitesRepository
import com.cr.o.cdc.sandboxAndroid.downdetector.service.DownDetectorWorker
import java.util.concurrent.TimeUnit

data class AddSiteViewModel @ViewModelInject constructor(
    private val repository: SitesRepository,
    private val workManager: WorkManager
) : ViewModel() {

    fun saveSite(site: Site) {
        val request = PeriodicWorkRequestBuilder<DownDetectorWorker>(
            site.intervalCheck.toLong(),
            TimeUnit.MINUTES
        ).setInputData(workDataOf(Pair(DownDetectorWorker.ARG_ADDRESS_SITE, site.address)))
            .setInitialDelay(15, TimeUnit.MINUTES)
            .build()

        repository.saveSite(site.copy(workRequestId = request.id.toString()))

        workManager.enqueue(request)
    }
}
