package com.cr.o.cdc.sandboxAndroid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.cr.o.cdc.requestsannotations.Request
import com.cr.o.cdc.sandboxAndroid.databinding.ActivityMainBinding
import com.cr.o.cdc.sandboxAndroid.model.Generated_Pokemon

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.btn1.setOnClickListener {
            supportFragmentManager.beginTransaction().add(R.id.root, TabLayoutFragment()).commit()
        }

    }
}
