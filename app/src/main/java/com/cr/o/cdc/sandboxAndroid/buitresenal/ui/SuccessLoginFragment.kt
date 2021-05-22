package com.cr.o.cdc.sandboxAndroid.buitresenal.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.cr.o.cdc.sandboxAndroid.R

class SuccessLoginFragment : Fragment() {

    private val arguments: SuccessLoginFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.e("Cami", arguments.code)
        return inflater.inflate(R.layout.fragment_success_login, container, false)
    }

}
