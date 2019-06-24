package com.example.heimaplayer.net

import android.util.Log
import com.example.heimaplayer.util.ThreadUtil
import okhttp3.*
import java.io.IOException

class NetManager private constructor(){
    private val client by lazy { OkHttpClient() }
    companion object {
        val manager by lazy { NetManager() }
    }

    /**
     * 发送网络请求
     */

      fun<REPOSE> sendRequest(req: MyRequest<REPOSE>){

        val request  = Request.Builder()
            .url(req.url)
            .header("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.75 Safari/537.36")
            .get()
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                ThreadUtil.runOnMainThread(Runnable {
                    req.resposeHandler.onError(req.type,e?.message)
                })
            }

            override fun onResponse(call: Call, response: Response?) {
                val result = response?.body()?.string()

                Log.e("djfasdjas",result.toString())
                val parseResult = req.parseResult(result)
                ThreadUtil.runOnMainThread(Runnable {
                    req.resposeHandler.onSucess(req.type,parseResult)
                })
            }

        })
    }

}