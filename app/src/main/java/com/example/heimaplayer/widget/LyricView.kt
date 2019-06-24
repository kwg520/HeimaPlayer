package com.example.heimaplayer.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.example.heimaplayer.R

class LyricView:View {
    val paint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG)
    }
    var BigSize  =  0f
    var SmallSize = 0f
    var white = 0
    var green  =0
    private var ViewH :Int =0
    private var ViewW :Int  = 0
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        BigSize = resources.getDimension(R.dimen.bigSize)
        SmallSize = resources.getDimension(R.dimen.smallSize)
        white = ContextCompat.getColor(context,R.color.white)
        green  = ContextCompat.getColor(context,R.color.green)


        //加上这句话是以x轴对称
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        //初始化paint 字体和颜色大小
        paint.textSize =  BigSize
        paint.color = green

        //绘制内容
        var content = "正在加载。。"

        val bounds = Rect()

        paint.getTextBounds(content,0,content.length,bounds)
        val contentW = bounds.width()
        val contentH = bounds.height()

        val X = ViewW/2 - contentW/2
        val Y= ViewH/2+contentH/2

        canvas?.drawText(content,X.toFloat(),Y.toFloat(),paint)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        ViewH = h
        ViewW = w
    }
}