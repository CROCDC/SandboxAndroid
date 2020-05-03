package com.cr.o.cdc.sandboxAndroid.whatsapputils.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.cr.o.cdc.sandboxAndroid.databinding.FragmentAddWhatsappMessageBotBinding
import com.cr.o.cdc.sandboxAndroid.di.Injectable
import com.cr.o.cdc.sandboxAndroid.whatsapputils.db.model.WhatsappMessageBot
import com.cr.o.cdc.sandboxAndroid.whatsapputils.vm.AddWhatsappMessageBotViewModel
import javax.inject.Inject

@Injectable
class AddWhatsappMessageBotFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: FragmentAddWhatsappMessageBotBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddWhatsappMessageBotBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val vm = ViewModelProviders.of(this, viewModelFactory)
            .get(AddWhatsappMessageBotViewModel::class.java)

        binding.btnAdd.setOnClickListener {
            vm.add(
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
