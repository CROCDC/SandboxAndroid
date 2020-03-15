package com.cr.o.cdc.sandboxAndroid.coronavirus.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cr.o.cdc.sandboxAndroid.coronavirus.model.CountryStat

@Dao
interface CountryStatDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAll(countryStats: List<CountryStat>)

    @Query("SELECT * FROM CountryStat")
    fun loadAll(): LiveData<List<CountryStat>>

    @Query("DELETE FROM CountryStat")
    fun deleteAll()
}