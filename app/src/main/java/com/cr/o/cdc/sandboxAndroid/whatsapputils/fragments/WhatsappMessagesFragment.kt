package com.cr.o.cdc.sandboxAndroid.whatsapputils.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.cr.o.cdc.sandboxAndroid.databinding.FragmentWhatsappMessagesBinding
import com.cr.o.cdc.sandboxAndroid.whatsapputils.ui.WhatsappMessageAdapter
import com.cr.o.cdc.sandboxAndroid.whatsapputils.vm.WhatsappMessagesViewModel

class WhatsappMessagesFragment : Fragment() {

    private lateinit var binding: FragmentWhatsappMessagesBinding

    private val viewModel: WhatsappMessagesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWhatsappMessagesBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = WhatsappMessageAdapter()

        binding.recycler.adapter = adapter

        viewModel.whatsappMessages.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }
}
