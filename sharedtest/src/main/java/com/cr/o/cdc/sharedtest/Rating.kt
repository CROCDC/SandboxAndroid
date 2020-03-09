package com.cr.o.cdc.sharedtest

data class Rating(val score: String)

fun Rating.getStars(): Float = when (score) {
    "ONE" -> 1f
    "TWO" -> 2f
    "THREE" -> 3f
    "FOUR" -> 4f
    "FIVE" -> 5f
    else -> 0f
}
