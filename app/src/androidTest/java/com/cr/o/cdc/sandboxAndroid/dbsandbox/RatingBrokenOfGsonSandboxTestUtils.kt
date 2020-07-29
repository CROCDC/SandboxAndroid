package com.cr.o.cdc.sandboxAndroid.dbsandbox

import com.cr.o.cdc.sandboxAndroid.coronavirus.di.CoronavirusModule
import com.cr.o.cdc.sandboxAndroid.dbsandbox.fake.MockFactoryDBSandbox
import com.cr.o.cdc.sharedtest.getCountOfChangesLiveData
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import junit.framework.TestCase.assertTrue
import org.junit.Test

@HiltAndroidTest
@UninstallModules(CoronavirusModule::class)
class RatingBrokenOfGsonSandboxTestUtils : DBSandboxTestUtils() {

    @Test
    fun ratingRoomTestWithValStars() {
        db.ratingDao().save(MockFactoryDBSandbox.getRatingBrokenOfGson("1"))

        val rating = db.ratingDao().loadAsync("1")

        assertTrue(rating.stars == 5F)
    }

    @Test
    fun saveAndUpdateValueRoom() {
        val rate = MockFactoryDBSandbox.getRatingBrokenOfGson("1")
        db.ratingDao().save(rate)

        val count = getCountOfChangesLiveData(db.ratingDao().load(rate.id), 5) {
            db.ratingDao().updateScore("FOUR", rate.id)
        }

        assertTrue(count == 2)
    }

    @Test
    fun updateSameValueRoom() {
        val rate = MockFactoryDBSandbox.getRatingBrokenOfGson("1")
        db.ratingDao().save(rate)

        val count = getCountOfChangesLiveData(db.ratingDao().load(rate.id), 5) {
            db.ratingDao().updateScore(rate.score, rate.id)
        }

        assertTrue(count == 2)
    }
}
