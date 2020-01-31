package com.cr.o.cdc.requestbuilder

import com.cr.o.cdc.requestsannotations.GraphQlRequest
import com.cr.o.cdc.requestsannotations.RequestInterface
import com.google.auto.service.AutoService
import com.squareup.kotlinpoet.*
import okhttp3.Request
import java.io.File
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.ElementKind
import javax.lang.model.element.TypeElement
import javax.lang.model.util.Elements

@AutoService(Processor::class)
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedOptions(FileGenerator.KAPT_KOTLIN_GENERATED_OPTION_NAME)
class FileGenerator : AbstractProcessor() {

    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        return mutableSetOf(GraphQlRequest::class.java.name)
    }

    override fun getSupportedSourceVersion(): SourceVersion {
        return SourceVersion.latest()
    }

    override fun process(
        set: MutableSet<out TypeElement>?,
        roundEnvironment: RoundEnvironment?
    ): Boolean {

        roundEnvironment?.getElementsAnnotatedWith(GraphQlRequest::class.java)
            ?.forEach { it ->
                val annotation = it.getAnnotation(GraphQlRequest::class.java)
                val nameRequest = annotation.name
                val parameters = annotation.parameters
                val url = annotation.url

                val variables = FunSpec.builder("buildVariables")
                    .returns(String::class)
                    .addParameters(parameters.map {
                        ParameterSpec.builder(
                            it.substringBefore(":"),
                            Class.forName(it.substringAfter(":")).kotlin
                        ).build()
                    })
                    .addModifiers(KModifier.PRIVATE)
                    .build()

                val request = FunSpec.builder("buildRequest")
                    .addModifiers(KModifier.PRIVATE)
                    .addStatement("return Request.Builder().url(\"$url\").build()")
                    .returns(Request::class)
                    .build()


                val build = FunSpec.builder("build")
                    .addModifiers(KModifier.PUBLIC)
                    .addStatement(
                        "return object : RequestInterface{\n" +
                                "override fun getRequest():Request = buildRequest()" +
                                "\n}"
                    )
                    .returns(RequestInterface::class)
                    .build()

                val className = it.simpleName.toString()
                val pack = processingEnv.elementUtils.getPackageOf(it).toString()
                val fileName = "$pack.$className"

                FileSpec.builder("queries", "Query$className")
                    .addType(
                        TypeSpec.classBuilder("Query$className")
                            .addFunction(variables)
                            .addFunction(build)
                            .addFunction(request)
                            .addProperty(
                                PropertySpec.builder(
                                    "COLS", String::class, KModifier.PUBLIC
                                ).initializer(
                                    "%S",
                                    getCOLS(processingEnv.elementUtils, fileName)
                                ).build()
                            ).build()
                    ).build().writeTo(
                        File(
                            processingEnv.options[KAPT_KOTLIN_GENERATED_OPTION_NAME]
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