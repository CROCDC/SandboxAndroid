package queries

import com.cr.o.cdc.requests.DebugInfo
import com.cr.o.cdc.requests.QueryBuilder
import com.cr.o.cdc.requests.RequestInterface
import com.cr.o.cdc.requestsmodule.RequestInterface
import com.cr.o.cdc.sandboxAndroid.Pokemon
import com.google.gson.Gson
import com.google.gson.JsonParser
import kotlin.String
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody

class QueryPokemon {
  private fun queryBuilder(query: String): String = Gson().toJson(QueryBuilder(query,null))

  fun build(): RequestInterface =
      "com.cr.o.cdc.requestbuilder.QueryBuilder${'$'}files${'$'}1${'$'}returns${'$'}1@515c6049"

  fun parseJson(json: String): Pokemon {
    val jsonObj = JsonParser.parseString(json).asJsonObject.get("data").asJsonObject.get("pokemon") 
        if(jsonObj.isJsonNull){return null}else{return Gson().fromJson(jsonObj,Pokemon::class.java)}
  }
}
