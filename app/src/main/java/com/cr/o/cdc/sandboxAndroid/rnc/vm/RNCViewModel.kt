package com.cr.o.cdc.sandboxAndroid.rnc.vm

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.cr.o.cdc.sandboxAndroid.rnc.model.Place
import com.cr.o.cdc.sandboxAndroid.rnc.repos.RNCRepository

class RNCViewModel @ViewModelInject constructor(private val mapRepository: RNCRepository) :
    ViewModel() {

    fun getPlaces(): List<Place> = mapRepository.getPlaces()
}
