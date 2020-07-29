package com.cr.o.cdc.sandboxAndroid.utils

import androidx.navigation.NavController

class FakeNavController : NavController(FakeContext()) {

    var destiny: Int? = null

    override fun navigate(resId: Int) {
        destiny = resId
    }
}
