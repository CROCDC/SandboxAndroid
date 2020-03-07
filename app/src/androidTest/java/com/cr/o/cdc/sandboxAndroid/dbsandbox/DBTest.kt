package com.cr.o.cdc.sandboxAndroid.dbsandbox

import android.content.Context
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry

abstract class DBTest {

    private val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

    val db = Room
        .databaseBuilder(context, DBSandBoxTest::class.java, "TEST")
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()
}