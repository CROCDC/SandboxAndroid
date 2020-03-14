package com.cr.o.cdc.sandboxAndroid.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.cr.o.cdc.sandboxAndroid.R
import com.cr.o.cdc.sandboxAndroid.SandBoxApp
import com.cr.o.cdc.sandboxAndroid.db.SandBoxDB
import com.cr.o.cdc.sandboxAndroid.pokemons.repos.PokemonDataSource
import com.cr.o.cdc.sandboxAndroid.pokemons.repos.PokemonDataSourceProvider
import com.cr.o.cdc.networking.AppExecutors
import com.cr.o.cdc.networking.LiveDataCallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(app: SandBoxApp): Retrofit = Retrofit.Builder()
        .baseUrl(app.resources.getString(R.string.recipes_url))
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(LiveDataCallAdapterFactory())
        .build()


    @Singleton
    @Provides
    fun provideDB(app: SandBoxApp): SandBoxDB = Room
        .databaseBuilder(app, SandBoxDB::class.java, SandBoxDB.DATABASE_NAME)
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun providePokemonDataSource(): PokemonDataSourceProvider =
        fakePokemonDataSource ?: PokemonDataSource()

    @Singleton
    @Provides
    fun provideSharedPreferences(app: SandBoxApp): SharedPreferences =
        app.getSharedPreferences(app.packageName, Context.MODE_PRIVATE)

    @Singleton
    @Provides
    fun provideAppExecutors() = AppExecutors()

    companion object {
        private var fakePokemonDataSource: PokemonDataSourceProvider? = null
        fun setPokemonDataSourceProvider(dataSource: PokemonDataSourceProvider) {
            fakePokemonDataSource = dataSource
        }
    }
}