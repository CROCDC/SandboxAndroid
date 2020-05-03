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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.cr.o.cdc.sandboxAndroid.databinding.FragmentNotificationsBinding
import com.cr.o.cdc.sandboxAndroid.di.Injectable
import com.cr.o.cdc.sandboxAndroid.notifications.vm.NotificationsViewModel
import javax.inject.Inject

@Injectable
class NotificationsFragment : Fragment() {

    private lateinit var broadcastReceiver: BroadcastReceiver

    private lateinit var binding: FragmentNotificationsBinding

    private lateinit var viewModel: NotificationsViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotificationsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(NotificationsViewModel::class.java)

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