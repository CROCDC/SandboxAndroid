package com.cr.o.cdc.sandboxAndroid.coronavirus.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.cr.o.cdc.sandboxAndroid.coronavirus.ui.CountryStatsAdapter
import com.cr.o.cdc.sandboxAndroid.coronavirus.vm.SearchViewModel
import com.cr.o.cdc.sandboxAndroid.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding

    private lateinit var viewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val adapter = CountryStatsAdapter()
        binding.recycler.adapter = adapter
        viewModel.list.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        binding.input.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.setSearch(s.toString())
            }
        })
    }
}
