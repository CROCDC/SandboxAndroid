package com.cr.o.cdc.sandboxAndroid.rnc.vm

import androidx.lifecycle.ViewModel
import com.cr.o.cdc.sandboxAndroid.rnc.model.Place
import com.cr.o.cdc.sandboxAndroid.rnc.repos.MapRepository
import javax.inject.Inject

class MapViewModel @Inject constructor(private val mapRepository: MapRepository) : ViewModel() {

    fun getPlaces(): List<Place> = mapRepository.getPlaces()
}