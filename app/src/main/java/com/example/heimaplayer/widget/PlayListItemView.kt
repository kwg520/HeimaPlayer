package com.example.heimaplayer.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.example.heimaplayer.R
import com.example.heimaplayer.model.AudioBean
import kotlinx.android.synthetic.main.play_item.view.*

class PlayListItemView : RelativeLayout {
    fun setData(audioBean: AudioBean) {
        title.text = audioBean.display_name
        artist.text = audioBean.artist
    }

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    init {
        View.inflate(context, R.layout.play_item,this)
    }
}