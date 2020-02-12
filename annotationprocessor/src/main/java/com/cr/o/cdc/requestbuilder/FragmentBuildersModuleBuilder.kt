package com.cr.o.cdc.requestbuilder

import com.squareup.javapoet.ClassName
import com.squareup.javapoet.JavaFile
import com.squareup.javapoet.MethodSpec
import com.squareup.javapoet.TypeSpec
import dagger.Module
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
            .returns(ClassName.get(it.asType()))
            .build()
    }

    fun build() {
        if (methods.isNotEmpty()) {
            JavaFile.builder(
                "com.cr.o.cdc.sandboxAndroid.di",
                TypeSpec.classBuilder("FragmentBuildersModuleT")
                    .addModifiers(Modifier.ABSTRACT, Modifier.PUBLIC)
                    .addAnnotation(Module::class.java)
                    .addMethods(methods).build()
            ).build().writeTo(
                File(
                    processingEnv.options[FileGenerator.KAPT_KOTLIN_GENERATED_OPTION_NAME] ?: ""
                )
            )
        }

    }
}