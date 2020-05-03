package com.cr.o.cdc.sandboxAndroid.coronavirus.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CountryStat(@PrimaryKey val country_name: String, val cases: String, val deaths: String)
