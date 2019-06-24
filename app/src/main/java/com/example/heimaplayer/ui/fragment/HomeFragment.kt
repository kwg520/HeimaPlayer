package com.example.heimaplayer.ui.fragment

import com.example.heimaplayer.Base.BaseListAdapter
import com.example.heimaplayer.Base.BaseListFragment
import com.example.heimaplayer.Base.BaseListPresent
import com.example.heimaplayer.adapter.HomeAdapter
import com.example.heimaplayer.model.MyHomeItemBean
import com.example.heimaplayer.present.impl.HomePresentImpl
import com.example.heimaplayer.widget.HomeItemView

class HomeFragment : BaseListFragment<MyHomeItemBean,MyHomeItemBean.DetailsBean, HomeItemView>() {
    override fun getSpecialPresent(): BaseListPresent {
        return HomePresentImpl(this)
    }

    override fun getSpecialAdpter(): BaseListAdapter<MyHomeItemBean.DetailsBean, HomeItemView> {
           return HomeAdapter()
    }

    override fun getList(respose: MyHomeItemBean?): List<MyHomeItemBean.DetailsBean>? {
        return respose?.details
    }

    override fun onDestroy() {
        super.onDestroy()
        present.onDestroy()
    }
}