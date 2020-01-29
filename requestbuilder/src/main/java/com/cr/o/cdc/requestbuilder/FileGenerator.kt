package com.cr.o.cdc.requestbuilder

import com.cr.o.cdc.requestsannotations.GraphQlRequest
import com.cr.o.cdc.requestsannotations.RequestInterface
import com.google.auto.service.AutoService
import com.squareup.kotlinpoet.*
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
                val className = it.simpleName.toString()
                val pack = processingEnv.elementUtils.getPackageOf(it).toString()
                val fileName = "$pack.$className"

                FileSpec.builder("queries", "Query$className")
                    .addType(
                        TypeSpec.classBuilder("Query$className")
                            .addFunction(
                                FunSpec.builder("build")
                                    .addModifiers(KModifier.PUBLIC)

                                    .addCode(
                                        CodeBlock.builder()
                                            .add(
                                                CodeBlock.builder().addStatement("return object:RequestInterface")
                                                    .addStatement("override fun getRequestInfo() = RequestInfo()")
                                                    .build()
                                            )
                                            .build()
                                    )
                                    .returns(RequestInterface::class)
                                    .build()
                            )
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