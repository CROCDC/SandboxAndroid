package com.cr.o.cdc.sandboxAndroid

import android.content.SharedPreferences

inline fun SharedPreferences.editAndApply(values: SharedPreferences.Editor.() -> SharedPreferences.Editor) {
    edit().values().apply()
}