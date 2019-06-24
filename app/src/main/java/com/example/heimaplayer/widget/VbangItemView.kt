package com.example.heimaplayer.widget

import android.content.Context
import android.text.format.Formatter
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.example.heimaplayer.R
import com.example.heimaplayer.model.AudioBean
import kotlinx.android.synthetic.main.item_vbang.view.*

class VbangItemView : RelativeLayout {
    fun setData(itemBean: AudioBean) {
        title.text = itemBean.display_name
        artist.text = itemBean.artist
        size.text = Formatter.formatFileSize(context,itemBean.size)

    }

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        View.inflate(context, R.layout.item_vbang,this)
    }
}