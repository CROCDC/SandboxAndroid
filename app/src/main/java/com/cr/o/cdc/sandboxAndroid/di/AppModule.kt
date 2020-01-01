package com.cr.o.cdc.sandboxAndroid.di

import com.apollographql.apollo.ApolloClient
import com.cr.o.cdc.sandboxAndroid.BuildConfig
import com.cr.o.cdc.requests.Manager
import com.cr.o.cdc.requests.AppExecutors
import com.cr.o.cdc.sandboxAndroid.repos.PokemonDataSource
import com.cr.o.cdc.sandboxAndroid.repos.PokemonDataSourceProvider
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Singleton

/**
 * Created by Camilo on 31/12/19.
 */
@Module(includes = [ViewModelModule::class])
class AppModule {

    @Provides
    @Singleton
    fun provideApolloClient(): ApolloClient = ApolloClient.builder().serverUrl(
        BuildConfig.URL_SERVER
    ).okHttpClient(OkHttpClient().newBuilder().build()).build()

    @Provides
    @Singleton
    fun provideApolloManager() = Manager()

    @Provides
    @Singleton
    fun providePokemonDataSourceProvide(
        apolloManager: com.cr.o.cdc.requests.Manager,
        apolloClient: ApolloClient
    ): PokemonDataSourceProvider =
        PokemonDataSource(apolloManager, apolloClient)
}