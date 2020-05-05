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

    @Query(
        "UPDATE Site SET workRequestId =:workRequestId," +
                " enable =:enable WHERE address =:address"
    )
    fun modifyEnable(address: String, enable: Boolean, workRequestId: String?)

    @Query(
        "UPDATE Site SET cantErrors =:cantErrors, lastErrorMessage =:errorMessage," +
                " isWorking = 0 WHERE address =:address"
    )
    fun siteNotWorking(cantErrors: Int, address: String, errorMessage: String?)

    @Query("SELECT * FROM Site WHERE address =:address")
    fun find(address: String): Site

    @Query(
        "UPDATE Site SET numberOfRetriesOfError = null" +
                ", isWorking = 1 WHERE address =:address"
    )
    fun siteIsWorking(address: String)

    @Query(
        "UPDATE Site SET numberOfRetriesOfError" +
                " =:numberOfRetriesOfError WHERE address =:address"
    )
    fun setNumberOfRetriesOfError(address: String, numberOfRetriesOfError: Int?)

    @Query("DELETE FROM Site WHERE address=:address")
    fun delete(address: String)
}
