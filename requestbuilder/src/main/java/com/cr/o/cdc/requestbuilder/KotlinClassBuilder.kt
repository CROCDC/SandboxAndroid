package com.cr.o.cdc.requestbuilder

/**
 * Created by Camilo on 01/01/20.
 */

class KotlinClassBuilder(
    fileName: String,
    packageName: String,
    cols: String
) {

    private val contentTemplate = """
package $packageName 
class Query$fileName {
    val COLS = "$cols"
}
    """.trimIndent()

    fun getContent(): String = contentTemplate
}