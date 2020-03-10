package com.cr.o.cdc.sandboxAndroid.di

import com.cr.o.cdc.sandboxAndroid.notifications.fragments.NotificationsFragment
import com.cr.o.cdc.sandboxAndroid.pagination.fragments.RecipesFragment
import com.cr.o.cdc.sandboxAndroid.pokemons.fragments.PokemonFragment
import com.cr.o.cdc.sandboxAndroid.rnc.fragments.MapFragment
import com.cr.o.cdc.sandboxAndroid.whatsapputils.fragments.AddWhatsappMessageBotFragment
import com.cr.o.cdc.sandboxAndroid.whatsapputils.fragments.WhatsappBotFragment
import com.cr.o.cdc.sandboxAndroid.whatsapputils.fragments.WhatsappMessagesFragment
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
    abstract fun contributesWhatsappMessagesFragment(): WhatsappMessagesFragment

    @ContributesAndroidInjector
    abstract fun contributesMapFragment(): MapFragment

    @ContributesAndroidInjector
    abstract fun contributesWhatsappBotFragment(): WhatsappBotFragment

    @ContributesAndroidInjector
    abstract fun contributesAddWhatsappMessageBotFragment(): AddWhatsappMessageBotFragment
}