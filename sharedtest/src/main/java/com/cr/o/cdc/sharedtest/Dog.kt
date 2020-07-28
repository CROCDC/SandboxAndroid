package com.cr.o.cdc.sharedtest

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Dog(
    @PrimaryKey
    val name: String
)