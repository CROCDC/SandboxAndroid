package com.cr.o.cdc.sandboxAndroid.dbsandbox

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry

abstract class DBTest {

    val context = InstrumentationRegistry.getInstrumentation().targetContext

    val db = Room
        .databaseBuilder(context, DBSandBoxTest::class.java, "TEST")
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()
}