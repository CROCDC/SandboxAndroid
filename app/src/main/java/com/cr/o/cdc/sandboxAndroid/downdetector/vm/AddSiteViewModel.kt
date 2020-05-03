package com.cr.o.cdc.sandboxAndroid.downdetector.vm

import androidx.lifecycle.ViewModel
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.cr.o.cdc.sandboxAndroid.downdetector.db.model.Site
import com.cr.o.cdc.sandboxAndroid.downdetector.repos.SitesRepository
import com.cr.o.cdc.sandboxAndroid.downdetector.vo.DownDetectorWorker
import java.util.concurrent.TimeUnit
import javax.inject.Inject

data class AddSiteViewModel @Inject constructor(
    private val repository: SitesRepository,
    private val workManager: WorkManager
) : ViewModel() {

    fun saveSite(site: Site) {
        val request = PeriodicWorkRequestBuilder<DownDetectorWorker>(
            site.intervalCheck.toLong(),
            TimeUnit.MINUTES
        ).setInputData(workDataOf(Pair(DownDetectorWorker.ARG_ADDRESS_SITE, site.address)))
            .build()

        repository.saveSite(site.copy(workRequestId = request.id.toString()))

        workManager.enqueue(request)
    }

}