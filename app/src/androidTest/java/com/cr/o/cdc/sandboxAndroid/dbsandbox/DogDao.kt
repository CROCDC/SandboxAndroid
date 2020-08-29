package com.cr.o.cdc.sandboxAndroid.dbsandbox

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.cr.o.cdc.sharedtest.Dog

@Dao
interface DogDao {

    @Query("SELECT * FROM dog")
    fun loadAll(): LiveData<List<Dog>>

    @Query("SELECT * FROM Dog WHERE name = :name")
    fun load(name: String): LiveData<Dog>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(dog: Dog)

    @Update
    fun update(dog: Dog)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAll(dogs: List<Dog>)

    @Insert
    fun saveDogHouse(dogHouse: DogHouse)

    @Query(
        "SELECT * FROM DogHouse LEFT JOIN Dog ON dogName = name WHERE positionDogHouse =:position"
    )
    fun getDogsByPosition(position: Int): List<Dog>
}
