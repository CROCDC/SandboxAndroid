package com.cr.o.cdc.sandboxAndroid.whatsapputils.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cr.o.cdc.sandboxAndroid.whatsapputils.db.model.WhatsappMessage

@Dao
interface WhatsappMessageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(whatsappMessage: WhatsappMessage)

    @Query("SELECT * FROM whatsappmessage")
    fun loadAll(): LiveData<List<WhatsappMessage>>
}
