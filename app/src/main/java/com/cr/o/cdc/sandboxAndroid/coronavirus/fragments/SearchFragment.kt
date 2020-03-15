package com.cr.o.cdc.sandboxAndroid.coronavirus.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.cr.o.cdc.annotations.Injectable
import com.cr.o.cdc.sandboxAndroid.coronavirus.ui.CountryStatsAdapter
import com.cr.o.cdc.sandboxAndroid.coronavirus.vm.SearchViewModel
import com.cr.o.cdc.sandboxAndroid.databinding.FragmentSearchBinding
import javax.inject.Inject

@Injectable
class SearchFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: FragmentSearchBinding

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
        val vm = ViewModelProviders.of(this, viewModelFactory).get(SearchViewModel::class.java)

        val adapter = CountryStatsAdapter()
        binding.recycler.adapter = adapter
        vm.list.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        binding.input.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                vm.setSearch(s.toString())
            }
        })

    }
}
