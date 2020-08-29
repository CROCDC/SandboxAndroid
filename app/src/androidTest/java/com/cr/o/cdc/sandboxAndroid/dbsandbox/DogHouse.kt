package com.cr.o.cdc.sandboxAndroid.dbsandbox

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DogHouse(
    @PrimaryKey val id: String,
    val dogName: String,
    val positionDogHouse: Int
)

enum class PositionDogHouse {
    CABA
}
