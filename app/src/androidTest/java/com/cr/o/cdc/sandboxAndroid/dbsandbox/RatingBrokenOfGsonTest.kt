package com.cr.o.cdc.sandboxAndroid.dbsandbox

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.cr.o.cdc.sharedtest.RatingBrokenOfGson
import com.cr.o.cdc.sharedtest.getCountOfChangesLiveData
import junit.framework.TestCase.assertTrue
import org.junit.Rule
import org.junit.Test

class RatingBrokenOfGsonTest : DBTest() {

    @Test
    fun ratingRoomTestWithValStars() {
        db.ratingDao().save(RatingBrokenOfGson.rate1)
        db.ratingDao().save(RatingBrokenOfGson.rate2)

        val rating2 = db.ratingDao().loadAsync("2")
        val rating1 = db.ratingDao().loadAsync("1")

        assertTrue(rating2.stars == 4F)
        assertTrue(rating1.stars == 5F)
    }

    @Test
    fun saveAndUpdateValueRoom() {
        db.ratingDao().save(RatingBrokenOfGson.rate1)

        val count = getCountOfChangesLiveData(db.ratingDao().load(RatingBrokenOfGson.rate1.id), 5) {
            db.ratingDao().updateScore("FOUR", RatingBrokenOfGson.rate1.id)
        }

        assertTrue(count == 2)
    }

    @Test
    fun updateSameValueRoom() {
        db.ratingDao().save(RatingBrokenOfGson.rate1)

        val count = getCountOfChangesLiveData(db.ratingDao().load(RatingBrokenOfGson.rate1.id), 5) {
            db.ratingDao().updateScore(RatingBrokenOfGson.rate1.score, RatingBrokenOfGson.rate1.id)
        }

        assertTrue(count == 2)
    }
}
