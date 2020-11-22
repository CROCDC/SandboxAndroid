package com.cr.o.cdc.sandboxAndroid

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.cr.o.cdc.sandboxAndroid.databinding.DialogTextInputBinding

class TextInputDialog : DialogFragment() {

    interface TextInputDialogListener {
        fun send(input: String)
    }

    private var listener: TextInputDialogListener? = null

    private lateinit var binding: DialogTextInputBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = targetFragment as TextInputDialogListener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogTextInputBinding.inflate(inflater, container, false)
        binding.btnSend.setOnClickListener {
            listener?.send(binding.input.text.toString())
            dismiss()
        }
        return binding.root
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

}
