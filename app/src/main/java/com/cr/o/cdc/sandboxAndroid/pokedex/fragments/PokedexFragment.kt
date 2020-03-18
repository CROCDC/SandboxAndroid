package com.cr.o.cdc.sandboxAndroid.pokedex.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.cr.o.cdc.annotations.Injectable
import com.cr.o.cdc.sandboxAndroid.databinding.FragmentPokedexBinding
import com.cr.o.cdc.sandboxAndroid.pokedex.ui.PokemonsAdapter
import com.cr.o.cdc.sandboxAndroid.pokedex.vm.PokemonViewModel
import javax.inject.Inject

@Injectable
class PokedexFragment : Fragment() {

    private lateinit var binding: FragmentPokedexBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var adapter: PokemonsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPokedexBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val vm = ViewModelProviders.of(this, viewModelFactory).get(PokemonViewModel::class.java)

        adapter = PokemonsAdapter()

        binding.recycler.adapter = adapter

        vm.pokemons.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it.data)
        })

    }
}
