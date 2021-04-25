package com.cr.o.cdc.sandboxAndroid.rnc.vm

import androidx.lifecycle.ViewModel
import com.cr.o.cdc.sandboxAndroid.rnc.model.Place
import com.cr.o.cdc.sandboxAndroid.rnc.repos.RNCRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RNCViewModel @Inject constructor(private val mapRepository: RNCRepository) :
    ViewModel() {

    fun getPlaces(): List<Place> = mapRepository.getPlaces()
}
