package com.example.heimaplayer.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.example.heimaplayer.R
import com.example.heimaplayer.model.MvPageBean
import com.example.heimaplayer.model.MyHomeItemBean
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_home.view.*

class MvitemView :RelativeLayout{


    fun setData(data: MvPageBean.VideosBean) {
        title.text = data.title
        desc.text = data.subTitle
        Picasso.get().load("http://"+data.image).into(imagebg)
    }
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        View.inflate(context, R.layout.iten_mv,this)
    }
}