package com.example.heimaplayer.adapter

import android.content.Context
import com.example.heimaplayer.Base.BaseListAdapter
import com.example.heimaplayer.model.MyHomeItemBean
import com.example.heimaplayer.widget.HomeItemView

class HomeAdapter : BaseListAdapter<MyHomeItemBean.DetailsBean, HomeItemView>() {
    override fun getItView(context: Context?): HomeItemView {
        return HomeItemView(context)
    }

    override fun RefreshItemView(itemView: HomeItemView, data: MyHomeItemBean.DetailsBean) {
        itemView.setData(data)
    }
}