package com.cr.o.cdc.sandboxAndroid.sandbox.locale

import com.cr.o.cdc.sandboxAndroid.coronavirus.di.CoronavirusModule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

@HiltAndroidTest
@UninstallModules(CoronavirusModule::class)
class LocaleTest {

    @Test
    fun `ARS_en-CA`() {
        val currency = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("en-CA")).apply {
            currency = Currency.getInstance("ARS")
        }.format(0.0)

        assertEquals(currency, "ARS0.00")
    }

    @Test
    fun `MXN_es-MX`() {
        val currency = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("es-MX")).apply {
            currency = Currency.getInstance("MXN")
        }.format(0.0)

        assertEquals(currency, "$0.00")
    }

    @Test
    fun `MXN_en-US`() {
        val text = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("en-US")).apply {
            currency = Currency.getInstance("MXN")
        }.format(0.0)

        assertEquals(text, "MX$0.00")
    }
}
