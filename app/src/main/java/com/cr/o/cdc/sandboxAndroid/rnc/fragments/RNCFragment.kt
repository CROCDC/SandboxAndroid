package com.cr.o.cdc.sandboxAndroid.rnc.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.cr.o.cdc.sandboxAndroid.R
import com.cr.o.cdc.sandboxAndroid.rnc.model.Place
import com.cr.o.cdc.sandboxAndroid.rnc.vm.RNCViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterManager

class RNCFragment : Fragment(), OnMapReadyCallback {

    private val viewModel: RNCViewModel by viewModels()

    private lateinit var clusterManager: ClusterManager<Place>

    override fun onMapReady(map: GoogleMap) {
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(-34.5939357, -58.3794686), 10F))

        clusterManager = ClusterManager(requireContext(), map)

        map.setOnCameraIdleListener(clusterManager)

        clusterManager.addItems(viewModel.getPlaces())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_rnc, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (childFragmentManager.fragments.find {
            it::class.java == SupportMapFragment::class.java
        } as SupportMapFragment).getMapAsync(this)
    }
}
