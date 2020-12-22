package com.cr.o.cdc.sandboxAndroid.sandbox.locale

import android.content.res.Resources
import androidx.test.platform.app.InstrumentationRegistry
import com.cr.o.cdc.sandboxAndroid.test.R
import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.util.Locale

class ResolutionStrategy {

    private lateinit var resources: Resources

    @Test
    fun `string-locale_en-return_STRING_DEFAULT`() {
        setLocale(LANGUAGE_TAG_ENGLISH)
        assertEquals(STRING_DEFAULT, resources.getString(R.string.string))
    }

    @Test
    fun `string-locale_es_AR-return_STRING_ARGENTINA`() {
        setLocale(LANGUAGE_TAG_ARGENTINA)
        assertEquals(STRING_ARGENTINA, resources.getString(R.string.string))
    }

    @Test
    fun `string-locale_es-return_STRING_ESPANOL_NEUTRO`() {
        setLocale(LANGUAGE_TAG_ESPANOL_NEUTRO)
        assertEquals(STRING_ESPANOL_NEUTRO, resources.getString(R.string.string))
    }

    @Test
    fun `string-locale_es_MX-return_STRING_MEXICO`() {
        setLocale(LANGUAGE_TAG_MEXICO)
        assertEquals(STRING_MEXICO, resources.getString(R.string.string))
    }

    @Test
    fun `string-locale_es_DO-return_STRING_REPUBLICA_DOMINICANA`() {
        setLocale(LANGUAGE_TAG_REPUBLICA_DOMINICANA)
        assertEquals(STRING_REPUBLICA_DOMINICANA, resources.getString(R.string.string))
    }

    @Test
    fun `string_default__es__es_MX-locale_es_DO-return_STRING_MEXICO`() {
        setLocale(LANGUAGE_TAG_REPUBLICA_DOMINICANA)
        assertEquals(
            STRING_MEXICO,
            resources.getString(R.string.string_default__es__es_MX)
        )
    }

    @Test
    fun `string_default__es__es_MX-locale_es_AR-return_STRING_MEXICO`() {
        setLocale(LANGUAGE_TAG_ARGENTINA)
        assertEquals(
            STRING_MEXICO,
            resources.getString(R.string.string_default__es__es_MX)
        )
    }

    @Test
    fun `string_default__es__es_MX__es_DO-locale_es_AR-return_STRING_MEXICO`() {
        setLocale(LANGUAGE_TAG_ARGENTINA)
        assertEquals(
            STRING_MEXICO,
            resources.getString(R.string.string_default__es__es_MX__es_DO)
        )
    }

    @Test
    fun `string_default__es__es_ES-locale_es_AR-return_STRING_ESPANOL_NEUTRO`() {
        setLocale(LANGUAGE_TAG_ARGENTINA)
        assertEquals(
            STRING_ESPANOL_NEUTRO,
            resources.getString(R.string.string_default__es__es_ES)
        )
    }

    @Test
    fun `string_default__es__es_ES-locale_es_ES-return_STRING_ESPANOL_NEUTRO`() {
        setLocale(LANGUAGE_TAG_ESPANOL_ESPANA)
        assertEquals(
            STRING_ESPANOL_NEUTRO,
            resources.getString(R.string.string_default__es__es_ES)
        )
    }

    @Test
    fun `string-locale_es_UY-return_STRING_URUGUAY`() {
        setLocale(LANGUAGE_TAG_URUGUAY)
        assertEquals(
            STRING_URUGUAY,
            resources.getString(R.string.string)
        )
    }

    @Test
    fun `string_default__es__es_MX-locale_es_UY-return_STRING_MEXICO`() {
        setLocale(LANGUAGE_TAG_URUGUAY)
        assertEquals(
            STRING_MEXICO,
            resources.getString(R.string.string_default__es__es_MX)
        )
    }



    private fun setLocale(languageTag: String) {
        val context = InstrumentationRegistry.getInstrumentation().context
        val configuration = context.resources.configuration
        configuration.setLocale(Locale.forLanguageTag(languageTag))
        resources = context.createConfigurationContext(configuration).resources
    }

    companion object {
        private const val STRING_DEFAULT = "English Default"
        private const val STRING_ARGENTINA = "Español Argentina"
        private const val STRING_ESPANOL_NEUTRO = "Español Neutro"
        private const val STRING_MEXICO = "Español Mexico"
        private const val STRING_REPUBLICA_DOMINICANA = "Español Republica Domininica"
        private const val STRING_URUGUAY = "Español Uruguay"

        private const val LANGUAGE_TAG_ENGLISH = "en"
        private const val LANGUAGE_TAG_ESPANOL_ESPANA = "es-ES"
        private const val LANGUAGE_TAG_ESPANOL_NEUTRO = "es"
        private const val LANGUAGE_TAG_MEXICO = "es-MX"
        private const val LANGUAGE_TAG_REPUBLICA_DOMINICANA = "es-DO"
        private const val LANGUAGE_TAG_ARGENTINA = "es-AR"
        private const val LANGUAGE_TAG_URUGUAY = "es-UY"
    }
}