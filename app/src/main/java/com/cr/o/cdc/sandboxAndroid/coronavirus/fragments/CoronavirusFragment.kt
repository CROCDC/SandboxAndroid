package com.cr.o.cdc.sandboxAndroid.coronavirus.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.cr.o.cdc.sandboxAndroid.R
import com.cr.o.cdc.sandboxAndroid.coronavirus.ui.CountryStatsAdapter
import com.cr.o.cdc.sandboxAndroid.coronavirus.vm.CoronavirusViewModel
import com.cr.o.cdc.sandboxAndroid.databinding.FragmentCoronavirusBinding
import com.cr.o.cdc.sandboxAndroid.di.Injectable
import javax.inject.Inject

@Injectable
class CoronavirusFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: FragmentCoronavirusBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCoronavirusBinding.inflate(inflater, container, false)

        binding.txtSearch.setOnClickListener {
            findNavController().navigate(R.id.action_coronavirusFragment_to_searchFragment)
        }
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val vm = ViewModelProviders.of(this, viewModelFactory).get(CoronavirusViewModel::class.java)

        val adapter = CountryStatsAdapter()

        binding.recycler.adapter = adapter

        vm.getCasesByCountry().observe(viewLifecycleOwner, Observer {
            adapter.submitList(it.data)
        })
    }
}
