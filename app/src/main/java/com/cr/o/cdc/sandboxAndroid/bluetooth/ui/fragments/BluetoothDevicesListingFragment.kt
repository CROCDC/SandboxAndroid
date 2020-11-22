package com.cr.o.cdc.sandboxAndroid.bluetooth.ui.fragments

import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothDevice.ACTION_FOUND
import android.bluetooth.BluetoothDevice.EXTRA_DEVICE
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.cr.o.cdc.sandboxAndroid.bluetooth.ui.adapters.BluetoothDeviceAdapter
import com.cr.o.cdc.sandboxAndroid.bluetooth.util.getBluetoothManager
import com.cr.o.cdc.sandboxAndroid.bluetooth.vm.BluetoothDevicesListingViewModel
import com.cr.o.cdc.sandboxAndroid.databinding.FragmentBluetoothDevicesListingBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BluetoothDevicesListingFragment : Fragment() {

    private val viewModel: BluetoothDevicesListingViewModel by viewModels()

    private lateinit var binding: FragmentBluetoothDevicesListingBinding

    private lateinit var adapter: BluetoothDeviceAdapter

    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            intent.takeIf { intent.action == ACTION_FOUND }
                ?.getParcelableExtra<BluetoothDevice?>(EXTRA_DEVICE)?.let {
                    viewModel.addDevice(it)
                }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBluetoothDevicesListingBinding.inflate(inflater, container, false)

        scan()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = BluetoothDeviceAdapter(
            object : BluetoothDeviceAdapter.BluetoothDeviceAdapterListener {
                override fun connect(bluetoothDevice: BluetoothDevice) {
                    val bluetoothManager = requireContext().getBluetoothManager()
                    bluetoothManager?.let { viewModel.connect(bluetoothDevice) }
                }

                override fun sendMessage(bluetoothDevice: BluetoothDevice) {
                    findNavController().navigate(
                        BluetoothDevicesListingFragmentDirections
                            .actionBluetoothDevicesListingFragmentToSendMessageToDeviceFragment(
                                bluetoothDevice.address
                            )
                    )

                }
            })
        binding.recycler.adapter = adapter
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            scan()
        } else {
            requestPermissions(
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                ACCESS_FINE_LOCATION_CODE
            )
        }
        viewModel.devices.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it.toList())
        })
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (ACCESS_FINE_LOCATION_CODE == requestCode) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                scan()
            }
        }
    }

    private fun scan() {
        requireContext().getBluetoothManager()?.adapter?.startDiscovery()
        requireContext().registerReceiver(receiver, IntentFilter(ACTION_FOUND))

    }

    override fun onDestroy() {
        super.onDestroy()
        requireContext().unregisterReceiver(receiver)
    }

    companion object {
        private const val ACCESS_FINE_LOCATION_CODE = 2020
    }
}