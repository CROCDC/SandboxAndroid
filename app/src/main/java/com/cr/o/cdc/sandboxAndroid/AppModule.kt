package com.cr.o.cdc.sandboxAndroid

import android.content.Context
import android.content.SharedPreferences
import androidx.core.app.NotificationManagerCompat
import androidx.room.Room
import androidx.work.WorkManager
import com.apollographql.apollo.ApolloClient
import com.cr.o.cdc.networking.AppExecutors
import com.cr.o.cdc.networking.LiveDataCallAdapterFactory
import com.cr.o.cdc.sandboxAndroid.downdetector.repos.SitesDataSource
import com.cr.o.cdc.sandboxAndroid.downdetector.repos.SitesDataSourceProvider
import com.cr.o.cdc.sandboxAndroid.pagination.repos.RecipeService
import com.cr.o.cdc.sandboxAndroid.pokedex.repos.PokemonDataSource
import com.cr.o.cdc.sandboxAndroid.pokedex.repos.PokemonDataSourceProvider
import com.cr.o.cdc.sandboxAndroid.preguntadoshelper.db.model.PreguntadosQuestion
import com.cr.o.cdc.sandboxAndroid.rnc.repos.RNCDataSource
import com.cr.o.cdc.sandboxAndroid.rnc.repos.RNCDataSourceProvider
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.io.InputStream

@Module
@InstallIn(ApplicationComponent::class)
class AppModule {

    @Provides
    fun provideRetrofit(): Retrofit.Builder = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(LiveDataCallAdapterFactory())

    @Provides
    fun provideRecipeService(
        retrofitBuilder: Retrofit.Builder,
        @ApplicationContext appContext: Context
    ): RecipeService =
        retrofitBuilder.baseUrl(appContext.resources.getString(R.string.recipes_api)).build()
            .create(RecipeService::class.java)

    @Provides
    fun provideDB(@ApplicationContext appContext: Context): SandBoxDB = Room
        .databaseBuilder(appContext, SandBoxDB::class.java, SandBoxDB.DATABASE_NAME)
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build().also {
            try {
                val inputStream: InputStream = appContext.assets.open("questions.json")
                val size: Int = inputStream.available()
                val buffer = ByteArray(size)
                inputStream.read(buffer)
                inputStream.close()
                it.preguntadosDao().saveAll(
                    Gson().fromJson(
                        String(buffer, Charsets.UTF_8),
                        object : TypeToken<List<PreguntadosQuestion>>() {}.type
                    )
                )
            } catch (ex: IOException) {
            }
        }

    @Provides
    fun providePokemonDataSource(apolloClient: ApolloClient): PokemonDataSourceProvider =
        PokemonDataSource(apolloClient)

    @Provides
    fun provideSharedPreferences(@ApplicationContext appContext: Context): SharedPreferences =
        appContext.getSharedPreferences(appContext.packageName, Context.MODE_PRIVATE)

    @Provides
    fun provideAppExecutors(): AppExecutors = AppExecutors()

    @Provides
    fun provideMapDataSourceProvider(): RNCDataSourceProvider = RNCDataSource()

    @Provides
    fun provideApolloClient(@ApplicationContext appContext: Context): ApolloClient =
        ApolloClient.builder().serverUrl(appContext.resources.getString(R.string.pokemon_api))
            .okHttpClient(OkHttpClient.Builder().build()).build()

    @Provides
    fun provideSitesDataSourceProvider(): SitesDataSourceProvider = SitesDataSource()

    @Provides
    fun provideWorkManager(@ApplicationContext appContext: Context): WorkManager =
        WorkManager.getInstance(appContext)

    @Provides
    fun provideNotificationManagerCompat(
        @ApplicationContext appContext: Context
    ): NotificationManagerCompat = NotificationManagerCompat.from(appContext)
}
