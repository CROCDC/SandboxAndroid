package com.cr.o.cdc.requestbuilder

/**
 * Created by Camilo on 01/01/20.
 */

class KotlinClassBuilder(className: String,
                         packageName:String,
                         greeting:String = "Merry Christmas!!"){

    private val contentTemplate = """
        package $packageName
        class $className {
             fun greeting() = "$greeting"
        }
    """.trimIndent()

    fun getContent() : String{

        return contentTemplate

    }

}