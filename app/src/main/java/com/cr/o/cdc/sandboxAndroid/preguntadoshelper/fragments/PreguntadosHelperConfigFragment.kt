package com.cr.o.cdc.sandboxAndroid.preguntadoshelper.fragments

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cr.o.cdc.sandboxAndroid.databinding.FragmentPreguntadosHelperConfigBinding
import com.cr.o.cdc.sandboxAndroid.preguntadoshelper.service.PreguntadosHelperOverlayWidgetService
import javax.inject.Inject

class PreguntadosHelperConfigFragment : Fragment() {

    private lateinit var binding: FragmentPreguntadosHelperConfigBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPreguntadosHelperConfigBinding.inflate(inflater, container, false)

        binding.btnStart.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!Settings.canDrawOverlays(requireContext())) {
                    val intent = Intent(
                        Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:${requireContext().packageName}")
                    )
                    startActivityForResult(intent, 12345)
                } else {
                    requireActivity().startService(
                        Intent(
                            requireContext(),
                            PreguntadosHelperOverlayWidgetService::class.java
                        )
                    )
                }
            }
        }

        return binding.root
    }
}