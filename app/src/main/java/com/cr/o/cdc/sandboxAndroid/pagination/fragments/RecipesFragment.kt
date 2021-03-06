package com.cr.o.cdc.sandboxAndroid.pagination.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.cr.o.cdc.sandboxAndroid.databinding.FragmentRecipesBinding
import com.cr.o.cdc.sandboxAndroid.libraries.networking.StatusResult
import com.cr.o.cdc.sandboxAndroid.pagination.ui.RecipeAdapter
import com.cr.o.cdc.sandboxAndroid.pagination.vm.RecipeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipesFragment : Fragment() {

    private lateinit var binding: FragmentRecipesBinding

    private val viewModel: RecipeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecipesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = RecipeAdapter()
        binding.recycler.adapter = adapter

        viewModel.recipes.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
        binding.input.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.setSearch(s.toString())
            }
        })
        binding.btnRefresh.setOnClickListener {
            viewModel.refresh()
        }

        viewModel.loading.observe(viewLifecycleOwner, Observer {
            binding.progressbar.visibility =
                if (it != StatusResult.LOADING) {
                    View.GONE
                } else {
                    View.VISIBLE
                }
        })
    }
}
