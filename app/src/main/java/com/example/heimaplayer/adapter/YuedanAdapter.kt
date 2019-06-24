package com.example.heimaplayer.adapter

import android.content.Context
import com.example.heimaplayer.Base.BaseListAdapter
import com.example.heimaplayer.model.YuedanBean
import com.example.heimaplayer.widget.YueDanItemView

class YuedanAdapter :BaseListAdapter<YuedanBean.PlaylistsBean, YueDanItemView>() {
    override fun getItView(context: Context?): YueDanItemView {
        return YueDanItemView(context)
    }

    override fun RefreshItemView(itemView: YueDanItemView, data: YuedanBean.PlaylistsBean) {
        itemView.setData(data)
    }
}