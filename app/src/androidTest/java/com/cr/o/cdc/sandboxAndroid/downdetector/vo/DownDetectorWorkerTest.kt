package com.cr.o.cdc.sandboxAndroid.downdetector.vo

import android.content.Context
import androidx.core.app.NotificationManagerCompat
import androidx.test.core.app.ApplicationProvider
import androidx.work.ListenableWorker
import androidx.work.testing.TestListenableWorkerBuilder
import com.cr.o.cdc.sandboxAndroid.coronavirus.di.CoronavirusModule
import com.cr.o.cdc.sandboxAndroid.downdetector.repos.SitesRepository
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
@UninstallModules(CoronavirusModule::class)
class DownDetectorWorkerTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private val context: Context = ApplicationProvider.getApplicationContext()

    @Inject
    lateinit var repository: SitesRepository

    @Inject
    lateinit var notificationManager: NotificationManagerCompat

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun testDownDetectorWorker() {
        val worker = TestListenableWorkerBuilder<DownDetectorWorker>(context)
            .build()

        runBlocking {
            val result = worker.doWork()
            assertThat(result, `is`(ListenableWorker.Result.success()))
        }
    }
}
