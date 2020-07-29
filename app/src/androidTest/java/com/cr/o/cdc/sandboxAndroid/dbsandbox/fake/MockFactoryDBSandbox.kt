package com.cr.o.cdc.sandboxAndroid.dbsandbox.fake

import com.cr.o.cdc.sharedtest.Dog
import com.cr.o.cdc.sharedtest.RatingBrokenOfGson

object MockFactoryDBSandbox {

    fun getDog(name: String? = null): Dog = Dog(name ?: "name")

    fun getListOfDogs(): List<Dog> = listOf(getDog(), getDog("Leo"))

    fun getRatingBrokenOfGson(id: String? = null): RatingBrokenOfGson =
        RatingBrokenOfGson(id ?: "1", "FIVE")
}
