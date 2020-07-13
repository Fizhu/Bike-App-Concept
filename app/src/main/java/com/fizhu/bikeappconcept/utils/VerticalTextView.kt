package com.fizhu.bikeappconcept.utils

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.graphics.Rect
import android.util.AttributeSet
import android.widget.TextView
import com.fizhu.bikeappconcept.R


/**
 * Created by fizhu on 13,July,2020
 * https://github.com/Fizhu
 */

@SuppressLint("AppCompatCustomView")
class VerticalTextView : TextView {
    var text_bounds: Rect = Rect()
    private var direction = 0

    constructor(context: Context?) : super(context) {}
    @SuppressLint("CustomViewStyleable")
    constructor(
        context: Context,
        attrs: AttributeSet?
    ) : super(context, attrs) {
        val a = context.obtainStyledAttributes(
            attrs,
            R.styleable.verticaltextview
        )
        direction = a.getInt(R.styleable.verticaltextview_direction, 0)
        a.recycle()
        requestLayout()
        invalidate()
    }

    fun setDirection(direction: Int) {
        this.direction = direction
        requestLayout()
        invalidate()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        paint.getTextBounds(
            text.toString(), 0, text.length,
            text_bounds
        )
        if (direction == ORIENTATION_LEFT_TO_RIGHT
            || direction == ORIENTATION_RIGHT_TO_LEFT
        ) {
            setMeasuredDimension(
                measureHeight(widthMeasureSpec),
                measureWidth(heightMeasureSpec)
            )
        } else if (direction == ORIENTATION_UP_TO_DOWN
            || direction == ORIENTATION_DOWN_TO_UP
        ) {
            setMeasuredDimension(
                measureWidth(widthMeasureSpec),
                measureHeight(heightMeasureSpec)
            )
        }
    }

    private fun measureWidth(measureSpec: Int): Int {
        var result = 0
        val specMode = MeasureSpec.getMode(measureSpec)
        val specSize = MeasureSpec.getSize(measureSpec)
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize
        } else {
            result = (text_bounds.height() + paddingTop
                    + paddingBottom)
            // result = text_bounds.height();
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize)
            }
        }
        return result
    }

    private fun measureHeight(measureSpec: Int): Int {
        var result = 0
        val specMode = MeasureSpec.getMode(measureSpec)
        val specSize = MeasureSpec.getSize(measureSpec)
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize
        } else {
            result = text_bounds.width() + paddingLeft + paddingRight
            // result = text_bounds.width();
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize)
            }
        }
        return result
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
//		 super.onDraw(canvas);
        canvas.save()
        var startX = 0
        var startY = 0
        var stopX = 0
        var stopY = 0
        val path = Path()
        if (direction == ORIENTATION_UP_TO_DOWN) {
            startX = width - text_bounds.height() shr 1
            startY = height - text_bounds.width() shr 1
            stopX = width - text_bounds.height() shr 1
            stopY = height + text_bounds.width() shr 1
            path.moveTo(startX.toFloat(), startY.toFloat())
            path.lineTo(stopX.toFloat(), stopY.toFloat())
        } else if (direction == ORIENTATION_DOWN_TO_UP) {
            startX = width + text_bounds.height() shr 1
            startY = height + text_bounds.width() shr 1
            stopX = width + text_bounds.height() shr 1
            stopY = height - text_bounds.width() shr 1
            path.moveTo(startX.toFloat(), startY.toFloat())
            path.lineTo(stopX.toFloat(), stopY.toFloat())
        } else if (direction == ORIENTATION_LEFT_TO_RIGHT) {
            startX = width - text_bounds.width() shr 1
            startY = height + text_bounds.height() shr 1
            stopX = width + text_bounds.width() shr 1
            stopY = height + text_bounds.height() shr 1
            path.moveTo(startX.toFloat(), startY.toFloat())
            path.lineTo(stopX.toFloat(), stopY.toFloat())
        } else if (direction == ORIENTATION_RIGHT_TO_LEFT) {
            startX = width + text_bounds.width() shr 1
            startY = height - text_bounds.height() shr 1
            stopX = width - text_bounds.width() shr 1
            stopY = height - text_bounds.height() shr 1
            path.moveTo(startX.toFloat(), startY.toFloat())
            path.lineTo(stopX.toFloat(), stopY.toFloat())
        }
        this.paint.color = this.currentTextColor
        //		canvas.drawLine(startX, startY, stopX, stopY, this.getPaint());
        canvas.drawTextOnPath(text.toString(), path, 0f, 0f, this.paint)
        canvas.restore()
    }

    companion object {
        const val ORIENTATION_UP_TO_DOWN = 0
        const val ORIENTATION_DOWN_TO_UP = 1
        const val ORIENTATION_LEFT_TO_RIGHT = 2
        const val ORIENTATION_RIGHT_TO_LEFT = 3
    }
}