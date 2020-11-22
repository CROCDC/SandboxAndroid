package com.cr.o.cdc.sandboxAndroid.bluetooth.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.cr.o.cdc.sandboxAndroid.TextInputDialog
import com.cr.o.cdc.sandboxAndroid.bluetooth.db.model.BluetoothMessage
import com.cr.o.cdc.sandboxAndroid.bluetooth.ui.adapters.BluetoothMessageAdapter
import com.cr.o.cdc.sandboxAndroid.bluetooth.vm.BluetoothDevicesListingViewModel
import com.cr.o.cdc.sandboxAndroid.databinding.FragmentSendMessageToDeviceBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SendMessageToDeviceFragment : Fragment(), TextInputDialog.TextInputDialogListener {

    private lateinit var binding: FragmentSendMessageToDeviceBinding

    val args: SendMessageToDeviceFragmentArgs by navArgs()

    private val viewModel: BluetoothDevicesListingViewModel by viewModels()

    private lateinit var adapter: BluetoothMessageAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSendMessageToDeviceBinding.inflate(inflater, container, false)

        val macAddress = args.address
        binding.txtMacAdress.text = macAddress

        binding.btnSend.setOnClickListener {
            viewModel.sendMessage(binding.input.text.toString(), macAddress)
        }
        adapter = BluetoothMessageAdapter(object :
            BluetoothMessageAdapter.BluetoothMessageAdapterListener {
            override fun select(bluetoothMessage: BluetoothMessage) {
                viewModel.sendMessage(bluetoothMessage.message, macAddress)
            }
        })

        binding.recycler.adapter = adapter

        viewModel.bluetoothMessages.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        binding.imgAdd.setOnClickListener {
            TextInputDialog().apply {
                setTargetFragment(this@SendMessageToDeviceFragment, 2020)
            }.show(parentFragmentManager, null)
        }

        return binding.root
    }

    override fun send(input: String) {
        viewModel.addBluetoothMessage(input)
    }

}
