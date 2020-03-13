package com.cr.o.cdc.sandboxAndroid.kotlin

import junit.framework.TestCase.assertTrue
import org.junit.Test

class ToString {

    @Test
    fun dateInputToStringContains() {
        val dateInput1 = DateInput(2005, 5, 12)
        val dateInput2 = DateInput(2020, 5, 12)

        assertTrue((dateInput1.toString() + dateInput2.toString()).contains("="))
    }

}

data class DateInput(val year: Int, val month: Int, val day: Int)