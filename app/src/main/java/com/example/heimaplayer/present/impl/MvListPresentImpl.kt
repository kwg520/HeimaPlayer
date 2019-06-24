package com.example.heimaplayer.present.impl

import com.example.heimaplayer.Base.BaseListPresent
import com.example.heimaplayer.Base.BaseListView
import com.example.heimaplayer.model.MvPageBean
import com.example.heimaplayer.net.MvRequest
import com.example.heimaplayer.net.ResposeHandler
import com.example.heimaplayer.present.interf.MvListPresent
import com.example.heimaplayer.widget.MvitemView

class MvListPresentImpl(var code:String,var mvitemView: BaseListView<MvPageBean>?):MvListPresent,ResposeHandler<MvPageBean> {
    override fun onSucess(type: Int, respose: MvPageBean) {
        when(type){
            BaseListPresent.TYPE_INIT_REFRESH-> mvitemView?.OnSucess(respose)
            BaseListPresent.TYPE_LOAD_MORE-> mvitemView?.loadMore(respose)
        }
    }

    override fun loadData() {
        MvRequest(BaseListPresent.TYPE_INIT_REFRESH,code,0,this).execute()
    }

    override fun loadMore(i: Int) {
        MvRequest(BaseListPresent.TYPE_LOAD_MORE,code,i,this).execute()
    }

    override fun onDestroy() {
        if(mvitemView!=null){
            mvitemView=null
        }
    }

    override fun onError(type: Int, string: String?) {
        mvitemView?.OnFailure(string)
    }


}