package com.cr.o.cdc.sandboxAndroid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.cr.o.cdc.sandboxAndroid.databinding.FragmentHomeBinding
import com.cr.o.cdc.sandboxAndroid.di.Injectable
import javax.inject.Inject

@Injectable
class HomeFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)

        val viewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)

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
        binding.btnBitbucket.setOnClickListener {
            findNavController().navigate(
                if (viewModel.isLoginInBitbucket()) {
                    R.id.action_homeFragment_to_workspacesFragment
                } else {
                    R.id.action_homeFragment_to_loginFragment
                }
            )
        }
        return binding.root
    }
}
