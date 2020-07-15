package com.cr.o.cdc.sandboxAndroid.dbsandbox

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule

abstract class DBTest {

    private val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    val db = Room
        .inMemoryDatabaseBuilder(context, DBSandBoxTest::class.java)
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()
}
