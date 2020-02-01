package com.cr.o.cdc.requestsannotations

import kotlin.reflect.KClass

/**
 * Created by Camilo on 01/01/20.
 */

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
@Repeatable
@MustBeDocumented
annotation class GraphQlRequest(
    val url: String,
    val name: String,
    val inputs: Array<KClass<*>>
)