package com.cr.o.cdc.sandboxAndroid.dbsandbox

import com.cr.o.cdc.sharedtest.Dog
import com.cr.o.cdc.sharedtest.getCountOfChangesLiveData
import junit.framework.TestCase.assertEquals
import org.junit.Test

class DbSandbox : DBTest() {
    private val dao = db.dogDao()

    @Test
    fun saveSameValue() {
        val dog = Dog("Leo")
        dao.save(dog)

        val count = getCountOfChangesLiveData(dao.load("Leo"), 5) {
            dao.save(dog)
        }

        assertEquals(2, count)
    }

    @Test
    fun saveSameValueInTransaction() {
        val dog = Dog("Leo")
        dao.save(dog)

        val count = getCountOfChangesLiveData(dao.load("Leo"), 5) {
            db.runInTransaction {
                dao.save(dog)
            }
        }

        assertEquals(2, count)
    }
}