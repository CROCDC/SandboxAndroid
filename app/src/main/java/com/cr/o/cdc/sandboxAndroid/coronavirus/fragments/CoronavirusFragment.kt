package com.cr.o.cdc.sandboxAndroid.coronavirus.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.cr.o.cdc.annotations.Injectable
import com.cr.o.cdc.networking.RetrofitSuccessResponse
import com.cr.o.cdc.sandboxAndroid.coronavirus.ui.CountryStatsAdapter
import com.cr.o.cdc.sandboxAndroid.coronavirus.vm.CoronavirusViewModel
import com.cr.o.cdc.sandboxAndroid.databinding.FragmentCoronavirusBinding
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

        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val vm = ViewModelProviders.of(this, viewModelFactory).get(CoronavirusViewModel::class.java)

        val adapter = CountryStatsAdapter()

        binding.recycler.adapter = adapter

        vm.getCasesByCountry().observe(viewLifecycleOwner, Observer {
            adapter.submitList((it as? RetrofitSuccessResponse)?.data?.countries_stat)
        })
    }
}
