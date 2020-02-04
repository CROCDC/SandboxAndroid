package com.cr.o.cdc.sandboxAndroid.parsetest

import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class SimpleWSParser<T> : WSParser<T> {

    private val type: Type
    private val rootNode: String
    private val includeElementsNode: Boolean
    private val adapters: MutableList<ResponseDeserializer.TypeAdapter> = mutableListOf()

    constructor(classOfT: Class<T>, rootNode: String) {
        type = classOfT
        this.rootNode = rootNode
        includeElementsNode = false
    }

    constructor(typeToken: TypeToken<T>, rootNode: String) {
        type = typeToken.type
        this.rootNode = rootNode
        includeElementsNode = false
    }

    constructor(typeToken: TypeToken<T>, rootNode: String, includeElementsNode: Boolean) {
        type = typeToken.type
        this.rootNode = rootNode
        this.includeElementsNode = includeElementsNode
    }

    constructor(classOfT: Class<T>, rootNode: String, includeElementsNode: Boolean) {
        type = classOfT
        this.rootNode = rootNode
        this.includeElementsNode = includeElementsNode
    }

    fun registerTypeAdapter(type: Type, typeAdapter: JsonDeserializer<out Any>) =
        adapters.add(ResponseDeserializer.TypeAdapter(type, typeAdapter))

    override fun parserResponse(response: String): T? = GsonBuilder()
        .registerTypeAdapter(type, ResponseDeserializer<Any>(rootNode, includeElementsNode).apply {
            registerTypeAdapters(adapters)
        }).create().fromJson<T>(response, type)

    override fun getRootNode(): String = rootNode
}
