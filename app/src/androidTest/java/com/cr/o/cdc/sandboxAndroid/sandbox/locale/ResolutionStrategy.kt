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
    fun `locale-en-return_default`() {
        setLocale("en")
        assertEquals(STRING_DEFAULT, resources.getString(R.string.string))
    }

    @Test
    fun `locale-es-AR-return_Argentina`() {
        setLocale("es-AR")
        assertEquals(STRING_ARGENTINA, resources.getString(R.string.string))
    }

    @Test
    fun `locale-es-return_Espanol_Neutro`() {
        setLocale(LANGUAGE_TAG_ESPANOL_NEUTRO)
        assertEquals(STRING_ESPANOL_NEUTRO, resources.getString(R.string.string))
    }

    @Test
    fun `locale-es-MX-return_Espanol_Mexico`() {
        setLocale(LANGUAGE_TAG_MEXICO)
        assertEquals(STRING_MEXICO, resources.getString(R.string.string))
    }

    @Test
    fun `locale-es-DO-return_Espanol_Republica_Dominicana`() {
        setLocale(LANGUAGE_TAG_REPUBLICA_DOMINICANA)
        assertEquals(STRING_REPUBLICA_DOMINICANA, resources.getString(R.string.string))
    }

    @Test
    fun `string_default_es_es_MX_locale-es-DO-return_Espanol_Mexico`() {
        setLocale(LANGUAGE_TAG_REPUBLICA_DOMINICANA)
        assertEquals(LANGUAGE_TAG_MEXICO, resources.getString(R.string.string_default_es_es_MX))
    }

    @Test
    fun `string_default_es_es_MX_locale-es-AR-return_Espanol_Mexico`() {
        setLocale(LANGUAGE_TAG_ARGENTINA)
        assertEquals(LANGUAGE_TAG_MEXICO, resources.getString(R.string.string_default_es_es_MX))
    }

    @Test
    fun `string_default_es_es_MX_es_DO_locale-es-AR-return_Espanol_Mexico`() {
        setLocale(LANGUAGE_TAG_ARGENTINA)
        assertEquals(
            LANGUAGE_TAG_MEXICO,
            resources.getString(R.string.string_default_es_es_MX_es_DO)
        )
    }

    @Test
    fun `string_default_es_es_es_ES_locales-es-AR-return-Espanol_Neutro`() {
        setLocale(LANGUAGE_TAG_ARGENTINA)
        assertEquals(
            LANGUAGE_TAG_ESPANOL_NEUTRO,
            resources.getString(R.string.string_default_es_es_es_ES)
        )
    }

    @Test
    fun `string_default_es_es_es_ES_locales-es-ES-return-Espanol_Espana`() {
        setLocale(LANGUAGE_TAG_ESPANOL_ESPANA)
        assertEquals(
            LANGUAGE_TAG_ESPANOL_ESPANA,
            resources.getString(R.string.string_default_es_es_es_ES)
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

        private const val LANGUAGE_TAG_ESPANOL_ESPANA = "es-ES"
        private const val LANGUAGE_TAG_ESPANOL_NEUTRO = "es"
        private const val LANGUAGE_TAG_MEXICO = "es-MX"
        private const val LANGUAGE_TAG_REPUBLICA_DOMINICANA = "es-DO"
        private const val LANGUAGE_TAG_ARGENTINA = "es-AR"
    }
}
