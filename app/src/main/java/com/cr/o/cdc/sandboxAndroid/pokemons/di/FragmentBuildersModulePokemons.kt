package com.cr.o.cdc.sandboxAndroid.pokemons.di

import com.cr.o.cdc.sandboxAndroid.pokemons.fragments.PokemonFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModulePokemons {

    @ContributesAndroidInjector
    abstract fun contributesPokemonFragment(): PokemonFragment

}