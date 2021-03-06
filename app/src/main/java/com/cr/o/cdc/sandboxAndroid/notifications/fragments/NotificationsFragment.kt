package com.cr.o.cdc.sandboxAndroid.notifications.fragments

import android.content.BroadcastReceiver
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.cr.o.cdc.sandboxAndroid.databinding.FragmentNotificationsBinding
import com.cr.o.cdc.sandboxAndroid.notifications.vm.NotificationsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotificationsFragment : Fragment() {

    private lateinit var broadcastReceiver: BroadcastReceiver

    private lateinit var binding: FragmentNotificationsBinding

    private val viewModel: NotificationsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotificationsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        broadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                binding.txtMsgNotification.text = intent.extras?.getString(EXTRA_MSG_NOTIFICATION)
            }
        }

        viewModel.token.observe(viewLifecycleOwner, Observer { pushToken ->
            binding.txtPushToken.text = pushToken

            binding.btnCopy.setOnClickListener {
                getSystemService(requireContext(), ClipboardManager::class.java)?.setPrimaryClip(
                    ClipData.newPlainText("pushToken", pushToken)
                )
                Toast.makeText(requireContext(), "Copied $pushToken", Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onResume() {
        super.onResume()
        LocalBroadcastManager.getInstance(requireContext())
            .registerReceiver(broadcastReceiver, IntentFilter(BROADCAST_RECEIVER))
    }

    override fun onStop() {
        super.onStop()
        try {
            LocalBroadcastManager.getInstance(requireContext())
                .unregisterReceiver(broadcastReceiver)
        } catch (ignored: Exception) {
        }
    }

    companion object {
        const val BROADCAST_RECEIVER = "BROADCAST_RECEIVER"
        const val EXTRA_MSG_NOTIFICATION = "MSG_NOTIFICATION"
    }
}
