package com.cr.o.cdc.sandboxAndroid.downdetector.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cr.o.cdc.sandboxAndroid.downdetector.db.model.Site

@Dao
interface SiteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(site: Site)


    @Query("SELECT * FROM Site")
    fun getAll(): LiveData<List<Site>>

    @Query("UPDATE Site SET enable =:enable WHERE address =:address")
    fun modifyEnable(address: String, enable: Boolean)
}