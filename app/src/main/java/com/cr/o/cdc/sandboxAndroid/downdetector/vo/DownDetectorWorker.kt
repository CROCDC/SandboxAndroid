package com.cr.o.cdc.sandboxAndroid.downdetector.vo

import android.content.Context
import androidx.core.app.NotificationManagerCompat
import androidx.hilt.Assisted
import androidx.hilt.work.WorkerInject
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.cr.o.cdc.sandboxAndroid.R
import com.cr.o.cdc.sandboxAndroid.downdetector.model.PingResponse
import com.cr.o.cdc.sandboxAndroid.downdetector.repos.SitesRepository
import com.cr.o.cdc.sandboxAndroid.downdetector.utils.createNotification

class DownDetectorWorker @WorkerInject constructor(
    @Assisted private val context: Context,
    @Assisted workerParams: WorkerParameters,
    private val repository: SitesRepository,
    private val notificationManager: NotificationManagerCompat
) : Worker(context, workerParams) {

    override fun doWork(): Result {

        val address = checkNotNull(inputData.getString(ARG_ADDRESS_SITE))

        when (repository.pingSite(address)) {
            !is PingResponse.PingSuccess -> {
                notificationManager.notify(
                    1,
                    context.createNotification(context.getString(R.string.o_no_site_down, address))
                )
            }
        }

        return Result.success()
    }

    companion object {
        const val ARG_ADDRESS_SITE = "address_site"
    }
}
