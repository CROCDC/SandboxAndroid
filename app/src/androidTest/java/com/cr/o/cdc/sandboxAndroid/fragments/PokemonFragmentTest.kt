package com.cr.o.cdc.sandboxAndroid.fragments

import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.cr.o.cdc.requestsmodule.DebugInfo
import com.cr.o.cdc.requestsmodule.Response
import com.cr.o.cdc.sandboxAndroid.R
import com.cr.o.cdc.sandboxAndroid.pokemons.fragments.PokemonFragment
import com.cr.o.cdc.sandboxAndroid.pokemons.repos.PokemonDataSourceProvider
import com.cr.o.cdc.sandboxAndroid.pokemons.vm.PokemonViewModel
import com.cr.o.cdc.sharedtest.getMessage
import com.cr.o.cdc.sharedtest.myPostValue
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PokemonFragmentTest : FragmentTest() {


    @Test
    fun assertTxtNameSetValue() {
        val p = getPokemon()
        val vm =
            PokemonViewModel(getPokemonRepository(mockk<PokemonDataSourceProvider>(relaxed = true).apply {
                every { pokemon("Pikachu") } returns MutableLiveData()
            }))
        launchFragmentInContainer<PokemonFragment>(listOf(vm))

        vm.pikachu.myPostValue(Response(p, 200, DebugInfo("")))

        onView(ViewMatchers.withId(R.id.txt_name)).check { view, _ ->
            assertTrue(getMessage("R.id.txt_name"), (view as TextView).text == p.name)
        }
    }
}