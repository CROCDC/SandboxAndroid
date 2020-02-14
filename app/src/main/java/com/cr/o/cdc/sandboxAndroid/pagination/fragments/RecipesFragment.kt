package com.cr.o.cdc.sandboxAndroid.pagination.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.cr.o.cdc.annotations.Injectable
import com.cr.o.cdc.requests.StatusResult
import com.cr.o.cdc.sandboxAndroid.databinding.FragmentRecipesBinding
import com.cr.o.cdc.sandboxAndroid.pagination.ui.RecipeAdapter
import com.cr.o.cdc.sandboxAndroid.pagination.vm.RecipeViewModel
import javax.inject.Inject

@Injectable
class RecipesFragment : Fragment() {


    private lateinit var binding: FragmentRecipesBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecipesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val vm = ViewModelProviders.of(this, viewModelFactory).get(RecipeViewModel::class.java)

        val adapter = RecipeAdapter()
        binding.recycler.adapter = adapter

        vm.recipes.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        vm.loading.observe(viewLifecycleOwner, Observer {
            binding.progressbar.visibility = if (it != StatusResult.LOADING) {
                View.GONE
            } else {
                View.VISIBLE
            }
        })
    }
}
