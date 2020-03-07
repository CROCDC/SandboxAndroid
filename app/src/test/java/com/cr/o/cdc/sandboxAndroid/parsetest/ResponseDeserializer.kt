package com.cr.o.cdc.sandboxAndroid.parsetest

import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.lang.reflect.Type

class ResponseDeserializer<T>(
    private val rootRode: String,
    private val includeElementsNode: Boolean
) : JsonDeserializer<T> {

    private val adapters: MutableList<TypeAdapter> = mutableListOf()

    fun registerTypeAdapters(adapters: List<TypeAdapter>) = this.adapters.addAll(adapters)

    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): T? =
        json.asJsonObject.let { obj ->
            when {
                obj.get("errors") is JsonArray && obj.get("errors").asJsonArray.size() > 0 -> null
                obj.has("data") -> obj.get("data").asJsonObject.get(rootRode)
                else -> null
            }?.takeIf { !it.isJsonNull }?.let {
                when {
                    !includeElementsNode && it.isJsonObject && it.asJsonObject.has("elements") -> it.asJsonObject.get(
                        "elements"
                    )
                    else -> it
                }
            }?.let {

                GsonBuilder().apply {
                    adapters.forEach { adapter ->
                        registerTypeAdapter(adapter.type, adapter.typeAdapter)
                    }
                }.create().fromJson<T>(it, typeOfT)
            }
        }

    data class TypeAdapter(val type: Type, val typeAdapter: JsonDeserializer<out Any>)
}
