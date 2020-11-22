package com.cr.o.cdc.sandboxAndroid.bluetooth.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.cr.o.cdc.sandboxAndroid.bluetooth.db.model.BluetoothMessage

/**
 * Created by Cami on 11/22/20.
 */
@Dao
interface BluetoothMessageDao {
    @Insert
    fun insert(bluetoothMessage: BluetoothMessage)

    @Query("SELECT * FROM BluetoothMessage")
    fun selectAll(): LiveData<List<BluetoothMessage>>
}