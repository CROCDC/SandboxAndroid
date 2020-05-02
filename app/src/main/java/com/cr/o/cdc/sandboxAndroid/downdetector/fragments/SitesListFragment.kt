package com.cr.o.cdc.sandboxAndroid.downdetector.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.cr.o.cdc.annotations.Injectable
import com.cr.o.cdc.sandboxAndroid.databinding.FragmentSitesListBinding
import com.cr.o.cdc.sandboxAndroid.downdetector.ui.SiteAdapter
import com.cr.o.cdc.sandboxAndroid.downdetector.vm.SitesListViewModel
import javax.inject.Inject

@Injectable
class SitesListFragment : Fragment(), SiteAdapter.SiteAdapterListener {

    private lateinit var binding: FragmentSitesListBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: SitesListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSitesListBinding.inflate(inflater, container, false)

        binding.btnAdd.setOnClickListener {
            AddSiteBottomDialog().show(childFragmentManager, null)
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(SitesListViewModel::class.java)

        val adapter = SiteAdapter(this)

        binding.recycler.adapter = adapter

        viewModel.sites.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

    }

    override fun modifyEnable(address: String, enable: Boolean) {
        viewModel.modifyEnable(address, enable)
    }

}