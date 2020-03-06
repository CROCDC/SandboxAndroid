package com.cr.o.cdc.sandboxAndroid.rnc.repos

import com.cr.o.cdc.sandboxAndroid.rnc.model.Coordinates
import com.cr.o.cdc.sandboxAndroid.rnc.model.Place

class MapDataSource : MapDataSourceProvider {


    override fun getPlaces(): List<Place> = listOf(
        Place(
            Coordinates(
                -34.5938392,
                -58.3791293
            ),
            "fake name Leo Messi",
            "Ministerio de Relaciones Exteriores y Culto"
        ),
        Place(
            Coordinates(
                -34.593945,
                -58.379824
            ),
            "fake name Maradona",
            "La Fenice"
        ),
        Place(
            Coordinates(
                -34.592955,
                -58.379701
            ),
            "fake name Gallardo",
            "Argenta Tower Hotel"
        ),
        Place(
            Coordinates(
                -34.593034,
                -58.378679
            ),
            "fake name Ponzio",
            "Deli Wok"
        ),
        Place(
            Coordinates(
                -34.593161,
                -58.379886
            ),
            "fake name Scocco",
            "Basílica de Nuestra Señora del Socorro"
        )
    )

}