package com.cr.o.cdc.sandboxAndroid.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.cr.o.cdc.networking.AppExecutors
import com.cr.o.cdc.networking.LiveDataCallAdapterFactory
import com.cr.o.cdc.sandboxAndroid.R
import com.cr.o.cdc.sandboxAndroid.SandBoxApp
import com.cr.o.cdc.sandboxAndroid.coronavirus.di.ViewModelModuleCoronavirus
import com.cr.o.cdc.sandboxAndroid.coronavirus.repos.CoronavirusService
import com.cr.o.cdc.sandboxAndroid.db.SandBoxDB
import com.cr.o.cdc.sandboxAndroid.notifications.di.ViewModelModuleNotifications
import com.cr.o.cdc.sandboxAndroid.pagination.di.ViewModelModulePagination
import com.cr.o.cdc.sandboxAndroid.pagination.repos.RecipeService
import com.cr.o.cdc.sandboxAndroid.pokemons.di.ViewModelModulePokemons
import com.cr.o.cdc.sandboxAndroid.pokemons.repos.PokemonDataSource
import com.cr.o.cdc.sandboxAndroid.pokemons.repos.PokemonDataSourceProvider
import com.cr.o.cdc.sandboxAndroid.rnc.di.ViewModelModuleRNC
import com.cr.o.cdc.sandboxAndroid.rnc.repos.RNCDataSource
import com.cr.o.cdc.sandboxAndroid.rnc.repos.RNCDataSourceProvider
import com.cr.o.cdc.sandboxAndroid.whatsapputils.di.ViewModelModuleWhatsappUtils
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(
    includes = [ViewModelModule::class, ViewModelModuleCoronavirus::class,
        ViewModelModuleWhatsappUtils::class, ViewModelModuleNotifications::class,
        ViewModelModulePagination::class, ViewModelModulePokemons::class,
        ViewModelModuleRNC::class
    ]
)
class AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit.Builder = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(LiveDataCallAdapterFactory())

    @Provides
    @Singleton
    fun provideCoronavirusService(
        retrofitBuilder: Retrofit.Builder,
        app: SandBoxApp
    ): CoronavirusService =
        retrofitBuilder.baseUrl(app.resources.getString(R.string.coronavirus_api)).build()
            .create(CoronavirusService::class.java)

    @Provides
    @Singleton
    fun provideRecipeService(
        retrofitBuilder: Retrofit.Builder,
        app: SandBoxApp
    ): RecipeService =
        retrofitBuilder.baseUrl(app.resources.getString(R.string.recipes_api)).build()
            .create(RecipeService::class.java)

    @Singleton
    @Provides
    fun provideDB(app: SandBoxApp): SandBoxDB = Room
        .databaseBuilder(app, SandBoxDB::class.java, SandBoxDB.DATABASE_NAME)
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun providePokemonDataSource(): PokemonDataSourceProvider = PokemonDataSource()

    @Singleton
    @Provides
    fun provideSharedPreferences(app: SandBoxApp): SharedPreferences =
        app.getSharedPreferences(app.packageName, Context.MODE_PRIVATE)

    @Singleton
    @Provides
    fun provideAppExecutors() = AppExecutors()

    @Singleton
    @Provides
    fun provideMapDataSourceProvider(): RNCDataSourceProvider = RNCDataSource()
}