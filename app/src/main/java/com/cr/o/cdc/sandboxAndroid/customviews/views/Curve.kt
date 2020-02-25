package com.cr.o.cdc.sandboxAndroid.customviews.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin

class Curve @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var size = 0

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawCurvedArrow(
            canvas,
            size * 0.2,// x start
            size * 0.6,// y start
            size * 0.8,//x end
            size * 0.6,// y end
            size * 0.2,// curve
            Color.MAGENTA,
            20
        )
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        size = measuredWidth.coerceAtMost(measuredHeight)
        setMeasuredDimension(size, size)

    }

    private fun drawCurvedArrow(
        canvas: Canvas,
        x1: Double,
        y1: Double,
        x2: Double,
        y2: Double,
        curveRadius: Double,
        color: Int,
        lineWidth: Int
    ) {

        val paint = Paint()
        paint.isAntiAlias = true
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = lineWidth.toFloat()
        paint.color = color

        val path = Path()
        val midX = x1 + (x2 - x1) / 2
        val midY = y1 + (y2 - y1) / 2
        val xDiff = (midX - x1).toFloat()
        val yDiff = (midY - y1).toFloat()
        val angle = atan2(yDiff.toDouble(), xDiff.toDouble()) * (180 / Math.PI) - 90
        val angleRadians = Math.toRadians(angle)
        val pointX = (midX + curveRadius * cos(angleRadians)).toFloat()
        val pointY = (midY + curveRadius * sin(angleRadians)).toFloat()

        path.moveTo(x1.toFloat(), y1.toFloat())
        path.cubicTo(x1.toFloat(), y1.toFloat(), pointX, pointY, x2.toFloat(), y2.toFloat())
        canvas.drawPath(path, paint)
    }
}