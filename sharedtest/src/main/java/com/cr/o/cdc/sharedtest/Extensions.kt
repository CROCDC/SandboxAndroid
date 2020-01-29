package com.cr.o.cdc.sharedtest

import android.content.Context

fun Any.getPrivateField(fieldName: String): Any? = javaClass.getDeclaredField(fieldName).let {
    it.isAccessible = true
    return@let it.get(this)
}

fun Context.sharedPreferences() =
    getSharedPreferences("${this.packageName}_test", Context.MODE_PRIVATE)