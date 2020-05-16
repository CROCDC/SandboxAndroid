package com.cr.o.cdc.sandboxAndroid.bitbucket.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Workspace(@PrimaryKey val name: String, val slug: String)
