package com.cr.o.cdc.sandboxAndroid.customviews.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

class Curve @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawCurvedArrow(canvas)
    }

    private fun drawCurvedArrow(canvas: Canvas) {

        val paint = Paint()
        paint.isAntiAlias = true
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 40F
        paint.color = Color.MAGENTA

        val path = Path()
        path.moveTo(0F, height.toFloat())
        path.cubicTo(
            0F,
            height.toFloat(),
            (width / 2).toFloat(),
            -height.toFloat(),
            width.toFloat(),
            height.toFloat()
        )
        canvas.drawPath(path, paint)
    }
}