package com.cr.o.cdc.sandboxAndroid.bluetooth.di

import android.bluetooth.BluetoothManager
import android.content.Context
import androidx.core.content.ContextCompat
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

/**
 * Created by Cami on 11/21/20.
 */
@Module
@InstallIn(ApplicationComponent::class)
class BluetoothModule {

    @Provides
    @Singleton
    fun provideBluetoothManager(@ApplicationContext appContext: Context): BluetoothManager? =
        ContextCompat.getSystemService<BluetoothManager>(appContext, BluetoothManager::class.java)

}
