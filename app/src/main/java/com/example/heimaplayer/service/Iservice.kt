package com.example.heimaplayer.service

import com.example.heimaplayer.model.AudioBean

interface Iservice {
    fun upDateState()
    fun isPlay(): Boolean?
    fun getDuration():Int
    fun getProgress(): Int?
    fun seekto(progress: Int)
    fun setMode()
    fun getMode(): Int
    fun playPre()
    fun playNext()
    fun getPlaylist(): List<AudioBean>?
    fun playCurrentPosition(position: Int)
}