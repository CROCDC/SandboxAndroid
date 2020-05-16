package com.cr.o.cdc.sandboxAndroid.bitbucket.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cr.o.cdc.sandboxAndroid.databinding.DialogOAuthInputDialogBinding

class OAuthInputDialog : Fragment() {

    private lateinit var binding: DialogOAuthInputDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogOAuthInputDialogBinding.inflate(layoutInflater, container, false)


        return binding.root
    }

}
