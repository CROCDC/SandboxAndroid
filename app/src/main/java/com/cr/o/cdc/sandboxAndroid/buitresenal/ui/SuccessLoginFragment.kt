package com.cr.o.cdc.sandboxAndroid.buitresenal.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.cr.o.cdc.sandboxAndroid.buitresenal.vm.SuccessLoginViewModel
import com.cr.o.cdc.sandboxAndroid.databinding.FragmentSuccessLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SuccessLoginFragment : Fragment() {

    private val arguments: SuccessLoginFragmentArgs by navArgs()

    private val viewModel: SuccessLoginViewModel by viewModels()

    private lateinit var binding: FragmentSuccessLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSuccessLoginBinding.inflate(inflater, container, false)

        viewModel.getUser(arguments.code).observe(
            viewLifecycleOwner,
            Observer {
                binding.txtUsername.text = it.data?.username
            }
        )

        return binding.root
    }

}
