package com.cr.o.cdc.sandboxAndroid

import com.cr.o.cdc.requestbuilder.FileGenerator
import com.google.testing.compile.CompilationSubject.assertThat
import com.google.testing.compile.Compiler.javac
import com.google.testing.compile.JavaFileObjects
import org.junit.Test


class FileGeneratorTest {


    @Test
    fun assertCompileIsSuccessQueryBuilder() {
        val file = JavaFileObjects.forResource("Pokemon.java")

        val com = javac().withProcessors(FileGenerator()).compile(file)

        assertThat(com).succeeded()
    }

    @Test
    fun assertCompileIsSuccessFragmentBuilder() {
        val file = JavaFileObjects.forResource("Fragment.java")

        val com = javac().withProcessors(FileGenerator()).compile(file)

        assertThat(com).succeeded()
    }
}