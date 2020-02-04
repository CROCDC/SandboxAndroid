package com.cr.o.cdc.sandboxAndroid.dbsandbox

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cr.o.cdc.sharedtest.RatingBrokenOfGson

@Dao
interface RatingDao {

    @Query("SELECT * FROM RatingBrokenOfGson WHERE id = :id")
    fun load(id: String): RatingBrokenOfGson

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(ratingBrokenOfGson: RatingBrokenOfGson)
}