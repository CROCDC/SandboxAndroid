package com.cr.o.cdc.sandboxAndroid.hiltbug

import android.app.TaskStackBuilder
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cr.o.cdc.sandboxAndroid.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Activity1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_1)
        TaskStackBuilder.create(this).addNextIntentWithParentStack(
            Intent(this, Activity2::class.java)
        ).startActivities()
    }
}
