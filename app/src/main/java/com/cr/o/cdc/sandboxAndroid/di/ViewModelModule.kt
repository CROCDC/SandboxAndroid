package com.cr.o.cdc.sandboxAndroid.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cr.o.cdc.sandboxAndroid.coronavirus.vm.CoronavirusViewModel
import com.cr.o.cdc.sandboxAndroid.notifications.vm.NotificationsViewModel
import com.cr.o.cdc.sandboxAndroid.pagination.vm.RecipeViewModel
import com.cr.o.cdc.sandboxAndroid.pokemons.vm.PokemonViewModel
import com.cr.o.cdc.sandboxAndroid.rnc.vm.MapViewModel
import com.cr.o.cdc.sandboxAndroid.whatsapputils.vm.AddWhatsappMessageBotViewModel
import com.cr.o.cdc.sandboxAndroid.whatsapputils.vm.WhatsappBotViewModel
import com.cr.o.cdc.sandboxAndroid.whatsapputils.vm.WhatsappMessagesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

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

    @Binds
    @IntoMap
    @ViewModelKey(RecipeViewModel::class)
    abstract fun bindRecipeViewModel(notificationsViewModel: RecipeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MapViewModel::class)
    abstract fun bindMapViewModel(mapViewModel: MapViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}