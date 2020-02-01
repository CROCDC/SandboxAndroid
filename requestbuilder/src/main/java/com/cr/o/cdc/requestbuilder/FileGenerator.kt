package com.cr.o.cdc.requestbuilder

import com.cr.o.cdc.requestsannotations.GraphQlRequest
import com.google.auto.service.AutoService
import com.squareup.kotlinpoet.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Request
import java.io.File
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.ElementKind
import javax.lang.model.element.TypeElement
import javax.lang.model.type.MirroredTypesException
import javax.lang.model.type.TypeMirror
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
                var type: List<TypeMirror> = listOf()
                try {
                    annotation.inputs
                } catch (mte: MirroredTypesException) {
                    type = mte.typeMirrors
                }
                val url = annotation.url

                val parameter = ParameterSpec.builder(
                    "input",
                    Class.forName(type[0].toString()).kotlin
                ).build()


                val queryBuilder = FunSpec.builder("queryBuilder")
                    .returns(String::class)
                    .addParameter(ParameterSpec.builder("query", String::class).build())
                    .addStatement("return Gson().toJson(QueryBuilder(query,null))")
                    .addModifiers(KModifier.PRIVATE)
                    .build()

                val declaration = """{$name(name:"\"$input"\")"""

                val variables = FunSpec.builder("buildVariables")
                    .returns(String::class)
                    .addParameter(parameter)
                    .addStatement("return \"$declaration$cols}\"")
                    .addModifiers(KModifier.PRIVATE)
                    .build()

                val need = "application/json; charset=utf-8".toMediaTypeOrNull()
                val request = FunSpec.builder("buildRequest")
                    .addModifiers(KModifier.PRIVATE)
                    .addParameter(parameter)
                    .addStatement("return Request.Builder().url(\"$url\").post(queryBuilder(buildVariables(input)).toRequestBody(\"$need\".toMediaTypeOrNull())).build()")
                    .returns(Request::class)
                    .build()


                val parse = FunSpec.builder("parseJson").returns(it.asType().asTypeName())
                    .addParameter("json", String::class)
                    .addStatement("return Gson().fromJson(JsonParser.parseString(json).asJsonObject.get(\"data\").asJsonObject.get(\"${annotation.name}\"),${className}::class.java)")
                    .build()

                val build = FunSpec.builder("build")
                    .addModifiers(KModifier.PUBLIC)
                    .addParameter(parameter)
                    .addStatement(
                        "return object : RequestInterface<$className>{\n" +
                                "override fun parse(json:String):$className = parseJson(json)\n" +
                                "override fun getRequest():Request = buildRequest(input)\n" +
                                "override fun getDebugInto():DebugInfo = DebugInfo(\"$cols\",buildVariables(input))\n" +
                                "\n}"
                    )
                    .build()

                FileSpec.builder("queries", "Query$className")
                    .addStaticImport("com.google.gson", "Gson")
                    .addStaticImport("com.google.gson", "JsonParser")
                    .addStaticImport("okhttp3.RequestBody.Companion", "toRequestBody")
                    .addStaticImport("okhttp3.MediaType.Companion", "toMediaTypeOrNull")
                    .addStaticImport("com.cr.o.cdc.requestsannotations", "DebugInfo")
                    .addStaticImport("com.cr.o.cdc.requestsannotations", "QueryBuilder")
                    .addStaticImport("com.cr.o.cdc.requestsannotations", "RequestInterface")
                    .addType(
                        TypeSpec.classBuilder("Query$className")
                            .addFunction(queryBuilder)
                            .addFunction(build)
                            .addFunction(parse)
                            .addFunction(request)
                            .addFunction(variables)
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