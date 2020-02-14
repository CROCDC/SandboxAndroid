package com.cr.o.cdc.sandboxAndroid.pokemons.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.cr.o.cdc.annotations.Injectable
import com.cr.o.cdc.sandboxAndroid.databinding.FragmentPokemonBinding
import com.cr.o.cdc.sandboxAndroid.pokemons.vm.PokemonViewModel
import javax.inject.Inject

@Injectable
class PokemonFragment : Fragment() {

    private lateinit var binding: FragmentPokemonBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPokemonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val vm = ViewModelProviders.of(this, viewModelFactory).get(PokemonViewModel::class.java)

        vm.pikachu.observe(viewLifecycleOwner, Observer {
            binding.txtName.text = it.data?.name
        })

    }
}
