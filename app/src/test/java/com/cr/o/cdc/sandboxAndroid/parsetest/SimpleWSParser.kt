package com.cr.o.cdc.sandboxAndroid.parsetest

import com.google.gson.GsonBuilder
import java.lang.reflect.Type

class SimpleWSParser<T>(classOfT: Class<T>, private val rootNode: String) : WSParser {

    private val type: Type = classOfT
    private val includeElementsNode: Boolean = false
    private val adapters: MutableList<ResponseDeserializer.TypeAdapter> = mutableListOf()

    fun parserResponse(response: String): T? = GsonBuilder()
        .registerTypeAdapter(type, ResponseDeserializer<Any>(rootNode, includeElementsNode).apply {
            registerTypeAdapters(adapters)
        }).create().fromJson<T>(response, type)

}
