package com.cr.o.cdc.sandboxAndroid.mockksandbox

import io.mockk.MockKException
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import junit.framework.TestCase.assertTrue
import org.junit.Test

class Mockk {

    private val mockName = "Leo"

    @Test
    fun mockVariableKotlin() {
        val dog = mockk<DogKotlin>().apply {
            every { name } returns mockName
        }

        assertTrue(dog.name == mockName)
    }

    @Test(expected = MockKException::class)
    fun mockkVariableJava() {
        val dog = spyk<DogJava>().apply {
            every { name } returns mockName
        }

        assertTrue(dog.name == mockName)
    }
}
