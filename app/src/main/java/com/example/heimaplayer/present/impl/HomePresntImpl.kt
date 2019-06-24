package com.example.heimaplayer.present.impl

import com.example.heimaplayer.Base.BaseListPresent
import com.example.heimaplayer.Base.BaseListView
import com.example.heimaplayer.model.MyHomeItemBean
import com.example.heimaplayer.net.HomeRequest
import com.example.heimaplayer.net.ResposeHandler
import com.example.heimaplayer.present.interf.HomePresent

class HomePresentImpl(var homeView: BaseListView<MyHomeItemBean>?) :HomePresent,ResposeHandler<MyHomeItemBean> {
    override fun onDestroy() {
        if(homeView!=null){
            homeView=null
        }
    }


    override fun onError(type: Int, string: String?) {
        homeView?.OnFailure(string)
    }

    override fun onSucess(type: Int, respose: MyHomeItemBean) {
          when(type){
              BaseListPresent.TYPE_INIT_REFRESH-> homeView?.OnSucess(respose)
              BaseListPresent.TYPE_LOAD_MORE-> homeView?.loadMore(respose)
          }
    }

    override fun loadData() {
            HomeRequest(BaseListPresent.TYPE_INIT_REFRESH,9,this).execute()

    }

    override fun loadMore(offset: Int) {
           HomeRequest(BaseListPresent.TYPE_LOAD_MORE,offset,this).execute()
    }
}