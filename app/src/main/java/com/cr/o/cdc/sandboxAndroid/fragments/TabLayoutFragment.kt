package com.cr.o.cdc.sandboxAndroid.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cr.o.cdc.sandboxAndroid.databinding.FragmentTabLayoutBinding
import com.google.android.material.tabs.TabLayout

class TabLayoutFragment : Fragment() {

    private lateinit var binding: FragmentTabLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTabLayoutBinding.inflate(inflater, container, false)

        binding.tabLayout.addOnTabSelectedListener(object :
            TabLayout.BaseOnTabSelectedListener<TabLayout.Tab> {
            override fun onTabReselected(tab: TabLayout.Tab) {}

            override fun onTabUnselected(tab: TabLayout.Tab) {}

            override fun onTabSelected(tab: TabLayout.Tab) {
                Log.e("CdeAmerica", tab.position.toString())
            }
        })

        binding.tabLayout.addTab(binding.tabLayout.newTab().apply {
            text = "1"
        }, false)
        binding.tabLayout.addTab(binding.tabLayout.newTab().apply {
            text = "2"
        }, false)
        binding.tabLayout.addTab(binding.tabLayout.newTab().apply {
            text = "3"
        }, false)

        return binding.root
    }
}
