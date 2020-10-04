package com.cr.o.cdc.sandboxAndroid.utils

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.cr.o.cdc.sandboxAndroid.SandBoxDB
import org.junit.Rule

abstract class DBTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    val db: SandBoxDB = Room.inMemoryDatabaseBuilder(
        InstrumentationRegistry.getInstrumentation().context,
        SandBoxDB::class.java
    ).fallbackToDestructiveMigration()
        .allowMainThreadQueries()
        .build()
}
