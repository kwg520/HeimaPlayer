package com.example.heimaplayer.net

interface ResposeHandler<REPOSE> {
    fun onError(type:Int,string:String?)
    fun onSucess(type:Int,respose:REPOSE)
}