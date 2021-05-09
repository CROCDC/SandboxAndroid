package com.cr.o.cdc.sandboxAndroid.hiltbug

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cr.o.cdc.sandboxAndroid.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Activity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)
    }
}
