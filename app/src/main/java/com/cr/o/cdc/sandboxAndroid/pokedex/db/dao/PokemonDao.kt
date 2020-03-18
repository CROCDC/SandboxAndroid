package com.cr.o.cdc.sandboxAndroid.pokedex.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cr.o.cdc.sandboxAndroid.pokedex.db.model.Pokemon

@Dao
interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAll(list: List<Pokemon>)

    @Query("SELECT * FROM Pokemon")
    fun loadAll(): LiveData<List<Pokemon>>
}