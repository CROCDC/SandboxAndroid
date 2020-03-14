package com.cr.o.cdc.sandboxAndroid.pokemons.fragments

import android.widget.TextView
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.MutableLiveData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers
import com.cr.o.cdc.sandboxAndroid.R
import com.cr.o.cdc.sandboxAndroid.di.AppModule
import com.cr.o.cdc.sandboxAndroid.pokemons.fakes.FakePokemonDataSource
import com.cr.o.cdc.sandboxAndroid.pokemons.model.Pokemon
import com.cr.o.cdc.networking.DebugInfo
import com.cr.o.cdc.sandboxAndroid.utils.FragmentTest
import com.cr.o.cdc.networking.Response
import com.cr.o.cdc.sharedtest.getMessage
import com.cr.o.cdc.sharedtest.makeRandomInstance
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test

class PokemonFragmentTest : FragmentTest() {

    private val pokemon =
        MutableLiveData(
            Response<Pokemon>(
                Pokemon::class.makeRandomInstance(), 200,
                DebugInfo("")
            )
        )

    private val dataSource = FakePokemonDataSource(listOf(pokemon))

    @Before
    fun initDataSource() {
        AppModule.setPokemonDataSourceProvider(dataSource)
    }

    @Test
    fun assertTxtNameSetValue() {
        launchFragmentInContainer<PokemonFragment>()

        onView(ViewMatchers.withId(R.id.txt_name)).check { view, _ ->
            assertTrue(
                getMessage("R.id.txt_name"),
                (view as TextView).text == pokemon.value?.data?.name
            )
        }
    }
}