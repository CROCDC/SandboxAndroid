package com.cr.o.cdc.sandboxAndroid.bitbucket.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cr.o.cdc.sandboxAndroid.bitbucket.model.Workspace

@Dao
interface WorkspaceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAll(workspaces: List<Workspace>?)

    @Query("SELECT * FROM Workspace")
    fun loadAll(): LiveData<List<Workspace>>

}
