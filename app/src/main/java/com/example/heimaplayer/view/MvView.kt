package com.example.heimaplayer.view

import com.example.heimaplayer.model.MvAreaBean

interface MvView {
    fun  onError(msg: String?)
    fun  onSuccess(result: List<MvAreaBean>)
}