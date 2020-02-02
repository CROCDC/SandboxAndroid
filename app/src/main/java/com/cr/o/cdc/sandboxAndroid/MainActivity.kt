package com.cr.o.cdc.sandboxAndroid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.cr.o.cdc.sandboxAndroid.databinding.ActivityMainBinding
import com.cr.o.cdc.sandboxAndroid.fragments.NotificationsFragment
import com.cr.o.cdc.sandboxAndroid.fragments.PokemonFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        
    }
}
