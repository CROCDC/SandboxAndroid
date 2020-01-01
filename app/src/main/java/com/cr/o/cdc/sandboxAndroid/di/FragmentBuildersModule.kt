package com.cr.o.cdc.sandboxAndroid.di

import com.cr.o.cdc.sandboxAndroid.PokemonFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Camilo on 31/12/19.
 */

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributesPokemonFragment(): PokemonFragment
}