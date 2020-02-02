package com.cr.o.cdc.requestsannotations

import kotlin.reflect.KClass

@Repeatable
annotation class Input(val name: String, val type: KClass<*>)