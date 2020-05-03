package com.cr.o.cdc.sandboxAndroid.dbsandbox

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cr.o.cdc.sharedtest.RatingBrokenOfGson

@Database(
    entities = [RatingBrokenOfGson::class],
    version = 1
)

abstract class DBSandBoxTest : RoomDatabase() {

    abstract fun ratingDao(): RatingDao
}
