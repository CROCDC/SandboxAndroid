package com.cr.o.cdc.sandboxAndroid.dbsandbox.fake

import com.cr.o.cdc.sandboxAndroid.dbsandbox.DogHouse
import com.cr.o.cdc.sandboxAndroid.dbsandbox.PositionDogHouse
import com.cr.o.cdc.sharedtest.Dog
import com.cr.o.cdc.sharedtest.RatingBrokenOfGson

object MockFactoryDBSandbox {

    fun getDog(name: String? = null, age: Int? = null): Dog = Dog(name ?: "name", age ?: 15)

    fun getListOfDogs(): List<Dog> = listOf(getDog(), getDog("Leo"))

    fun getRatingBrokenOfGson(id: String? = null): RatingBrokenOfGson =
        RatingBrokenOfGson(id ?: "1", "FIVE")

    fun getDogHouse(): DogHouse {
        val dog = getDog()
        val positionDogHouse = PositionDogHouse.CABA

        return DogHouse(
            "${dog.name}${positionDogHouse.hashCode()}",
            dog.name,
            positionDogHouse.hashCode()
        )
    }
}
