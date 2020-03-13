package com.cr.o.cdc.networking

import java.util.concurrent.Executor
import java.util.concurrent.Executors

class AppExecutors(
    private val diskIO: Executor,
    private val networkIO: Executor
) {
    constructor() : this(
        Executors.newSingleThreadExecutor(),
        Executors.newFixedThreadPool(3)
    )

    fun diskIO(): Executor = diskIO

    fun networkIO(): Executor = networkIO

}