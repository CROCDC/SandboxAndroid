package com.cr.o.cdc.sandboxAndroid.sandbox.time

import android.text.format.DateFormat
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Test
import org.threeten.bp.LocalDate
import java.text.SimpleDateFormat
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
    fun gregorianCalendar() {
        val gregorianCalendar = GregorianCalendar()
        gregorianCalendar.timeInMillis = milliseconds

        assertTrue(gregorianCalendar.get(GregorianCalendar.DAY_OF_MONTH) == 11)
    }

    @Test
    fun calendar() {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = milliseconds

        assertTrue(calendar.get(Calendar.DAY_OF_MONTH) == 11)
    }

    //hacer esto esta mal ya que en US el formato deberia ser meses/dias
    @Test
    fun simpleDateFormat() {
        val badFormat = SimpleDateFormat("dd/MM/yy", Locale.US).format(Date(milliseconds))

        val realFormat =
            SimpleDateFormat(
                DateFormat.getBestDateTimePattern(Locale.US, "dd/MM/yy"),
                Locale.US
            ).format(Date(milliseconds))

        assertTrue(realFormat != badFormat)
    }

    @Test
    fun dateFormat_US() {
        assertEquals(DateFormat.getBestDateTimePattern(Locale.US, "dd/MM/yy"), "MM/dd/yy")

    }

    @Test
    fun dateFormat_es_ES() {
        assertEquals(
            DateFormat.getBestDateTimePattern(Locale.forLanguageTag("es-ES"), "dd/MM/yy"),
            "dd/MM/yy"
        )
    }
}