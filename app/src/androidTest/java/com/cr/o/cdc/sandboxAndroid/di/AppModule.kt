package com.cr.o.cdc.sandboxAndroid.di

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.room.Room
import com.apollographql.apollo.ApolloClient
import com.cr.o.cdc.networking.AppExecutors
import com.cr.o.cdc.networking.LiveDataCallAdapterFactory
import com.cr.o.cdc.sandboxAndroid.R
import com.cr.o.cdc.sandboxAndroid.SandBoxApp
import com.cr.o.cdc.sandboxAndroid.coronavirus.repos.CoronavirusService
import com.cr.o.cdc.sandboxAndroid.pagination.repos.RecipeService
import com.cr.o.cdc.sandboxAndroid.pokedex.repos.PokemonDataSource
import com.cr.o.cdc.sandboxAndroid.pokedex.repos.PokemonDataSourceProvider
import com.cr.o.cdc.sandboxAndroid.whatsapputils.db.SandBoxDB
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
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
    ): CoronavirusService {
        Log.e("CROCDC", retrofitBuilder.toString())
        Log.e("CROCDC", app.toString())
        return fakeCoronavirusService
    }

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
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun providePokemonDataSource(apolloClient: ApolloClient): PokemonDataSourceProvider =
        fakePokemonDataSource ?: PokemonDataSource(apolloClient)

    @Singleton
    @Provides
    fun provideSharedPreferences(app: SandBoxApp): SharedPreferences =
        app.getSharedPreferences(app.packageName, Context.MODE_PRIVATE)

    @Singleton
    @Provides
    fun provideAppExecutors() = AppExecutors()

    @Singleton
    @Provides
    fun provideApolloClient(app: SandBoxApp): ApolloClient =
        ApolloClient.builder().serverUrl(app.resources.getString(R.string.pokemon_api))
            .okHttpClient(OkHttpClient.Builder().build()).build()

    companion object {
        private var fakePokemonDataSource: PokemonDataSourceProvider? = null
        private lateinit var fakeCoronavirusService: CoronavirusService

        fun setCoronavirusService(coronavirusService: CoronavirusService) {
            fakeCoronavirusService = coronavirusService
        }
    }
}
