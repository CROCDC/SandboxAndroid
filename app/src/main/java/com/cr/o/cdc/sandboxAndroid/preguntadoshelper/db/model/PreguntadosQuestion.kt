package com.cr.o.cdc.sandboxAndroid.preguntadoshelper.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PreguntadosQuestion(
    @PrimaryKey val questionTitle: String,
    val answer: String
)