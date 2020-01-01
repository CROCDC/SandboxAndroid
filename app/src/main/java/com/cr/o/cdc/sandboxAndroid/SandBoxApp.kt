package com.cr.o.cdc.sandboxAndroid

import android.app.Application
import com.cr.o.cdc.sandboxAndroid.di.AppInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

/**
 * Created by Camilo on 31/12/19.
 */
class SandBoxApp : Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    override fun onCreate() {
        super.onCreate()

        AppInjector.init(this)
    }
}