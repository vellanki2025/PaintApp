package com.android.my.paintapp

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class DrawingView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private var drawPath: Path = Path()
    private var drawPaint: Paint = Paint()
    private var canvasPaint: Paint = Paint(Paint.DITHER_FLAG)
    private var drawCanvas: Canvas? = null
    private var canvasBitmap =
        android.graphics.Bitmap.createBitmap(1, 1, android.graphics.Bitmap.Config.ARGB_8888)

    init {
        setupDrawing()
    }

    private fun setupDrawing() {
        drawPaint.color = Color.BLACK
        drawPaint.isAntiAlias = true
        drawPaint.strokeWidth = 10f
        drawPaint.style = Paint.Style.STROKE
        drawPaint.strokeJoin = Paint.Join.ROUND
        drawPaint.strokeCap = Paint.Cap.ROUND
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        canvasBitmap =
            android.graphics.Bitmap.createBitmap(w, h, android.graphics.Bitmap.Config.ARGB_8888)
        drawCanvas = Canvas(canvasBitmap)
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawBitmap(canvasBitmap, 0f, 0f, canvasPaint)
        canvas.drawPath(drawPath, drawPaint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val touchX = event.x
        val touchY = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                drawPath.moveTo(touchX, touchY)
            }

            MotionEvent.ACTION_MOVE -> {
                drawPath.lineTo(touchX, touchY)
            }

            MotionEvent.ACTION_UP -> {
                drawCanvas?.drawPath(drawPath, drawPaint)
                drawPath.reset()
            }

            else -> return false
        }
        invalidate()
        return true
    }

    fun clearCanvas() {
        drawCanvas?.drawColor(Color.WHITE)
        invalidate()
    }

    fun changeColor(color: Int) {
        drawPaint.color = color
    }
}
