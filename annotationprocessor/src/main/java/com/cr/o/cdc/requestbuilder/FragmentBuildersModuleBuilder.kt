package com.cr.o.cdc.requestbuilder

import com.squareup.kotlinpoet.*
import dagger.Module
import java.io.File
import javax.annotation.processing.ProcessingEnvironment
import javax.lang.model.element.Element

class FragmentBuildersModuleBuilder(
    elements: MutableSet<out Element>,
    private val processingEnv: ProcessingEnvironment
) {


    private val methods = elements.map {
        FunSpec.builder("contributes${it.simpleName}")
            .addModifiers(KModifier.ABSTRACT)
            .returns(it.asType().asTypeName())
            .build()
    }

    fun build() {
        if (methods.isNotEmpty()) {
            FileSpec.builder("dagger.util", "FragmentBuildersModule").addType(
                TypeSpec.classBuilder("FragmentBuildersModule")
                    .addModifiers(KModifier.ABSTRACT)
                    .addAnnotation(Module::class.java)
                    .addFunctions(methods).build()
            ).build()
                .writeTo(
                    File(
                        processingEnv.options[FileGenerator.KAPT_KOTLIN_GENERATED_OPTION_NAME] ?: ""
                    )
                )
        }

    }
}