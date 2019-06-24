package com.example.heimaplayer.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.example.heimaplayer.R
import com.example.heimaplayer.model.YuedanBean
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import kotlinx.android.synthetic.main.item_home.view.*


class YueDanItemView: RelativeLayout{
    fun setData(data: YuedanBean.PlaylistsBean) {
        title.text = data.title
        desc.text = data.description
        Picasso.get().load("http://"+data.image).into(imagebg)
        Picasso.get().load("http://"+data.image)
            .transform(CropCircleTransformation()).into(image)
    }

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    init {
        View.inflate(context, R.layout.item_home,this)
    }
}