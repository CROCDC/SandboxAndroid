package com.cr.o.cdc.sandboxAndroid.whatsapputils.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cr.o.cdc.sandboxAndroid.whatsapputils.db.model.WhatsappMessageBot

@Dao
interface WhatsappMessageBotDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(whatsappMessageBot: WhatsappMessageBot)

    @Query("SELECT * FROM whatsappmessagebot WHERE contact== :contact and message == :message")
    fun find(contact: String, message: String): List<WhatsappMessageBot>


    @Query("SELECT * FROM whatsappmessagebot")
    fun loadAll(): LiveData<List<WhatsappMessageBot>>
}