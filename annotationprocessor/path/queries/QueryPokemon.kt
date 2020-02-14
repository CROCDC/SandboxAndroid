package queries

import com.cr.o.cdc.requestsannotations.DebugInfo
import com.cr.o.cdc.requestsannotations.QueryBuilder
import com.cr.o.cdc.requestsannotations.RequestInterface
import com.cr.o.cdc.sandboxAndroid.Pokemon
import com.google.gson.Gson
import com.google.gson.JsonParser
import kotlin.String
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody

class QueryPokemon {
    private fun queryBuilder(query: String): String = Gson().toJson(QueryBuilder(query,null))

    fun build() = object : RequestInterface<Pokemon?>{
    override fun parse(json:String):Pokemon? = parseJson(json)
    override fun getRequest():Request = Request.Builder().url("url").post(queryBuilder("{pokemon(){name}}").toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())).build() 
    override fun getDebugInto():DebugInfo = DebugInfo("{name}","{pokemon(){name}}")

    }

    fun parseJson(json: String): Pokemon? {
        val jsonObj = JsonParser.parseString(json).asJsonObject.get("data").asJsonObject.get("pokemon") 
                if(jsonObj.isJsonNull){return null}else{return Gson().fromJson(jsonObj,Pokemon::class.java)}
    }
}
