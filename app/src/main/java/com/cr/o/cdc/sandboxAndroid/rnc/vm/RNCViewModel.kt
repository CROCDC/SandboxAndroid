package com.cr.o.cdc.sandboxAndroid.rnc.vm

import androidx.lifecycle.ViewModel
import com.cr.o.cdc.sandboxAndroid.rnc.model.Place
import com.cr.o.cdc.sandboxAndroid.rnc.repos.RNCRepository
import javax.inject.Inject

class RNCViewModel @Inject constructor(private val mapRepository: RNCRepository) : ViewModel() {

    fun getPlaces(): List<Place> = mapRepository.getPlaces()
}