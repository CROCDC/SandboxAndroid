package com.cr.o.cdc.sandboxAndroid.di

import com.cr.o.cdc.sandboxAndroid.downdetector.vo.DownDetectorWorker
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class WorkerModule {

    @ContributesAndroidInjector
    abstract fun contributesDownDetectorWorker(): DownDetectorWorker
}
