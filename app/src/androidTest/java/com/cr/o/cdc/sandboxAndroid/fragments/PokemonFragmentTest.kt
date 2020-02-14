package com.cr.o.cdc.sandboxAndroid.fragments

import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers
import com.cr.o.cdc.requests.Response
import com.cr.o.cdc.sandboxAndroid.R
import com.cr.o.cdc.sandboxAndroid.pokemons.fragments.PokemonFragment
import com.cr.o.cdc.sandboxAndroid.pokemons.repos.PokemonDataSourceProvider
import com.cr.o.cdc.sharedtest.getMessage
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertTrue
import org.junit.Test

class PokemonFragmentTest : FragmentTest() {


    @Test
    fun assertTxtNameSetValue() {
        val p = getPokemon()
        val vm =
            PokemonViewModel(getPokemonRepository(mockk<PokemonDataSourceProvider>(relaxed = true).apply {
                every { pokemon("Pikachu") } returns MutableLiveData()
            }))
        launchFragmentInContainer<PokemonFragment>(listOf(vm))

        vm.pikachu.myPostValue(Response(p, 200))

        onView(ViewMatchers.withId(R.id.txt_name)).check { view, _ ->
            assertTrue(getMessage("R.uri.txt_name"), (view as TextView).text == p.name)
        }
    }
}