package com.cr.o.cdc.sandboxAndroid.timeapi

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.text.format.DateFormat
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertTrue
import org.junit.Test
import org.threeten.bp.LocalDate
import java.util.*

class TimeSandbox {

    //11 Mar 2020
    private val milliseconds = 1583938374827

    @Test
    fun localDateOfEpochDay() {
        val l = LocalDate.ofEpochDay(milliseconds.div(86400000))
        assertTrue(l.dayOfMonth == 11)
        assertTrue(l.monthValue == 3)
        assertTrue(l.year == 2020)
    }

    @Test
    fun dateFormat() {
        val dateFormat = DateFormat.getDateFormat(mockk<Context>().apply {
            every { resources } returns mockk<Resources>().apply {
                every { configuration } returns mockk<Configuration>().apply {
                    every { locale } returns Locale.CANADA
                }
            }
        })
        dateFormat.format(LocalDate.now())
    }
}