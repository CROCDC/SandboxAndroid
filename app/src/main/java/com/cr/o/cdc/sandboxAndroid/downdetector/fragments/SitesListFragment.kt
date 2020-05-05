package com.cr.o.cdc.sandboxAndroid.downdetector.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.cr.o.cdc.sandboxAndroid.R
import com.cr.o.cdc.sandboxAndroid.databinding.FragmentSitesListBinding
import com.cr.o.cdc.sandboxAndroid.di.Injectable
import com.cr.o.cdc.sandboxAndroid.downdetector.db.model.Site
import com.cr.o.cdc.sandboxAndroid.downdetector.model.PingResponse
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
        inflater: LayoutInflater,
        container: ViewGroup?,
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

    override fun modifyEnable(site: Site) {
        viewModel.modifyEnable(site)
    }

    override fun deleteSite(address: String) {
        viewModel.deleteSite(address)
    }

    override fun testSite(address: String) {
        viewModel.testSite(address).observe(viewLifecycleOwner, Observer {
            val context = requireContext()

            Toast.makeText(
                context,
                when (it) {
                    PingResponse.PingSuccess -> context.getString(
                        R.string.success_in_test_of, address
                    )
                    is PingResponse.PingError -> context.getString(
                        R.string.error_in_test_with_code, it.response
                    )
                    is PingResponse.PingException -> it.message
                },
                Toast.LENGTH_LONG
            ).show()
        })
    }
}
