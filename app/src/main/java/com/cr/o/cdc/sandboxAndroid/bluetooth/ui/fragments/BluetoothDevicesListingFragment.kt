package com.cr.o.cdc.sandboxAndroid.bluetooth.ui.fragments

import android.bluetooth.BluetoothDevice
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.cr.o.cdc.sandboxAndroid.R
import com.cr.o.cdc.sandboxAndroid.bluetooth.ui.adapters.BluetoothDeviceAdapter
import com.cr.o.cdc.sandboxAndroid.bluetooth.vm.BluetoothDevicesListingViewModel
import com.cr.o.cdc.sandboxAndroid.databinding.FragmentBluetoothDevicesListingBinding
import com.github.douglasjunior.bluetoothclassiclibrary.BluetoothStatus
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BluetoothDevicesListingFragment : Fragment() {

    private val viewModel: BluetoothDevicesListingViewModel by viewModels()

    private lateinit var binding: FragmentBluetoothDevicesListingBinding

    private lateinit var adapter: BluetoothDeviceAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBluetoothDevicesListingBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = BluetoothDeviceAdapter(
            object : BluetoothDeviceAdapter.BluetoothDeviceAdapterListener {
                override fun connect(bluetoothDevice: BluetoothDevice) {
                    viewModel.connect(bluetoothDevice).observe(viewLifecycleOwner, Observer {
                        Toast.makeText(
                            requireContext(), when (it.status) {
                                BluetoothStatus.CONNECTED -> requireContext().getString(
                                    R.string.connected_to,
                                    it.deviceName
                                )
                                BluetoothStatus.CONNECTING -> requireContext().getString(R.string.connecting)
                                else ->requireContext().getString(R.string.fail_connection)
                            }, Toast.LENGTH_LONG
                        ).show()
                    })
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
            viewModel.startScan().observe(viewLifecycleOwner, Observer {
                adapter.submitList(it.devices.toList())
            })
        } else {
            requestPermissions(
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                ACCESS_FINE_LOCATION_CODE
            )
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (ACCESS_FINE_LOCATION_CODE == requestCode) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                viewModel.startScan().observe(viewLifecycleOwner, Observer {
                    adapter.submitList(it.devices.toList())
                })
            }
        }
    }

    companion object {
        private const val ACCESS_FINE_LOCATION_CODE = 2020
    }
}