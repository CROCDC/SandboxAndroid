package com.cr.o.cdc.requestbuilder

import com.cr.o.cdc.requestsannotations.Request
import com.google.auto.service.AutoService
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import java.io.File
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.ElementKind
import javax.lang.model.element.TypeElement
import javax.lang.model.util.Elements

@AutoService(Processor::class) // For registering the service
@SupportedSourceVersion(SourceVersion.RELEASE_8) // to support Java 8
@SupportedOptions(FileGenerator.KAPT_KOTLIN_GENERATED_OPTION_NAME)
class FileGenerator : AbstractProcessor() {

    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        return mutableSetOf(Request::class.java.name)
    }

    override fun getSupportedSourceVersion(): SourceVersion {
        return SourceVersion.latest()
    }

    override fun process(
        set: MutableSet<out TypeElement>?,
        roundEnvironment: RoundEnvironment?
    ): Boolean {

        roundEnvironment?.getElementsAnnotatedWith(Request::class.java)
            ?.forEach { it ->
                val className = it.simpleName.toString()
                val pack = processingEnv.elementUtils.getPackageOf(it).toString()
                val fileName = "$pack.$className"

                FileSpec.builder(
                    pack, "Query$className"
                )
                    .addType(TypeSpec.classBuilder("Query$className").build())
                    .addProperty(
                    PropertySpec.builder(
                        "COLS",
                        String::class,
                        KModifier.PRIVATE
                    ).initializer(
                        "%S",
                        getCOLS(processingEnv.elementUtils, fileName)
                    ).build()
                ).build().writeTo(
                    File(
                        processingEnv.options[KAPT_KOTLIN_GENERATED_OPTION_NAME],
                        "$fileName.kt"
                    )
                )

            }
        return true
    }

    //todo harcode
    private fun getCOLS(elementUtils: Elements, className: String): String =
        elementUtils.getAllMembers(elementUtils.getTypeElement(className)).mapNotNull {
            if (it.kind == ElementKind.FIELD) {
                when {
                    it.asType().toString() == "java.lang.String" -> it.simpleName
                    it.asType().toString().contains("java.util.List") -> "${it.simpleName}${
                    getCOLS(
                        elementUtils,
                        it.asType().toString().substringAfter("<").replace(">", "")
                    )
                    }"
                    else -> "${it.simpleName}${getCOLS(
                        elementUtils,
                        it.asType().toString()
                    )}"
                }
            } else {
                null
            }
        }.toString().replace("[", "{").replace("]", "}")

    companion object {
        const val KAPT_KOTLIN_GENERATED_OPTION_NAME = "kapt.kotlin.generated"
    }
}