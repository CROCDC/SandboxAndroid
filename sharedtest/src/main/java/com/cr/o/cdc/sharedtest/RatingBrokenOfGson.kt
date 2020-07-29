package com.cr.o.cdc.sharedtest

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class RatingBrokenOfGson(
    @PrimaryKey
    val id: String,
    val score: String
) {

    @Ignore
    val stars: Float = when (score) {
        "ONE" -> 1f
        "TWO" -> 2f
        "THREE" -> 3f
        "FOUR" -> 4f
        "FIVE" -> 5f
        else -> 0f
    }

}

