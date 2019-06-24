package com.example.heimaplayer.net

import com.example.heimaplayer.model.YuedanBean
import com.example.heimaplayer.util.URLProviderUtils

class YueDanRequest(type:Int,offset:Int,handler: ResposeHandler<YuedanBean>)
    :MyRequest<YuedanBean>(type,
    URLProviderUtils.getYueDanUrl(offset,20),handler) {
}