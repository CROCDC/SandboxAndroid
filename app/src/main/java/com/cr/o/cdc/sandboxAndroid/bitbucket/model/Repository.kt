package com.cr.o.cdc.sandboxAndroid.bitbucket.model

import androidx.room.Entity

@Entity
data class Repository(val slug: String, val name: String)
