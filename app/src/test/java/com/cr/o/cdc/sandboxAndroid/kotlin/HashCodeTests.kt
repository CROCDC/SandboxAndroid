package com.cr.o.cdc.sandboxAndroid.kotlin

import junit.framework.TestCase.assertTrue
import org.junit.Test

class HashCodeTests {

    @Test
    fun hashCodeCompare() {
        val dateInput1 = DateInput(2005, 5, 12)
        val dateInput2 = DateInput(2020, 5, 12)

        val dateInput3 = DateInput(2006, 5, 12)
        val dateInput4 = DateInput(200, 6, 20)

        val hashCode1 = dateInput1.hashCode().toString() + dateInput2.hashCode().toString()
        val hashCode2 = dateInput3.hashCode().toString() + dateInput4.hashCode().toString()

        assertTrue(hashCode1 != hashCode2)
    }
}
