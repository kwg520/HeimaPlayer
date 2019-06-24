package com.example.heimaplayer.net

import com.google.gson.Gson
import org.json.JSONObject
import java.lang.reflect.ParameterizedType

open class MyRequest<REPOSE>
    (val type:Int,var url:String, val resposeHandler: ResposeHandler<REPOSE>) {


    fun parseResult(result: String?): REPOSE  {
        val gson = Gson()
//        val jsonObject  =JSONObject(result)
//        var opt = jsonObject.opt("details")
//        val result1 = opt.toString()
        val type = (this.javaClass
            .genericSuperclass as ParameterizedType).actualTypeArguments[0]
        val list = gson.fromJson<REPOSE>(result,type)
        return list
    }

    /**
     * 发送网络请求
     */

    fun execute(){
        NetManager.manager.sendRequest(this)
    }

}