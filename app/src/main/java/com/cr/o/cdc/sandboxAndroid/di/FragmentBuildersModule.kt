package com.cr.o.cdc.sandboxAndroid.di

import com.cr.o.cdc.sandboxAndroid.notifications.fragments.NotificationsFragment
import com.cr.o.cdc.sandboxAndroid.pagination.fragments.RecipesFragment
import com.cr.o.cdc.sandboxAndroid.pokemons.fragments.PokemonFragment
import com.cr.o.cdc.sandboxAndroid.whatsapputils.fragments.WhatsappMesaggesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributesPokemonFragment(): PokemonFragment

    @ContributesAndroidInjector
    abstract fun contributesNotificationsFragment(): NotificationsFragment

    @ContributesAndroidInjector
    abstract fun contributesRecipesFragment(): RecipesFragment

    @ContributesAndroidInjector
    abstract fun contributesWhatsappMesaggesFragment(): WhatsappMesaggesFragment
}