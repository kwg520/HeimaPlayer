package com.example.heimaplayer.widget

import android.content.Context
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.view.*
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.PopupWindow
import com.example.heimaplayer.R

class PlayListPopWindow(
    context: Context,
    adapter: BaseAdapter,
    listener: AdapterView.OnItemClickListener,
    val window: Window
) : PopupWindow(context) {

    var alpha = 0f

    init {
        alpha = window.attributes.alpha
        val view = LayoutInflater.from(context).inflate(R.layout.pop_list, null, false)
        val listView = view.findViewById<ListView>(R.id.listView)
        listView.adapter = adapter
        listView.onItemClickListener = listener
        contentView = view
        width = ViewGroup.LayoutParams.MATCH_PARENT
        val manager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val point = Point()
        manager.defaultDisplay.getSize(point)
        val WindowH = point.y
        height = (WindowH * 3) / 5
        //设置点击
        isFocusable = true
        //设置外部点击取消
        isOutsideTouchable = true
        //能够响应放回按钮(低版本popwindow点击返回按钮消失的关键)
        setBackgroundDrawable(ColorDrawable())
        animationStyle = R.style.pop


    }

    override fun showAsDropDown(anchor: View?, xoff: Int, yoff: Int, gravity: Int) {
        super.showAsDropDown(anchor, xoff, yoff, gravity)
        val attributes = window.attributes
        attributes.alpha = 0.3f
        //设置到应用程序窗体上面
        window.attributes = attributes

    }

    override fun dismiss() {
        super.dismiss()
        val attribute = window.attributes
        attribute.alpha = alpha
        window.attributes = attribute
    }

}