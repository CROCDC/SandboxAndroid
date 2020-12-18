package com.cr.o.cdc.sandboxAndroid.dbsandbox

import com.cr.o.cdc.sandboxAndroid.dbsandbox.fake.MockFactoryDBSandbox
import com.cr.o.cdc.sharedtest.getCountOfChangesLiveData
import com.cr.o.cdc.sharedtest.getValueLiveData
import junit.framework.TestCase.assertEquals
import org.junit.Test

class DBSandboxTest : DBSandboxTestUtils() {
    private val dao = db.dogDao()

    @Test
    fun saveSameValue() {
        val dog = MockFactoryDBSandbox.getDog()
        dao.save(dog)

        val count = getCountOfChangesLiveData(dao.load(dog.name), 5) {
            dao.save(dog)
        }

        assertEquals(2, count)
    }

    @Test
    fun saveSameValueList() {
        val dogs = MockFactoryDBSandbox.getListOfDogs()
        dao.saveAll(dogs)

        val count = getCountOfChangesLiveData(dao.loadAll(), 5) {
            dao.saveAll(dogs)
        }

        assertEquals(2, count)
    }

    /*when save empty list in room, the livedata not change */
    @Test
    fun saveListEmpty() {
        val dogs = MockFactoryDBSandbox.getListOfDogs()
        dao.saveAll(dogs)

        val count = getCountOfChangesLiveData(dao.loadAll(), 5) {
            dao.saveAll(listOf())
        }

        assertEquals(1, count)
    }

    @Test
    fun saveSameValueInTransaction() {
        val dog = MockFactoryDBSandbox.getDog()
        dao.save(dog)

        val count = getCountOfChangesLiveData(dao.load(dog.name), 5) {
            db.runInTransaction {
                dao.save(dog)
            }
        }

        assertEquals(2, count)
    }

    @Test
    fun replaceChangeOrderOfList() {
        val listOfDogs = MockFactoryDBSandbox.getListOfDogs()

        db.runInTransaction {
            dao.saveAll(listOfDogs)
            dao.save(MockFactoryDBSandbox.getDog())
        }

        val list = getValueLiveData(dao.loadAll(), 5)

        assertEquals(listOfDogs, list?.reversed())
    }

    @Test
    fun updateNotChangeOrderOfList() {
        val listOfDogs = MockFactoryDBSandbox.getListOfDogs()

        db.runInTransaction {
            dao.saveAll(listOfDogs)
            dao.update(MockFactoryDBSandbox.getDog())
        }

        val list = getValueLiveData(dao.loadAll(), 5)

        assertEquals(listOfDogs, list)
    }

    @Test
    fun updateWithoutSaveBefore() {
        val dog = MockFactoryDBSandbox.getDog()
        dao.update(dog)

        val dbDog = getValueLiveData(dao.load(dog.name), 5)

        assertEquals(dbDog, null)
    }

    @Test
    fun innerJoinExample() {
        val dog = MockFactoryDBSandbox.getDog()
        dao.save(dog)
        dao.saveDogHouse(MockFactoryDBSandbox.getDogHouse())

        val dogs = dao.getDogsByPosition(PositionDogHouse.CABA.hashCode())

        assertEquals(dog, dogs.first())
    }
}
