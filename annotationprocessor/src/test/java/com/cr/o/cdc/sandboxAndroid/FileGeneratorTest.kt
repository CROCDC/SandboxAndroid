package com.cr.o.cdc.sandboxAndroid

import com.cr.o.cdc.requestbuilder.FileGenerator
import com.tschuchort.compiletesting.KotlinCompilation
import com.tschuchort.compiletesting.SourceFile
import junit.framework.TestCase.assertTrue
import org.junit.Test
import java.io.File


class FileGeneratorTest {

    fun assertCompileIsSuccessFileGenerator() {
        KotlinCompilation().apply {
            sources = listOf(SourceFile.fromPath(File("src/test/resources/Pokemons.kt")))
            annotationProcessors = listOf(FileGenerator())
            inheritClassPath = true
            messageOutputStream = System.out
        }.compile().let {
            assertTrue(it.exitCode == KotlinCompilation.ExitCode.OK)
            it.generatedFiles
        }
    }
}