package com.cr.o.cdc.sandboxAndroid.preguntadoshelper.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cr.o.cdc.sandboxAndroid.preguntadoshelper.db.model.PreguntadosQuestion

@Dao
interface PreguntadosDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAll(preguntadosQuestions: List<PreguntadosQuestion>)

    @Query("SELECT * FROM PreguntadosQuestion")
    fun loadAll(): LiveData<List<PreguntadosQuestion>>
}
