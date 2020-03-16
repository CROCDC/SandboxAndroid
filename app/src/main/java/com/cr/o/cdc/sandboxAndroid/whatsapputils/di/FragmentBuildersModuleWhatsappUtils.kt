package com.cr.o.cdc.sandboxAndroid.whatsapputils.di

import com.cr.o.cdc.sandboxAndroid.whatsapputils.fragments.AddWhatsappMessageBotFragment
import com.cr.o.cdc.sandboxAndroid.whatsapputils.fragments.WhatsappBotFragment
import com.cr.o.cdc.sandboxAndroid.whatsapputils.fragments.WhatsappMessagesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModuleWhatsappUtils {

    @ContributesAndroidInjector
    abstract fun contributesWhatsappBotFragment(): WhatsappBotFragment

    @ContributesAndroidInjector
    abstract fun contributesAddWhatsappMessageBotFragment(): AddWhatsappMessageBotFragment

    @ContributesAndroidInjector
    abstract fun contributesWhatsappMessagesFragment(): WhatsappMessagesFragment
}