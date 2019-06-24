package com.example.heimaplayer.Base

import com.example.heimaplayer.model.MyHomeItemBean

interface BaseListView<RESPOSE> {
    fun OnFailure(message: String?)
    fun OnSucess(respose: RESPOSE)
    fun loadMore(respose: RESPOSE)
}