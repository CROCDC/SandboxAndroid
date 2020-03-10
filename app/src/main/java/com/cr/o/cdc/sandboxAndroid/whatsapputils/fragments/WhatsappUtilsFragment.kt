package com.cr.o.cdc.sandboxAndroid.whatsapputils.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.cr.o.cdc.sandboxAndroid.R

class WhatsappUtilsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_whatsapp_utils, container, false).also {
        it.findViewById<View>(R.id.btn_whatsapp_messages).setOnClickListener {
            findNavController().navigate(R.id.action_whatsappUtilsFragment_to_whatsappMessagesFragment)
        }
        it.findViewById<View>(R.id.btn_whatsapp_bot).setOnClickListener {
            findNavController().navigate(R.id.action_whatsappUtilsFragment_to_whatsappBotFragment)
        }
    }

}
