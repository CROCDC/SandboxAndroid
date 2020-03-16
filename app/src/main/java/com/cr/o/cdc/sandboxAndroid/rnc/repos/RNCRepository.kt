package com.cr.o.cdc.sandboxAndroid.rnc.repos

import com.cr.o.cdc.sandboxAndroid.rnc.model.Place
import javax.inject.Inject

class RNCRepository @Inject constructor(private val dataSource: RNCDataSourceProvider) {


    fun getPlaces(): List<Place> = dataSource.getPlaces()
}