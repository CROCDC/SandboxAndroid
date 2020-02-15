package com.cr.o.cdc.requestbuilder

import com.cr.o.cdc.annotations.GraphQlRequest
import com.cr.o.cdc.requestbuilder.FileGenerator.Companion.KAPT_KOTLIN_GENERATED_OPTION_NAME
import com.cr.o.cdc.requestsmodule.RequestInterface
import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import java.io.File
import javax.annotation.processing.ProcessingEnvironment
import javax.lang.model.element.Element
import javax.lang.model.element.ElementKind
import javax.lang.model.type.MirroredTypeException
import javax.lang.model.util.Elements

class QueryBuilder(
    elements: MutableSet<out Element>,
    private val processingEnv: ProcessingEnvironment
) {

    private val files = elements.map { element ->
        val annotation = element.getAnnotation(GraphQlRequest::class.java)
        val className = element.simpleName.toString()
        val thisClass = ClassName.bestGuess(element.toString())

        val parameters = annotation.inputs.map {
            ParameterSpec.builder(
                it.name,
                Class.forName(
                    try {
                        it.type.asTypeName()
                    } catch (e: MirroredTypeException) {
                        e.typeMirror.asTypeName()
                    }.toString()
                ).kotlin
            ).build()
        }

        FileSpec.builder("queries", "Query$className")
            .addType(
                TypeSpec.classBuilder("Query$className")
                    .addSuperinterface(
                        RequestInterface::class.asTypeName().parameterizedBy(
                            thisClass
                        )
                    )
                    .primaryConstructor(
                        FunSpec.constructorBuilder()
                            .addParameters(parameters)
                             .build()
                    )
                    .build()
            )
    }

    fun build() {
        files.forEach {
            it.build().writeTo(File(processingEnv.options[KAPT_KOTLIN_GENERATED_OPTION_NAME] ?: ""))
        }
    }

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
}