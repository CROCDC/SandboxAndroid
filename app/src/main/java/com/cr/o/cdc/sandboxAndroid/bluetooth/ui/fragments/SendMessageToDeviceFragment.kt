package com.cr.o.cdc.sandboxAndroid.bluetooth.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.cr.o.cdc.sandboxAndroid.databinding.FragmentSendMessageToDeviceBinding

class SendMessageToDeviceFragment : Fragment() {

    private lateinit var binding: FragmentSendMessageToDeviceBinding

    val args: SendMessageToDeviceFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSendMessageToDeviceBinding.inflate(inflater, container, false)

        binding.txtMacAdress.text = args.address

        return binding.root
    }

}
