package com.cr.o.cdc.sandboxAndroid.di

import androidx.lifecycle.ViewModel
import com.cr.o.cdc.sandboxAndroid.vm.NotificationsViewModel
import com.cr.o.cdc.sandboxAndroid.vm.PokemonViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by Camilo on 31/12/19.
 */

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(PokemonViewModel::class)
    abstract fun bindPokemonViewModel(pokemonViewModel: PokemonViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NotificationsViewModel::class)
    abstract fun bindNotificationsViewModel(notificationsViewModel: NotificationsViewModel): ViewModel
}