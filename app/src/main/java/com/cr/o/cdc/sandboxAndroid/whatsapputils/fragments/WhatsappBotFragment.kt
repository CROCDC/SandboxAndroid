package com.cr.o.cdc.sandboxAndroid.whatsapputils.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.cr.o.cdc.annotations.Injectable
import com.cr.o.cdc.sandboxAndroid.R
import com.cr.o.cdc.sandboxAndroid.databinding.FragmentWhatsappBotBinding
import com.cr.o.cdc.sandboxAndroid.whatsapputils.ui.WhatsappMessagesBotAdapter
import com.cr.o.cdc.sandboxAndroid.whatsapputils.vm.WhatsappBotViewModel
import javax.inject.Inject

@Injectable
class WhatsappBotFragment : Fragment() {

    private lateinit var binding: FragmentWhatsappBotBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWhatsappBotBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val vm = ViewModelProviders.of(this, viewModelFactory).get(WhatsappBotViewModel::class.java)

        vm.isEnabled.observe(viewLifecycleOwner, Observer {
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
        vm.whatsappBotMessages.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        binding.btnWhatsappBot.setOnClickListener {
            vm.modify()
        }

        binding.btnAdd.setOnClickListener {
            findNavController().navigate(R.id.action_whatsappBotFragment_to_addWhatsappMessageBotFragment)
        }


    }
}
