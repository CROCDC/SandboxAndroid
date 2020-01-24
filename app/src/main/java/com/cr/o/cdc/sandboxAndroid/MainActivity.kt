package com.cr.o.cdc.sandboxAndroid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.cr.o.cdc.sandboxAndroid.databinding.ActivityMainBinding
import com.cr.o.cdc.sandboxAndroid.fragments.NotificationsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.btnTabLayout.setOnClickListener {
            supportFragmentManager.beginTransaction().add(R.id.root, TabLayoutFragment()).commit()
        }

        binding.btnNotifications.setOnClickListener {
            supportFragmentManager.beginTransaction().add(R.id.root, NotificationsFragment())
                .commit()
        }
    }
}
