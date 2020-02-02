package com.cr.o.cdc.sandboxAndroid.di

import android.content.Context
import android.content.SharedPreferences
import com.cr.o.cdc.requests.Client
import com.cr.o.cdc.sandboxAndroid.SandBoxApp
import com.cr.o.cdc.sandboxAndroid.repos.PokemonDataSource
import com.cr.o.cdc.sandboxAndroid.repos.PokemonDataSourceProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Camilo on 31/12/19.
 */
@Module(includes = [ViewModelModule::class])
class AppModule {

    @Provides
    @Singleton
    fun provideManager() = Client()

    @Provides
    @Singleton
    fun providePokemonDataSourceProvide(
        client: Client
    ): PokemonDataSourceProvider = PokemonDataSource(client)

    @Singleton
    @Provides
    fun provideSharedPreferences(app: SandBoxApp): SharedPreferences =
        app.getSharedPreferences(app.packageName, Context.MODE_PRIVATE)
}