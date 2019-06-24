package com.example.heimaplayer.net

import com.example.heimaplayer.model.ParseVideoBean

class VideoRequest(type: Int, url: String, resposeHandler: ResposeHandler<ParseVideoBean>) :
    MyRequest<ParseVideoBean>(type, url, resposeHandler) {
}