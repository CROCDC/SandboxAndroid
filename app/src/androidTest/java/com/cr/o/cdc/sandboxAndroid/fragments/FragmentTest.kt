package com.cr.o.cdc.sandboxAndroid.fragments

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.test.platform.app.InstrumentationRegistry
import com.cr.o.cdc.sandboxAndroid.R
import com.cr.o.cdc.sandboxAndroid.pokemons.model.Pokemon
import com.cr.o.cdc.sandboxAndroid.notifications.vo.PushToken
import com.cr.o.cdc.sandboxAndroid.pokemons.repos.PokemonDataSourceProvider
import com.cr.o.cdc.sandboxAndroid.pokemons.repos.PokemonRepository
import com.cr.o.cdc.sharedtest.makeRandomInstance
import com.cr.o.cdc.sharedtest.modifyValue
import com.cr.o.cdc.sharedtest.sharedPreferences

@Suppress("UNCHECKED_CAST")
abstract class FragmentTest {

    private val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

    inline fun <reified F : Fragment> launchFragmentInContainer(vms: List<ViewModel> = listOf()) =
        launchFragmentInContainer<F>(themeResId = R.style.AppTheme)


    fun getPushToken() =
        PushToken(context.sharedPreferences())

    fun getPokemon() = Pokemon::class.makeRandomInstance<Pokemon>()

    fun getPokemonRepository(dataSource: PokemonDataSourceProvider) =
        PokemonRepository(dataSource)
}
