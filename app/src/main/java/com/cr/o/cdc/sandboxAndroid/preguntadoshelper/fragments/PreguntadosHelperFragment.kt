package com.cr.o.cdc.sandboxAndroid.preguntadoshelper.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.cr.o.cdc.sandboxAndroid.R
import com.cr.o.cdc.sandboxAndroid.databinding.FragmentPreguntadosHelperBinding

class PreguntadosHelperFragment : Fragment() {

    private lateinit var binding: FragmentPreguntadosHelperBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPreguntadosHelperBinding.inflate(inflater, container, false)

        binding.btnConfig.setOnClickListener {
            findNavController().navigate(R.id.action_preguntadosHelperFragment_to_preguntadosHelperConfigFragment)
        }

        binding.btnQuestionsList.setOnClickListener {
            findNavController().navigate(R.id.action_preguntadosHelperFragment_to_preguntadosQuestionListFragment)
        }

        return binding.root
    }

}