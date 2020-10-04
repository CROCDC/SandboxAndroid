package com.cr.o.cdc.sandboxAndroid.preguntadoshelper.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.cr.o.cdc.sandboxAndroid.databinding.FragmentPreguntadosQuestionListBinding
import com.cr.o.cdc.sandboxAndroid.preguntadoshelper.ui.PreguntadosQuestionAdapter
import com.cr.o.cdc.sandboxAndroid.preguntadoshelper.vm.PreguntadosQuestionListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PreguntadosQuestionListFragment : Fragment() {

    private lateinit var binding: FragmentPreguntadosQuestionListBinding

    private lateinit var adapter: PreguntadosQuestionAdapter

    private val viewModel: PreguntadosQuestionListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPreguntadosQuestionListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = PreguntadosQuestionAdapter()

        binding.recycler.adapter = adapter
        viewModel.preguntadosQuestions.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

    }
}