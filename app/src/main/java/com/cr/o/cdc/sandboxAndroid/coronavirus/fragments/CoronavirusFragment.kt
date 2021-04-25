package com.cr.o.cdc.sandboxAndroid.coronavirus.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.cr.o.cdc.sandboxAndroid.R
import com.cr.o.cdc.sandboxAndroid.coronavirus.ui.CountryStatsAdapter
import com.cr.o.cdc.sandboxAndroid.coronavirus.vm.CoronavirusViewModel
import com.cr.o.cdc.sandboxAndroid.databinding.FragmentCoronavirusBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoronavirusFragment : Fragment() {

    private val viewModel: CoronavirusViewModel by viewModels()

    private lateinit var binding: FragmentCoronavirusBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCoronavirusBinding.inflate(inflater, container, false)

        binding.txtSearch.setOnClickListener {
            findNavController().navigate(R.id.action_coronavirusFragment_to_searchFragment)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = CountryStatsAdapter()

        binding.recycler.adapter = adapter

        viewModel.getCasesByCountry().observe(viewLifecycleOwner, Observer {
            adapter.submitList(it.data)
        })
    }
}
