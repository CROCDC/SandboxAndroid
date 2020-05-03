package com.cr.o.cdc.sandboxAndroid.rnc.model

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem

data class Place(
    val coordinates: Coordinates,
    val personName: String,
    val name: String
) : ClusterItem {

    override fun getSnippet(): String = personName

    override fun getTitle(): String = name

    override fun getPosition(): LatLng = LatLng(coordinates.lat, coordinates.long)
}
