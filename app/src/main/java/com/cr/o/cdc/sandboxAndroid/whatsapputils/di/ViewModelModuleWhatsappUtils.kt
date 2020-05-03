package com.cr.o.cdc.sandboxAndroid.whatsapputils.di

import androidx.lifecycle.ViewModel
import com.cr.o.cdc.sandboxAndroid.di.ViewModelKey
import com.cr.o.cdc.sandboxAndroid.whatsapputils.vm.AddWhatsappMessageBotViewModel
import com.cr.o.cdc.sandboxAndroid.whatsapputils.vm.WhatsappBotViewModel
import com.cr.o.cdc.sandboxAndroid.whatsapputils.vm.WhatsappMessagesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModuleWhatsappUtils {

    @Binds
    @IntoMap
    @ViewModelKey(WhatsappBotViewModel::class)
    abstract fun bindWhatsappBotViewModel(whatsappBotViewModel: WhatsappBotViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddWhatsappMessageBotViewModel::class)
    abstract fun bindAddWhatsappMessageBotViewModel(
        addWhatsappMessageBotViewModel: AddWhatsappMessageBotViewModel
    ): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(WhatsappMessagesViewModel::class)
    abstract fun bindWhatsappMessagesViewModel(
        whatsappMessagesViewModel: WhatsappMessagesViewModel
    ): ViewModel
}
