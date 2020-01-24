package com.cr.o.cdc.sandboxAndroid

import android.content.SharedPreferences

/**
 * Created by Camilo on 22/01/20.
 */

inline fun SharedPreferences.editAndApply(values: SharedPreferences.Editor.() -> SharedPreferences.Editor) {
    edit().values().apply()
}