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
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.btnCoronavirus.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_coronavirusFragment)
        }
        binding.btnRnc.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_RNCFragment)
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
        binding.btnWhatsappUtils.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_whatsappUtilsFragment)
        }
        binding.btnDownDetector.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_sitesListFragment)
        }
        binding.btnMotionLayout.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_motionLayoutSelectorFragment)
        }
        return binding.root
    }
}
