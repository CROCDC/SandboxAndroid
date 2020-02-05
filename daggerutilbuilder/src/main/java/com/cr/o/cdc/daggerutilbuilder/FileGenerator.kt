package com.cr.o.cdc.daggerutilbuilder

import com.cr.o.cdc.daggerutilmodel.Injectable
import com.google.auto.service.AutoService
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import java.io.File
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement
import kotlin.reflect.KClass

@AutoService(Processor::class)
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedOptions(FileGenerator.KAPT_KOTLIN_GENERATED_OPTION_NAME)
class FileGenerator : AbstractProcessor() {

    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        return mutableSetOf(Injectable::class.java.name)
    }

    override fun getSupportedSourceVersion(): SourceVersion {
        return SourceVersion.latest()
    }

    override fun process(
        set: MutableSet<out TypeElement>?,
        roundEnvironment: RoundEnvironment?
    ): Boolean {

        val fragments = mutableListOf<String>()

        roundEnvironment?.getElementsAnnotatedWith(Injectable::class.java)?.forEach { it ->
            fragments.add("sdsdsd")
        }
        @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
        FileSpec.builder("daggerutil", "Test")
            .addType(
                TypeSpec.classBuilder("Test")
                    .addProperty(
                        PropertySpec.builder("list", Int::class.java, KModifier.PRIVATE)
                            .initializer("${fragments.size}")
                            .build()
                    )
                    .build()
            ).build()
            .writeTo(File(processingEnv.options[KAPT_KOTLIN_GENERATED_OPTION_NAME]))


        return true
    }

    companion object {
        const val KAPT_KOTLIN_GENERATED_OPTION_NAME = "kapt.kotlin.generated"
    }
}
