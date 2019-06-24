package com.example.heimaplayer.ui.fragment

import com.example.heimaplayer.Base.BaseListAdapter
import com.example.heimaplayer.Base.BaseListFragment
import com.example.heimaplayer.Base.BaseListPresent
import com.example.heimaplayer.adapter.YuedanAdapter
import com.example.heimaplayer.model.YuedanBean
import com.example.heimaplayer.present.impl.YuedanPresentImpl
import com.example.heimaplayer.widget.YueDanItemView


class YuedanFragment:BaseListFragment<YuedanBean,YuedanBean.PlaylistsBean, YueDanItemView>() {
    override fun getSpecialPresent(): BaseListPresent {
        return YuedanPresentImpl(this)
    }

    override fun getSpecialAdpter(): BaseListAdapter<YuedanBean.PlaylistsBean, YueDanItemView> {
        return YuedanAdapter()
    }

    override fun getList(respose: YuedanBean?): List<YuedanBean.PlaylistsBean>? {
        return respose?.playlists
    }

    override fun onDestroy() {
        super.onDestroy()
        present.onDestroy()
    }


}