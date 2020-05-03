package com.cr.o.cdc.sandboxAndroid.customviews.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.cr.o.cdc.sandboxAndroid.R
import com.cr.o.cdc.sandboxAndroid.databinding.FragmentCustomViewsBinding

class CustomViewsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCustomViewsBinding.inflate(inflater, container, false)

        binding.btnSmiles.setOnClickListener {
            findNavController().navigate(R.id.action_customViewsFragment_to_smilesFragment)
        }

        binding.btnCurve.setOnClickListener {
            findNavController().navigate(R.id.action_customViewsFragment_to_curveFragment)
        }

        return binding.root
    }
}
