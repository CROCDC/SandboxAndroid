package com.cr.o.cdc.sandboxAndroid


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.cr.o.cdc.sandboxAndroid.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.btnTabLayout.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_tabLayoutFragment)
        }

        binding.btnNotifications.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_notificationsFragment)
        }

        binding.btnPokedex.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_pokemonFragment)
        }

        binding.btnRecipes.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_recipesFragment)
        }

        binding.btnCustomViews.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_customViewsFragment)
        }

        return binding.root
    }


}
