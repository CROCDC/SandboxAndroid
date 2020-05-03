package com.cr.o.cdc.sandboxAndroid.pokedex.di

import com.cr.o.cdc.sandboxAndroid.pokedex.fragments.PokedexFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModulePokemons {

    @ContributesAndroidInjector
    abstract fun contributesPokemonFragment(): PokedexFragment
}
