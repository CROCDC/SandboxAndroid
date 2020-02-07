package com.cr.o.cdc.requestbuilder

import com.squareup.javapoet.JavaFile
import com.squareup.javapoet.MethodSpec
import com.squareup.javapoet.TypeSpec
import com.squareup.kotlinpoet.asTypeName
import dagger.Module
import dagger.android.ContributesAndroidInjector
import java.io.File
import javax.annotation.processing.ProcessingEnvironment
import javax.lang.model.element.Element
import javax.lang.model.element.Modifier


class FragmentBuildersModuleBuilder(
    elements: MutableSet<out Element>,
    private val processingEnv: ProcessingEnvironment
) {


    private val methods = elements.map {
        MethodSpec.methodBuilder("contributes${it.simpleName}")
            .addModifiers(Modifier.ABSTRACT)
            .addAnnotation(ContributesAndroidInjector::class.java)
            .returns(it::class.java)
            .build()
    }

    fun build() {
        if (methods.isNotEmpty()) {
            JavaFile.builder(
                "com.cr.o.cdc.sandboxAndroid.di",
                TypeSpec.classBuilder("FragmentBuildersModule")
                    .addModifiers(Modifier.ABSTRACT)
                    .addAnnotation(Module::class.java)
                    .addMethods(methods)
                    .build()
            ).build()
                .writeTo(File(processingEnv.options[FileGenerator.KAPT_KOTLIN_GENERATED_OPTION_NAME] ?: ""))
        }

    }
}