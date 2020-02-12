package com.cr.o.cdc.annotations

import kotlin.reflect.KClass

@Repeatable
annotation class Input(val name: String, val type: KClass<*>)