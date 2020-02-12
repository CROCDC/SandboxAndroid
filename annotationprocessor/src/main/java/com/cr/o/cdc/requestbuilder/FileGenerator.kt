package com.cr.o.cdc.requestbuilder

import com.cr.o.cdc.annotations.GraphQlRequest
import com.cr.o.cdc.annotations.Injectable
import com.google.auto.service.AutoService
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.ElementKind
import javax.lang.model.element.TypeElement
import javax.lang.model.util.Elements

@AutoService(Processor::class)
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedOptions(FileGenerator.KAPT_KOTLIN_GENERATED_OPTION_NAME)
class FileGenerator : AbstractProcessor() {

    override fun getSupportedAnnotationTypes(): MutableSet<String> =
        mutableSetOf(GraphQlRequest::class.java.name, Injectable::class.java.name)


    override fun getSupportedSourceVersion(): SourceVersion = SourceVersion.latest()

    override fun process(
        set: MutableSet<out TypeElement>?,
        roundEnvironment: RoundEnvironment?
    ): Boolean {

        /*FragmentBuildersModuleBuilder(
            roundEnvironment?.getElementsAnnotatedWith(Injectable::class.java)
                ?: mutableSetOf(),
            processingEnv
        ).build()
        TODO
*/
        QueryBuilder(
            roundEnvironment?.getElementsAnnotatedWith(GraphQlRequest::class.java)
                ?: mutableSetOf(),
            processingEnv
        ).build()
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