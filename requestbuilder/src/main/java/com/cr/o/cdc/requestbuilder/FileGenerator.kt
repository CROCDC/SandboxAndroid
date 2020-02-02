package com.cr.o.cdc.requestbuilder

import com.cr.o.cdc.requestsannotations.GraphQlRequest
import com.google.auto.service.AutoService
import com.squareup.kotlinpoet.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import java.io.File
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.ElementKind
import javax.lang.model.element.TypeElement
import javax.lang.model.type.MirroredTypeException
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
                val className = it.simpleName.toString()
                val pack = processingEnv.elementUtils.getPackageOf(it).toString()
                val fileName = "$pack.$className"
                val cols = getCOLS(processingEnv.elementUtils, fileName)
                val name = annotation.name
                val url = annotation.url

                val inputs = annotation.inputs.map {
                    Pair(
                        it.name,
                        Class.forName(
                            try {
                                it.type.asTypeName()
                            } catch (e: MirroredTypeException) {
                                e.typeMirror.asTypeName()
                            }.toString()
                        ).kotlin
                    )
                }

                val parameters = inputs.map {
                    ParameterSpec.builder(
                        it.first,
                        it.second
                    ).build()
                }

                val header = inputs.map {
                    """${it.first}:\${'"'}${'$'}${it.first}\${'"'}"""
                }.toString().replace("[", "(").replace("]", ")")

                val queryBuilder = FunSpec.builder("queryBuilder")
                    .returns(String::class)
                    .addParameter(ParameterSpec.builder("query", String::class).build())
                    .addStatement("return Gson().toJson(QueryBuilder(query,null))")
                    .addModifiers(KModifier.PRIVATE)
                    .build()

                val need = "application/json; charset=utf-8".toMediaTypeOrNull()


                val parseMethod = if (annotation.nullable) {
                    "val jsonObj = JsonParser.parseString(json).asJsonObject.get(\"data\").asJsonObject.get(\"${annotation.name}\") \n" +
                            "if(jsonObj.isJsonNull){" +
                            "return null}" +
                            "else{" +
                            "return Gson().fromJson(jsonObj,${className}::class.java)" +
                            "}"
                } else {
                    "return Gson().fromJson(JsonParser.parseString(json).asJsonObject.get(\"data\").asJsonObject.get(\"${annotation.name}\"),${className}::class.java)"
                }

                val parse = FunSpec.builder("parseJson")
                    .returns(it.asType().asTypeName().asNullable())
                    .addParameter("json", String::class)
                    .addStatement(parseMethod)
                    .build()

                val classReturn = "$className${
                if (annotation.nullable) {
                    "?"
                } else {
                    ""
                }}"

                val bodyRequest = "{$name$header$cols}"

                val build = FunSpec.builder("build")
                    .addModifiers(KModifier.PUBLIC)
                    .addParameters(parameters)
                    .addModifiers()
                    .addStatement(
                        "return object : RequestInterface<$classReturn>{\n" +
                                "override fun parse(json:String):$classReturn = parseJson(json)\n" +
                                "override fun getRequest():Request = Request.Builder().url(\"$url\").post(queryBuilder(\"$bodyRequest\").toRequestBody(\"$need\".toMediaTypeOrNull())).build() \n" +
                                "override fun getDebugInto():DebugInfo = DebugInfo(\"$cols\",\"$bodyRequest\")\n" +
                                "\n}"
                    )
                    .build()

                @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
                FileSpec.builder("queries", "Query$className")
                    .addStaticImport("com.google.gson", "Gson")
                    .addStaticImport("com.google.gson", "JsonParser")
                    .addStaticImport("okhttp3.RequestBody.Companion", "toRequestBody")
                    .addStaticImport("okhttp3.MediaType.Companion", "toMediaTypeOrNull")
                    .addStaticImport("com.cr.o.cdc.requestsannotations", "DebugInfo")
                    .addStaticImport("com.cr.o.cdc.requestsannotations", "QueryBuilder")
                    .addStaticImport("com.cr.o.cdc.requestsannotations", "RequestInterface")
                    .addStaticImport("okhttp3", "Request")
                    .addType(
                        TypeSpec.classBuilder("Query$className")
                            .addFunction(queryBuilder)
                            .addFunction(build)
                            .addFunction(parse)
                            .build()
                    ).build()
                    .writeTo(File(processingEnv.options[KAPT_KOTLIN_GENERATED_OPTION_NAME]))

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