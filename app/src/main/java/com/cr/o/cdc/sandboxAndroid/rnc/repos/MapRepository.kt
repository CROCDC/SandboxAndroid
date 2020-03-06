package com.cr.o.cdc.sandboxAndroid.rnc.repos

import com.cr.o.cdc.sandboxAndroid.rnc.model.Place
import javax.inject.Inject

class MapRepository @Inject constructor(private val dataSource: MapDataSourceProvider) {


    fun getPlaces(): List<Place> = dataSource.getPlaces()
}