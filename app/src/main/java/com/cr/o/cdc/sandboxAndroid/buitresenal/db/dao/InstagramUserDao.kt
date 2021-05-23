package com.cr.o.cdc.sandboxAndroid.buitresenal.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cr.o.cdc.sandboxAndroid.buitresenal.db.model.InstagramUser

/**
 * Created by Cami on 5/22/21.
 */
@Dao
interface InstagramUserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(instagramUser: InstagramUser)

    @Query("SELECT * FROM InstagramUser WHERE id =:id")
    fun loadById(id: String): LiveData<InstagramUser>
}