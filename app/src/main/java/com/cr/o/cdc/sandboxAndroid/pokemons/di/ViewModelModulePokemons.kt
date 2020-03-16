package com.cr.o.cdc.sandboxAndroid.pokemons.di

import androidx.lifecycle.ViewModel
import com.cr.o.cdc.sandboxAndroid.di.ViewModelKey
import com.cr.o.cdc.sandboxAndroid.pokemons.vm.PokemonViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModulePokemons {

    @Binds
    @IntoMap
    @ViewModelKey(PokemonViewModel::class)
    abstract fun bindPokemonViewModel(pokemonViewModel: PokemonViewModel): ViewModel

}