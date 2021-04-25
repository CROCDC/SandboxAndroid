package com.cr.o.cdc.sandboxAndroid.downdetector.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.cr.o.cdc.sandboxAndroid.databinding.BottomDialogAddSiteBinding
import com.cr.o.cdc.sandboxAndroid.downdetector.db.model.Site
import com.cr.o.cdc.sandboxAndroid.downdetector.utils.formatToString
import com.cr.o.cdc.sandboxAndroid.downdetector.vm.AddSiteViewModel
import com.cr.o.cdc.sandboxAndroid.getInput
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddSiteBottomDialog : BottomSheetDialogFragment() {

    private lateinit var binding: BottomDialogAddSiteBinding

    private val viewModel: AddSiteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomDialogAddSiteBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.inputIntervalCheck.setOnFocusChangeListener { _, _ ->

            binding.inputIntervalCheck.getInput().toIntOrNull()?.takeIf { it < 15 }?.let {
                binding.inputIntervalCheck.setText(15.formatToString())
            }
        }

        binding.add.setOnClickListener {
            // TODO CHECKS
            viewModel.saveSite(
                Site(
                    binding.inputAddress.getInput(),
                    binding.inputName.getInput(),
                    true,
                    binding.inputIntervalCheck.getInput().toInt(),
                    0,
                    null,
                    null,
                    null,
                    null
                )
            )

            dismiss()
        }
    }
}
