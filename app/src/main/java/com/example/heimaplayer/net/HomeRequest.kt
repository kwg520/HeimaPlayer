package com.example.heimaplayer.net

import com.example.heimaplayer.model.MyHomeItemBean
import com.example.heimaplayer.util.URLProviderUtils

class HomeRequest(type:Int,offset:Int,handler: ResposeHandler<MyHomeItemBean>)
    : MyRequest<MyHomeItemBean>
    (type,URLProviderUtils.getHomeUrl(offset,20),handler){
}