package com.cr.o.cdc.sandboxAndroid.dbsandbox

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cr.o.cdc.sharedtest.RatingBrokenOfGson

@Dao
interface RatingDao {

    @Query("SELECT * FROM RatingBrokenOfGson WHERE id = :id")
    fun loadAsync(id: String): RatingBrokenOfGson

    @Query("SELECT * FROM RatingBrokenOfGson WHERE id = :id")
    fun load(id: String): LiveData<RatingBrokenOfGson>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(ratingBrokenOfGson: RatingBrokenOfGson)

    @Query("UPDATE RatingBrokenOfGson SET score =:score WHERE id = :id")
    fun updateScore(score: String, id: String)
}
