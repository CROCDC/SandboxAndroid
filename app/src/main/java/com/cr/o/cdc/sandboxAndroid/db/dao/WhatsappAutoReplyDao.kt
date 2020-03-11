package com.cr.o.cdc.sandboxAndroid.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cr.o.cdc.sandboxAndroid.whatsapputils.model.WhatsappAutoReply

@Dao
interface WhatsappAutoReplyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(whatsappAutoReply: WhatsappAutoReply)


    @Query("SELECT * FROM whatsappautoreply")
    fun loadAll(): LiveData<List<WhatsappAutoReply>>
}