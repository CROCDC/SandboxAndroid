package com.cr.o.cdc.requestbuilder

import com.cr.o.cdc.annotations.GraphQlRequest
import com.cr.o.cdc.requestbuilder.FileGenerator.Companion.KAPT_KOTLIN_GENERATED_OPTION_NAME
import com.cr.o.cdc.requestsmodule.GraphQlDebugInfo
import com.cr.o.cdc.requestsmodule.QueryBuilder
import com.cr.o.cdc.requestsmodule.RequestInterface
import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import okhttp3.Request
import java.io.File
import javax.annotation.processing.ProcessingEnvironment
import javax.lang.model.element.Element
import javax.lang.model.element.ElementKind
import javax.lang.model.type.DeclaredType
import javax.lang.model.type.MirroredTypeException
import javax.lang.model.util.Elements
import kotlin.Int
import kotlin.String
import java.lang.Integer as int
import java.lang.String as string

class QueryBuilder(
    elements: MutableSet<out Element>,
    private val processingEnv: ProcessingEnvironment
) {

    private val files = elements.map { element ->
        val annotation = element.getAnnotation(GraphQlRequest::class.java)
        val className = element.simpleName.toString()
        val typeName = element.asType().asTypeName().copy(annotation.nullable)

        val cols = getCOLS(
            className,
            processingEnv.elementUtils,
            element.asType().asTypeName().toString()
        )

        val inputs = annotation.inputs.map {
            Pair(
                it.name,
                try {
                    it.type.asTypeName()
                } catch (e: MirroredTypeException) {
                    val typeMirror = e.typeMirror
                    when (typeMirror.asTypeName()) {
                        string::class.java.asTypeName() -> String::class.asTypeName()
                        int::class.java.asTypeName() -> Int::class.asTypeName()
                        else -> ClassName.bestGuess(typeMirror.asTypeName().toString())
                    }

                }
            )
        }

        val getQueryBuilderParameters = inputs.map {
            it.first
        }.toString().replace("[", "").replace("]", "")

        val parameters = inputs.map {
            ParameterSpec.builder(
                it.first,
                it.second
            ).build()
        }

        //https://github.com/square/kotlinpoet/issues/113 todo
        val properties = inputs.map {
            PropertySpec.builder(it.first, it.second)
                .initializer(it.first)
                .build()
        }

        val query = StringBuilder().apply {
            append("query ")
            append(element.simpleName.toString().toLowerCase())
            append("(")
            inputs.forEach {
                append(1)
                append(it.first)
                append(": ")
                append(it.second.simpleName)
                if (annotation.nullable) {
                    append("!")
                }
            }
            append(")")
            append("{")
            append(element.simpleName.toString().toLowerCase())
            append("(")
            inputs.forEach {
                append(it.first)
                append(": ")
                append(1)
                append(it.first)
            }
            append(")")
            append(cols)
            append("}")
        }.toString()

        val json = inputs.map {
            "\"${it.first}\":\"${"$"}${it.first}\""
        }.toString().replace("[", "{").replace("]", "}")


        FileSpec.builder("queries", "Query$className")
            .addImport("com.google.gson", "JsonParser", "GsonBuilder", "Gson")
            .addImport("com.cr.o.cdc.requestsmodule", "QueryBuilder")
            .addImport("okhttp3.RequestBody.Companion", "toRequestBody")
            .addImport("okhttp3.MediaType.Companion", "toMediaTypeOrNull")
            .addType(
                TypeSpec.classBuilder("Query$className")
                    .addSuperinterface(
                        RequestInterface::class.asTypeName().parameterizedBy(
                            typeName
                        )
                    )
                    .primaryConstructor(
                        FunSpec.constructorBuilder()
                            .addParameters(parameters)
                            .build()
                    )
                    .addProperties(properties)
                    .addFunction(
                        FunSpec.builder("parse")
                            .addModifiers(KModifier.OVERRIDE)
                            .addParameter(ParameterSpec.builder("json", String::class).build())
                            .addStatement("try {")
                            .addStatement(
                                "val jsonElement = JsonParser.parseString(json).asJsonObject.get(%P).asJsonObject.get(%P)",
                                "data",
                                element.simpleName.toString().toLowerCase()
                            )
                            .beginControlFlow("if(jsonElement.isJsonNull)")
                            .addStatement("return null")
                            .endControlFlow()
                            .addStatement(
                                "return GsonBuilder().create().fromJson(jsonElement , ${element.simpleName}::class.java)"
                            )
                            .addStatement("} catch(e:Exception){return null}")
                            .returns(typeName).build()
                    )
                    .addFunction(
                        FunSpec.builder("getRequest")
                            .returns(Request::class)
                            .addStatement(
                                "return Request.Builder().url(%P).post(Gson().toJson(getQueryBuilder($getQueryBuilderParameters)).toRequestBody(%P.toMediaTypeOrNull())).build()",
                                annotation.url,
                                "application/json; charset=utf-8"
                            )
                            .addModifiers(KModifier.OVERRIDE)
                            .build()
                    )
                    .addFunction(
                        FunSpec.builder("getDebugInfo")
                            .addModifiers(KModifier.OVERRIDE)
                            .returns(GraphQlDebugInfo::class)
                            .addStatement(
                                "return GraphQlDebugInfo(%P ,getQueryBuilder($getQueryBuilderParameters))",
                                annotation.url
                            ).build()
                    )
                    .addFunction(
                        FunSpec.builder("getQueryBuilder")
                            .addModifiers(KModifier.PRIVATE)
                            .addParameters(parameters)
                            .returns(QueryBuilder::class)
                            .addStatement(
                                "return QueryBuilder(%P.replace(%P,%P),%P)",
                                query,
                                "1",
                                "$",
                                json
                            )
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

    //todo
    private fun getCOLS(simpleName: String?, elementUtils: Elements, className: String): String =
        ArrayList<String>().apply {
            elementUtils.getAllMembers(elementUtils.getTypeElement(className))
                .filter { it.kind == ElementKind.FIELD }.forEach {
                    when {
                        it.asType().toString() == "java.lang.String" -> {
                            add(it.simpleName.toString())
                        }
                        it.asType().toString().contains("java.util.List") -> {
                            if (!simpleName.equals(it.simpleName.toString(), true)) {
                                add(it.simpleName.toString())
                            }
                            add(elementUtils.getAllMembers(elementUtils.getTypeElement((it.asType() as DeclaredType).typeArguments[0].toString())).filter { it.kind == ElementKind.FIELD }.toString())
                        }
                    }

                }
        }.toString().replace("[","{").replace("]","}")
}