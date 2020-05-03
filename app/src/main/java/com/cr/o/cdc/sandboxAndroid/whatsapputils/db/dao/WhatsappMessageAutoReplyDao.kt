package com.cr.o.cdc.sandboxAndroid.whatsapputils.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cr.o.cdc.sandboxAndroid.whatsapputils.db.model.WhatsappMessageAutoReply

@Dao
interface WhatsappMessageAutoReplyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(whatsappMessageAutoReply: WhatsappMessageAutoReply)

    @Query("SELECT * FROM whatsappmessageautoreply")
    fun loadAll(): LiveData<List<WhatsappMessageAutoReply>>
}
