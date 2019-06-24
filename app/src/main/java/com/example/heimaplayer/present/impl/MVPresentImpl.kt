package com.example.heimaplayer.present.impl

import com.example.heimaplayer.Base.BaseListView
import com.example.heimaplayer.model.MvPageBean
import com.example.heimaplayer.present.interf.MvPresent

class MVPresentImpl(var mvitemView: BaseListView<MvPageBean>?) :MvPresent {
    override fun loadData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadMore(i: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    open override fun onDestroy() {
        if(mvitemView!=null){
            mvitemView=null
        }
    }
}