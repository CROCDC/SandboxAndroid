package com.cr.o.cdc.sandboxAndroid.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.cr.o.cdc.sandboxAndroid.databinding.FragmentPokemonBinding
import com.cr.o.cdc.sandboxAndroid.di.Injectable
import com.cr.o.cdc.sandboxAndroid.di.ViewModelFactory
import com.cr.o.cdc.sandboxAndroid.vm.PokemonViewModel
import javax.inject.Inject

class PokemonFragment : Fragment(), Injectable {

    private lateinit var binding: FragmentPokemonBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

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
