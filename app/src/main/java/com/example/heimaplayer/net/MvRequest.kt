package com.example.heimaplayer.net

import com.example.heimaplayer.model.MvPageBean
import com.example.heimaplayer.model.MyHomeItemBean
import com.example.heimaplayer.util.URLProviderUtils

class MvRequest(type:Int,area:String,offset:Int,handler: ResposeHandler<MvPageBean>) :MyRequest<MvPageBean>(type,
    URLProviderUtils.getMVListUrl(area,offset,30),handler
    ) {

}