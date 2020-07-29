package com.cr.o.cdc.sandboxAndroid.dbsandbox

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule

abstract class DBSandboxTestUtils {

    private val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    val db: DBSandBox = Room
        .inMemoryDatabaseBuilder(context, DBSandBox::class.java)
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()
}
