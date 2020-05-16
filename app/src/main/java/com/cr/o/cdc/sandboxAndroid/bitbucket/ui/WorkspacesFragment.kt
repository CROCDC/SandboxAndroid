package com.cr.o.cdc.sandboxAndroid.bitbucket.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.cr.o.cdc.sandboxAndroid.bitbucket.vm.WorkspacesViewModel
import com.cr.o.cdc.sandboxAndroid.databinding.FragmentWorkspacesBinding
import com.cr.o.cdc.sandboxAndroid.di.Injectable
import javax.inject.Inject

@Injectable
class WorkspacesFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: FragmentWorkspacesBinding

    private lateinit var viewModel: WorkspacesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWorkspacesBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(WorkspacesViewModel::class.java)

        val adapter = WorkspacesAdapter()
        binding.recycler.adapter = adapter
        viewModel.workspaces.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it.data)
        })
    }
}
