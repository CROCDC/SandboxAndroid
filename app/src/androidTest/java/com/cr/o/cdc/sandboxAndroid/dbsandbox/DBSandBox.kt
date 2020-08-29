package com.cr.o.cdc.sandboxAndroid.dbsandbox

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cr.o.cdc.sharedtest.Dog
import com.cr.o.cdc.sharedtest.RatingBrokenOfGson

@Database(
    entities = [RatingBrokenOfGson::class, Dog::class, DogHouse::class],
    version = 1
)

abstract class DBSandBox : RoomDatabase() {

    abstract fun ratingDao(): RatingDao

    abstract fun dogDao(): DogDao
}
