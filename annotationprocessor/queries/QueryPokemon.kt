package queries

import Pokemon
import com.cr.o.cdc.requestsmodule.GraphQlDebugInfo
import com.cr.o.cdc.requestsmodule.QueryBuilder
import com.cr.o.cdc.requestsmodule.RequestInterface
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import java.lang.String
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody

class QueryPokemon(
  val name: String
) : RequestInterface<Pokemon?> {
  override fun parse(json: kotlin.String): Pokemon? {
    try {
    val jsonElement =
        JsonParser.parseString(json).asJsonObject.get("""data""").asJsonObject.get("""pokemon""")
    if(jsonElement.isJsonNull) {
      return null
    }
    return GsonBuilder().create().fromJson(jsonElement , Pokemon::class.java)
    } catch(e:Exception){return null}
  }

  override fun getRequest(): Request =
      Request.Builder().url("""url""").post(Gson().toJson(getQueryBuilder(name)).toRequestBody("""application/json; charset=utf-8""".toMediaTypeOrNull())).build()

  override fun getDebugInfo(): GraphQlDebugInfo = GraphQlDebugInfo("""url""" ,getQueryBuilder(name))

  private fun getQueryBuilder(name: String): QueryBuilder =
      QueryBuilder("""query pokemon(1name: java.lang.String){pokemon(name: 1name){name}}""".replace("""1""","""$"""),"""{"name":"$name"}""")
}
