package com.dkaresearchcenter.dkaframework.View

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import com.dkaresearchcenter.dkaframework.R

class ItemModel(context : Context, attrs : AttributeSet) : View(context, attrs) {
    init {
        context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.DKA,
                0, 0).apply {
            try {
                invalidate()
                requestLayout()
            } finally {
                recycle()
            }
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

    }
}