package com.example.heimaplayer.util

import android.os.Handler
import android.os.Looper

object ThreadUtil {

    val handler = Handler(Looper.getMainLooper())

    fun runOnMainThread(runnable: Runnable){
      handler.post(runnable)
    }
}