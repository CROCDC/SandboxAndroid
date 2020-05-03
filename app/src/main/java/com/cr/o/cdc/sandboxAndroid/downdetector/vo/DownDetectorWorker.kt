package com.cr.o.cdc.sandboxAndroid.downdetector.vo

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.cr.o.cdc.sandboxAndroid.downdetector.model.PingResponse
import com.cr.o.cdc.sandboxAndroid.downdetector.repos.SitesRepository
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class DownDetectorWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    init {
        (context.applicationContext as HasAndroidInjector).androidInjector().inject(this)
    }

    @Inject
    lateinit var repository: SitesRepository

    override fun doWork(): Result {

        val address = checkNotNull(inputData.getString(ARG_ADDRESS_SITE))

        Log.e("CROCDC", "ping to $address")

        when (repository.pingSite(address)) {
            !is PingResponse.PingSuccess -> {
                //TODO notificacion
            }

        }

        return Result.success()
    }

    companion object {
        const val ARG_ADDRESS_SITE = "address_site"
    }
}