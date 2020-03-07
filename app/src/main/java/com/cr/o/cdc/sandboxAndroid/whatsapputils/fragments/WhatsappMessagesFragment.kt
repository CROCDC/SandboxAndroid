package com.cr.o.cdc.sandboxAndroid.whatsapputils.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.cr.o.cdc.annotations.Injectable
import com.cr.o.cdc.sandboxAndroid.databinding.FragmentWhatsappMessagesBinding
import com.cr.o.cdc.sandboxAndroid.whatsapputils.ui.WhatsappMessageAdapter
import com.cr.o.cdc.sandboxAndroid.whatsapputils.vm.WhatsappMessagesViewModel
import javax.inject.Inject

@Injectable
class WhatsappMessagesFragment : Fragment() {

    private lateinit var binding: FragmentWhatsappMessagesBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWhatsappMessagesBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModel: WhatsappMessagesViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(WhatsappMessagesViewModel::class.java)

        val adapter = WhatsappMessageAdapter()

        binding.recycler.adapter = adapter

        viewModel.whatsappMessages.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }
}
