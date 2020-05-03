package com.cr.o.cdc.sandboxAndroid.downdetector.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Site(
    @PrimaryKey val address: String,
    val name: String,
    val enable: Boolean,
    val intervalCheck: Int,
    val cantErrors: Int,
    val isWorking: Boolean?,
    val workRequestId: String?,
    val numberOfRetriesOfError: Int?
)
