package com.cr.o.cdc.sharedtest

fun Any.getPrivateField(fieldName: String): Any? = javaClass.getDeclaredField(fieldName).let {
    it.isAccessible = true
    return@let it.get(this)
}