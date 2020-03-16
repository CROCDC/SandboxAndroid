package com.cr.o.cdc.sandboxAndroid.pagination.di

import androidx.lifecycle.ViewModel
import com.cr.o.cdc.sandboxAndroid.di.ViewModelKey
import com.cr.o.cdc.sandboxAndroid.pagination.vm.RecipeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModulePagination {

    @Binds
    @IntoMap
    @ViewModelKey(RecipeViewModel::class)
    abstract fun bindRecipeViewModel(notificationsViewModel: RecipeViewModel): ViewModel

}