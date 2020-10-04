package com.cr.o.cdc.sandboxAndroid.preguntadoshelper.service

import android.annotation.SuppressLint
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.PixelFormat
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.cr.o.cdc.sandboxAndroid.databinding.WidgetOverlayPreguntadoHelperBinding
import kotlin.math.roundToInt

// https://developer.android.com/guide/components/bound-services
class PreguntadosHelperOverlayWidgetService : Service() {

    private lateinit var windowManager: WindowManager

    private lateinit var binding: WidgetOverlayPreguntadoHelperBinding

    private lateinit var broadcastReceiver: BroadcastReceiver

    private val binder = PreguntadosHelperOverlayWidgetBinder()

    @SuppressLint("ClickableViewAccessibility")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()

        binding = WidgetOverlayPreguntadoHelperBinding.inflate(LayoutInflater.from(this))

        windowManager = getSystemService(WINDOW_SERVICE) as WindowManager

        broadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                binding.txtAnswer.text = intent.getStringExtra(ARG_ANSWER)
            }
        }

        LocalBroadcastManager.getInstance(this)
            .registerReceiver(broadcastReceiver, IntentFilter(INTENT_FILTER))

        val params = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT
        )

        params.gravity = Gravity.TOP or Gravity.START

        params.x = 0
        params.y = 100

        windowManager.addView(binding.root, params)

        binding.imgClose.setOnClickListener {
            windowManager.removeView(binding.root)
            stopSelf()
        }

        binding.root.setOnTouchListener { _, event ->
            val initialX = 0
            val initialY = 0
            val initialTouchX = binding.root.width / 2
            val initialTouchY = binding.root.height / 2
            when (event.action) {

                MotionEvent.ACTION_MOVE -> {
                    val xDiff = (event.rawX - initialTouchX).roundToInt().toFloat()
                    val yDiff = (event.rawY - initialTouchY).roundToInt().toFloat()

                    params.x = initialX + xDiff.toInt()
                    params.y = initialY + yDiff.toInt()

                    windowManager.updateViewLayout(binding.root, params)
                    true
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_DOWN -> true
                else -> false
            }
        }

    }

    override fun onBind(intent: Intent?): IBinder? = binder

    override fun onDestroy() {
        super.onDestroy()
        windowManager.removeViewImmediate(binding.root)
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver)
    }

    fun setAnswer(answer: String) {
        binding.txtAnswer.text = answer
        windowManager.updateViewLayout(binding.root, binding.root.layoutParams)
    }

    companion object {
        const val INTENT_FILTER = "intent_filter"
        const val ARG_ANSWER = "answer"
    }

    inner class PreguntadosHelperOverlayWidgetBinder : Binder() {
        fun getService() = this@PreguntadosHelperOverlayWidgetService
    }
}
