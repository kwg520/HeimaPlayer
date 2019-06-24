package com.example.heimaplayer.adapter

import android.content.Context
import com.example.heimaplayer.Base.BaseListAdapter
import com.example.heimaplayer.model.MvPageBean
import com.example.heimaplayer.widget.MvitemView

class MvListAdapter : BaseListAdapter<MvPageBean.VideosBean, MvitemView>() {
    override fun getItView(context: Context?): MvitemView {
        return MvitemView(context)
    }

    override fun RefreshItemView(itemView: MvitemView, data: MvPageBean.VideosBean) {
        itemView.setData(data)
    }
}