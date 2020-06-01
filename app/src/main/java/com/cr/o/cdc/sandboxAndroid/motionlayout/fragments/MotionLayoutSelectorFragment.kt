package com.cr.o.cdc.sandboxAndroid.motionlayout.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.cr.o.cdc.sandboxAndroid.R
import com.cr.o.cdc.sandboxAndroid.databinding.FragmentMotionLayoutSelectorBinding

class MotionLayoutSelectorFragment : Fragment() {

    private lateinit var binding: FragmentMotionLayoutSelectorBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMotionLayoutSelectorBinding.inflate(inflater, container, false)

        binding.btnSpaceRocket.setOnClickListener {
            findNavController().navigate(
                R.id.action_motionLayoutSelectorFragment_to_spaceRocketFragment
            )
        }

        return binding.root
    }

}
