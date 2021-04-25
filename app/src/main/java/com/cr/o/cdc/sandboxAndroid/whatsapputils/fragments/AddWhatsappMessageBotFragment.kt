package com.cr.o.cdc.sandboxAndroid.whatsapputils.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.cr.o.cdc.sandboxAndroid.databinding.FragmentAddWhatsappMessageBotBinding
import com.cr.o.cdc.sandboxAndroid.whatsapputils.db.model.WhatsappMessageBot
import com.cr.o.cdc.sandboxAndroid.whatsapputils.vm.AddWhatsappMessageBotViewModel

class AddWhatsappMessageBotFragment : Fragment() {

    private val viewModel: AddWhatsappMessageBotViewModel by viewModels()

    private lateinit var binding: FragmentAddWhatsappMessageBotBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddWhatsappMessageBotBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAdd.setOnClickListener {
            viewModel.add(
                WhatsappMessageBot(
                    binding.inputContact.text.toString(),
                    binding.inputResponse.text.toString(),
                    binding.inputMessage.text.toString()
                )
            )
            requireActivity().onBackPressed()
        }
    }
}
