package com.cr.o.cdc.sandboxAndroid.rnc.repos

import com.cr.o.cdc.sandboxAndroid.rnc.model.Place

interface RNCDataSourceProvider {

    fun getPlaces(): List<Place>
}