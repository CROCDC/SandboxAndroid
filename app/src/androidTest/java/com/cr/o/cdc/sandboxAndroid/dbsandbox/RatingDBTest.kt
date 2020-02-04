package com.cr.o.cdc.sandboxAndroid.dbsandbox

import com.cr.o.cdc.sharedtest.RatingBrokenOfGson
import junit.framework.TestCase.assertTrue
import org.junit.Test

class RatingBrokenOfGsonTest : DBTest() {

    @Test
    fun ratingRoomTestWithValStars() {
        db.ratingDao().save(RatingBrokenOfGson.rate1)
        db.ratingDao().save(RatingBrokenOfGson.rate2)

        val rating2 = db.ratingDao().load("2")
        val rating1 = db.ratingDao().load("1")


        assertTrue(rating2.stars == 4F)
        assertTrue(rating1.stars == 5F)
    }
}

