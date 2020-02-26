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
import com.cr.o.cdc.sandboxAndroid.databinding.FragmentWhatsappMesaggesBinding
import com.cr.o.cdc.sandboxAndroid.whatsapputils.ui.WhatsappMessageAdapter
import com.cr.o.cdc.sandboxAndroid.whatsapputils.vm.WhatsappMesaggesViewModel
import javax.inject.Inject

@Injectable
class WhatsappMesaggesFragment : Fragment() {

    private lateinit var binding: FragmentWhatsappMesaggesBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWhatsappMesaggesBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModel: WhatsappMesaggesViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(WhatsappMesaggesViewModel::class.java)

        val adapter = WhatsappMessageAdapter()

        binding.recycler.adapter = adapter

        viewModel.whatsappMessages.observe(this, Observer {
            adapter.submitList(it)
        })
    }
}
