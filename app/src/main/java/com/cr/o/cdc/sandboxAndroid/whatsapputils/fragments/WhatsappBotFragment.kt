package com.cr.o.cdc.sandboxAndroid.whatsapputils.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.cr.o.cdc.sandboxAndroid.R
import com.cr.o.cdc.sandboxAndroid.databinding.FragmentWhatsappBotBinding
import com.cr.o.cdc.sandboxAndroid.whatsapputils.ui.WhatsappMessagesBotAdapter
import com.cr.o.cdc.sandboxAndroid.whatsapputils.vm.WhatsappBotViewModel

class WhatsappBotFragment : Fragment() {

    private lateinit var binding: FragmentWhatsappBotBinding

    private val viewModel: WhatsappBotViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWhatsappBotBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.isEnabled.observe(viewLifecycleOwner, Observer {
            binding.btnWhatsappBot.setText(
                if (it) {
                    R.string.active
                } else {
                    R.string.inactive
                }
            )
        })

        val adapter = WhatsappMessagesBotAdapter()
        binding.recyclerWhatsappBotMessages.adapter = adapter
        viewModel.whatsappBotMessages.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        binding.btnWhatsappBot.setOnClickListener {
            viewModel.modify()
        }

        binding.btnAdd.setOnClickListener {
            findNavController().navigate(
                R.id.action_whatsappBotFragment_to_addWhatsappMessageBotFragment
            )
        }
    }
}
