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

    companion object {
        const val COLS = "{score}"
        val rate1 = RatingBrokenOfGson("1", "FIVE")
        val rate2 = RatingBrokenOfGson("2", "FOUR")

        val json = "{\n" +
                "  \"data\": {\n" +
                "    \"rating\": {\n" +
                "      \"id\": \"1\",\n" +
                "      \"score\": \"FIVE\"\n" +
                "    }\n" +
                "  }\n" +
                "}"
    }
}

