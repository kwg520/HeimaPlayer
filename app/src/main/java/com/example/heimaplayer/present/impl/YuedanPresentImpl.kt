package com.example.heimaplayer.present.impl

import com.example.heimaplayer.Base.BaseListPresent
import com.example.heimaplayer.Base.BaseListView
import com.example.heimaplayer.model.YuedanBean
import com.example.heimaplayer.net.ResposeHandler
import com.example.heimaplayer.net.YueDanRequest
import com.example.heimaplayer.present.interf.YueDanPresent
import com.example.heimaplayer.view.YuedanView

class YuedanPresentImpl (var yuedanView: BaseListView<YuedanBean>?):YueDanPresent,ResposeHandler<YuedanBean> {
    override fun onDestroy() {
        if(yuedanView!=null){
            yuedanView =null
        }
    }


    override fun onError(type: Int, string: String?) {

          yuedanView?.OnFailure(string)
    }

    override fun onSucess(type: Int, respose: YuedanBean) {
       when(type){
           BaseListPresent.TYPE_INIT_REFRESH-> yuedanView?.OnSucess(respose)
           BaseListPresent.TYPE_LOAD_MORE-> yuedanView?.loadMore(respose)
       }
    }

    override fun loadData() {
        YueDanRequest(BaseListPresent.TYPE_INIT_REFRESH,9,this).execute()
    }

    override fun loadMore(i: Int) {
        YueDanRequest(BaseListPresent.TYPE_LOAD_MORE,i,this).execute()
    }
}