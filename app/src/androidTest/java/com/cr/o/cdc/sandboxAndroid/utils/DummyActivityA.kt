package com.cr.o.cdc.sandboxAndroid.utils

import android.app.Activity
import android.os.Bundle
import android.widget.Button

class DummyActivityA : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(Button(this))
    }
}