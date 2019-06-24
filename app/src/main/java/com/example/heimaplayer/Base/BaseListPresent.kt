package com.example.heimaplayer.Base

interface BaseListPresent {
    companion object{
        val TYPE_INIT_REFRESH =1
        val TYPE_LOAD_MORE =2
    }
    fun loadData()
    fun loadMore(i: Int)
    //解绑presenter和view
    fun onDestroy()
}